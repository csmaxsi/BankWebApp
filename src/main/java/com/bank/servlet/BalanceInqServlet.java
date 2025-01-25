package com.bank.servlet;

import com.bank.model.Account;
import com.bank.util.DBUtilities;
import com.bank.util.SessionUtilities;
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

public class BalanceInqServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = SessionUtilities.getUserIdFromSession(request, response);

        try (Connection conn = DBUtilities.getConnection()) {
            String sql = "SELECT account_id, balance, interest_rate FROM savingsAccounts WHERE user_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String accountNumber = rs.getString("account_id");
                        BigDecimal balance = rs.getBigDecimal("balance");
                        BigDecimal interestRate = rs.getBigDecimal("interest_rate");

                        BigDecimal totalBalance = balance.add(balance.multiply(interestRate).divide(BigDecimal.valueOf(100)));

                        Account account = new Account(accountNumber, totalBalance);

                        request.setAttribute("account", account);
                    } else {
                        request.setAttribute("error", "You do not have a savings account.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("balanceInq.jsp").forward(request, response);
    }

}
