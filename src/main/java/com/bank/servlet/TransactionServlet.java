package com.bank.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;

import com.bank.util.DBUtilities;
import com.bank.model.Transaction;
import com.bank.util.SessionUtilities;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = SessionUtilities.getUserIdFromSession(request, response);

        try (Connection conn = DBUtilities.getConnection()) {
            int accountId = getAccountIdByUserId(conn, userId);
            System.out.println(accountId);
            request.setAttribute("accountId", accountId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("transactionsOP.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Unable to retrieve account information.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("savingsAccount.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumber = request.getParameter("accountId");
        String transactionType = request.getParameter("transactionType");
        String amount = request.getParameter("amount");

        if (accountNumber.isEmpty() || transactionType.isEmpty() || amount.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields");
            request.getRequestDispatcher("/transaction").forward(request, response);
            return;
        }

        try {
            int accountId = Integer.parseInt(accountNumber);
            BigDecimal amountD = new BigDecimal(amount);

            if (amountD.compareTo(BigDecimal.ZERO) <= 0) {
                request.setAttribute("error", "Amount must be greater than zero.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction");
                dispatcher.forward(request, response);
                return;
            }
            try (Connection conn = DBUtilities.getConnection()) {
                Transaction transaction = new Transaction(accountId, amountD, transactionType);
                processTransaction(conn, transaction);
                saveTransaction(conn, transaction);
                request.setAttribute("success", "Transaction Successfull");
                RequestDispatcher dispatcher = request.getRequestDispatcher("transactionsOP.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                System.err.println("Transaction Error: " + e.getMessage());
                request.setAttribute("error", "Transaction failed. Please try again later.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction.");
                dispatcher.forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid account number or amount format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction");
            dispatcher.forward(request, response);
        }

    }

    private int getAccountIdByUserId(Connection conn, int userId) throws SQLException {
        String sql = "SELECT account_id FROM savingsAccounts WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("account_id");
                } else {
                    throw new SQLException("No account found for the given user ID.");
                }
            }
        }
    }


    private void processTransaction(Connection conn, Transaction transaction) throws SQLException {
        if(Objects.equals(transaction.getTransactionType(), "Withdraw")) {
            String sql = "UPDATE savingsAccounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
               try (PreparedStatement ps = conn.prepareStatement(sql)) {
                   ps.setBigDecimal(1, transaction.getAmount());
                   ps.setInt(2, transaction.getAccountId());
                   ps.setBigDecimal(3, transaction.getAmount());
                   int rows = ps.executeUpdate();
                   if (rows == 0) {
                       throw new SQLException ("Transaction failed for Withdrawal");
                   }
               }

        } else if (Objects.equals(transaction.getTransactionType(), "Deposit")) {
            String sql2 = "UPDATE savingsAccounts SET balance = balance + ? WHERE account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                ps.setBigDecimal(1, transaction.getAmount());
                ps.setInt(2, transaction.getAccountId());
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new SQLException ("Transaction failed for deposit !");

                }
            }
        }
    }


    private void saveTransaction(Connection conn, Transaction transaction) throws SQLException {
            String sql = "INSERT INTO transactions ( account_id, type, amount, transaction_date) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, transaction.getAccountId());
                ps.setString(2, transaction.getTransactionType());
                ps.setBigDecimal(3, transaction.getAmount());
                ps.setObject(4, transaction.getTimestamp());
                ps.executeUpdate();

            }
    }



}
