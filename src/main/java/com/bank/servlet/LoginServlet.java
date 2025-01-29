package com.bank.servlet;

import com.bank.util.DBUtilities;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBUtilities.getConnection()) {
            User user = authenticateUser(conn, username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("user_id", user.getUserId());
                session.setMaxInactiveInterval(30*60);

                if(savingsUser(conn, user.getUserId())) {
                    response.sendRedirect("savAccount");
                } else if (loanUser(conn,user.getUserId())) {
                    response.sendRedirect("loaAccount");
                } else if (fixedUSer(conn, user.getUserId())) {
                    response.sendRedirect("fixAccount");
                } else {
                    response.sendRedirect("createAccount");
                }
            } else {
                request.setAttribute("error", "Invalid Username or Password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
            request.setAttribute("error", "Login failed. Please try again later.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private User authenticateUser(Connection conn, String username, String password) throws SQLException {
        String checkQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("user_id"),
                            rs.getString("username"),
                            null,
                            rs.getString("email")
                    );
                }

            }
        }
        return null;
    }

    private boolean savingsUser(Connection conn, String userId) throws SQLException {
        String query = "Select COUNT(*) FROM savingsAccounts WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 1;
                }
            }
        }
        return false;
    }

    private boolean loanUser(Connection conn, String userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM loanAccount WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, userId);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        } return false;
    }

    private boolean fixedUSer (Connection conn, String userId) throws SQLException {
        String query = " SELECT COUNT(*) FROM fixedDepositAccount WHERE user_id = ?";
        try( PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, userId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }return false;
    }
}