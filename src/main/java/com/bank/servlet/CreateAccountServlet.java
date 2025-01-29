package com.bank.servlet;


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
import java.sql.SQLException;

import static com.bank.util.SessionUtilities.getUserIdFromSession;

public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createAccountz.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = SessionUtilities.getUserIdFromSession(request, response);

        String accountType = request.getParameter("accountType");

        try {
            switch (accountType) {
                case "savings" :
                    BigDecimal amountSav = new BigDecimal(request.getParameter("savingsAmount"));
                    BigDecimal interestRateSav = new BigDecimal(request.getParameter("savingsInterestRate"));
                    saveSavingsAccount(userId, amountSav, interestRateSav);
                    response.sendRedirect("savingsAccount.jsp");
                    break;
                case "fixed" :
                    BigDecimal amountFix = new BigDecimal(request.getParameter("fixedAmount"));
                    BigDecimal interestRateFix = new BigDecimal(request.getParameter("fixedInterestRate"));
                    int fixdtermMonths = Integer.parseInt(request.getParameter("fixedTerm")); // Assuming you have this input
                    saveFixedDeposit(userId, amountFix, fixdtermMonths, interestRateFix);
                    response.sendRedirect("fixedAccount.jsp");
                    break;
                case "loan" :
                    BigDecimal amountLoan = new BigDecimal(request.getParameter("loanAmount"));
                    BigDecimal interestRateLoan = new BigDecimal(request.getParameter("loanInterestRate"));
                    int loantermMonths = Integer.parseInt(request.getParameter("loanTerm")); // Assuming you have this input
                    saveLoan(userId, amountLoan, loantermMonths, interestRateLoan);
                    response.sendRedirect("loanAccount.jsp");
                    break;
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating account");
        }

    }

    private void saveSavingsAccount(Integer userId, BigDecimal balance, BigDecimal interestRate) throws SQLException {
        String sql = "INSERT INTO savingsAccounts (user_id, balance, interest_rate) VALUES (?, ?, ?)";

        try (Connection conn = DBUtilities.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setBigDecimal(2, balance);
            pstmt.setBigDecimal(3, interestRate);
            pstmt.executeUpdate();

            //System.out.println("Savings Account created for User ID: " + userId);
        }
    }

    private void saveFixedDeposit(Integer userId, BigDecimal depositAmount, int termMonths, BigDecimal interestRate) throws SQLException {
        String sql = "INSERT INTO fixedDepositAccount (user_id, deposit_amount, term_months, interest_rate, start_date) VALUES (?, ?, ?, ?, CURDATE())";

        try (Connection conn = DBUtilities.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setBigDecimal(2, depositAmount);
            pstmt.setInt(3, termMonths);
            pstmt.setBigDecimal(4, interestRate);
            pstmt.executeUpdate();

            //System.out.println("Fixed Deposit created for User ID: " + userId);
        }
    }

    private void saveLoan(Integer userId, BigDecimal loanAmount, int termMonths, BigDecimal interestRate) throws SQLException {
        String sql = "INSERT INTO loanAccount (user_id, loan_amount, remaining_balance, interest_rate,  loan_term , start_date) VALUES (?, ?, ?, ?, ?, CURDATE())";

        try (Connection conn = DBUtilities.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setBigDecimal(2, loanAmount);
            pstmt.setBigDecimal(3, loanAmount);
            pstmt.setBigDecimal(4, interestRate);
            pstmt.setInt(5, termMonths);
            pstmt.executeUpdate();

            //System.out.println("Loan created for User ID: " + userId);
        }
    }


}