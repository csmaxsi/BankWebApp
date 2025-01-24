package com.bank.servlet;

import com.bank.model.Account;
import com.bank.util.DBUtilities;
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

public class BalanceInqServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("balanceInq.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int userId = Integer.parseInt(request.getParameter("userId"));
       System.out.println("user id is:" + userId);

        try(Connection conn = DBUtilities.getConnection()){
            String sql = "SELECT account_id, balance FROM accounts WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Account data found, set attributes to forward to JSP
                        String accountNumber = rs.getString("account_id");
                        BigDecimal balance = rs.getBigDecimal("balance");

                        System.out.println("account no is :"+ accountNumber);
                        Account account = new Account(accountNumber, balance);

                        // Set attributes to forward to JSP
                        request.setAttribute("account", account);
                    } else {
                        // No account found for the user
                        request.setAttribute("error", "No account found for the logged-in user.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Forward to the balance_inquiry.jsp to display the data
        request.getRequestDispatcher("balanceInq.jsp").forward(request, response);
    }

}
