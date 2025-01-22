package com.bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AccountCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("test 1: "+session.getAttribute("user_id"));
        Integer userId = (Integer) session.getAttribute("user_id");
        System.out.println(userId);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println("test 2: "+session.getAttribute("user_id"));
        Integer userId = Integer.parseInt((String) session.getAttribute("user_id"));
        System.out.println(userId);

        if (userId == null) {
            // Set an error message indicating session timeout
            request.setAttribute("error", "Your session has timed out. Please log in again.");

            // Redirect to login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String accType = request.getParameter("accountType");
        String initialDepositStr = request.getParameter("initialDeposit");

        if (accType.isEmpty() || initialDepositStr.isEmpty()) {
            //System.out.println("phase 0");
            request.setAttribute("error", "Please fill in all fields");
            request.getRequestDispatcher("/account").forward(request, response);
            return;
        }


        BigDecimal initialDeposit = new BigDecimal(initialDepositStr);
        //try {
//            if (initialDeposit <= 0) {
//                //System.out.println("Phase 1");
//                request.setAttribute("error", "Initial deposit must be greater than zero.");
//                request.getRequestDispatcher("/account").forward(request, response);
//                return;
//            }
//        } catch (NumberFormatException e) {
//            //System.out.println("Phase 2");
//            request.setAttribute("error", "Initial deposit must be a valid number.");
//            request.getRequestDispatcher("/account").forward(request, response);
//            return;
//        }

        try (Connection conn = DBUtilities.getConnection()) {
            Account account = new Account(accType, initialDeposit, userId);
            createAccountInDatabase(conn, account);
            switch (accType.toLowerCase()) {
                case "create":
                    response.sendRedirect("accountCreate");
                case "savings":
                    response.sendRedirect("savAccount");
                    break;
                case "fixed":
                    response.sendRedirect("fixAccount");
                    break;
                case "loan":
                    response.sendRedirect("loaAccount");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Account error: " + e.getMessage());
            request.setAttribute("error", "Account Creation failed. Please try again later.");
            request.getRequestDispatcher("/account").forward(request, response);
        }

    }

    private void createAccountInDatabase(Connection conn, Account account) throws SQLException {

        String sql = "INSERT INTO accounts (account_type, balance, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getAccType());
            ps.setBigDecimal(2, account.getBalance());
            ps.setInt(3, account.getId());
            ps.executeUpdate();
        }
    }


}
