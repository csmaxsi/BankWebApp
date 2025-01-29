package com.bank.servlet;

import com.bank.model.Account;
import com.bank.util.SessionUtilities;
import com.bank.util.DBUtilities;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FixedDepoDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = SessionUtilities.getUserIdFromSession(request, response);

        try (Connection conn = DBUtilities.getConnection()) {
            String query = "SELECT account_id, maturity_date, deposit_amount, interest_rate, start_date FROM fixedDepositAccount WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String accountId = rs.getString("account_id");
                        BigDecimal depositAmount = rs.getBigDecimal("deposit_amount");
                        BigDecimal interestRate = rs.getBigDecimal("interest_rate");
                        LocalDate maturityDate = rs.getDate("maturity_date").toLocalDate();
                        LocalDate startDate = rs.getDate("start_date").toLocalDate();

                        long totalMonths = ChronoUnit.MONTHS.between(startDate, maturityDate);
                        double time = totalMonths / 12.0; // Convert months to years

                        BigDecimal interestEarned = depositAmount.multiply(interestRate.divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(time));

                        Account account = new Account(accountId, maturityDate, interestEarned); // Total amount including interest
                        request.setAttribute("account", account);
                    } else {
                        request.setAttribute("error", "No fixed deposit found for this user.");
                    }
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error retrieving fixed deposit details.");
        }
        request.getRequestDispatcher("fixDepoDetails.jsp").forward(request, response);
    }

}