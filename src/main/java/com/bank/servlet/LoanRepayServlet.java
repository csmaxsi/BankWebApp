package com.bank.servlet;

import com.bank.model.Account;
import com.bank.util.DBUtilities;
import com.bank.util.SessionUtilities;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRepayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = SessionUtilities.getUserIdFromSession(request, response);

        String accountId = null;
        try (Connection conn = DBUtilities.getConnection()) {
            String query = "SELECT account_id FROM loanAccount WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        accountId = rs.getString("account_id");
                        request.getSession().setAttribute("accountId", accountId);
                        Account account = new Account(accountId);
                        request.setAttribute("account", account);
                    } else {
                        request.setAttribute("error", "No loan account found for this user.");
                        request.getRequestDispatcher("loanRepayment.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error retrieving loan account details: " + e.getMessage());
            request.getRequestDispatcher("loanRepayment.jsp").forward(request, response);
            return;
        }
        request.setAttribute("accountId", accountId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/loanRepayment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountId = (String) request.getSession().getAttribute("accountId");

        if (accountId == null) {
            request.setAttribute("error", "Account not found.");
            forwardToJSP(request, response);
            return;
        }

        BigDecimal repaymentAmount = new BigDecimal(request.getParameter("amount"));

        if (repaymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            request.setAttribute("error", "Repayment amount must be greater than zero.");
            forwardToJSP(request, response);
            return;
        }

        try (Connection conn = DBUtilities.getConnection()) {
            String balanceQuery = "SELECT remaining_balance FROM loanAccount WHERE account_id = ?";
            BigDecimal currentBalance = BigDecimal.ZERO;

            try (PreparedStatement ps = conn.prepareStatement(balanceQuery)) {
                ps.setString(1, accountId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        currentBalance = rs.getBigDecimal("remaining_balance");
                    } else {
                        request.setAttribute("error", "Account not found.");
                        forwardToJSP(request, response);
                        return;
                    }
                }
            }

            if (repaymentAmount.compareTo(currentBalance) > 0) {
                request.setAttribute("error", "Repayment amount exceeds outstanding loan balance.");
                forwardToJSP(request, response);
                return;
            }

            String updateQuery = "UPDATE loans SET remaining_balance = remaining_balance - ? WHERE account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                ps.setBigDecimal(1, repaymentAmount);
                ps.setString(2, accountId);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    BigDecimal newBalance = currentBalance.subtract(repaymentAmount);
                    request.setAttribute("success", "Loan repayment successful. New balance: " + newBalance);
                } else {
                    request.setAttribute("error", "Error updating loan balance.");
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Database error occurred during repayment processing.");
        }
        forwardToJSP(request, response);
    }

    private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loanRepayment.jsp").forward(request, response); // Adjust this path as necessary
    }

}

