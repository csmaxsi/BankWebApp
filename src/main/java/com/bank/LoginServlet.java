package com.bank;

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
        System.out.println("doGet");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            //System.out.println("phase 0");
            request.setAttribute("error", "Please fill in all fields");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBUtilities.getConnection()) {
            User user = authenticateUser(conn, username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                //System.out.println("user: " + user.getUserId());
                session.setAttribute("user_id", user.getUserId()); // Store userId in session
                session.setMaxInactiveInterval(30*60);
                response.sendRedirect("accountCreate.jsp");
            } else {
                System.out.println("Phase 1");
                request.setAttribute("error", "Invalid Username or Password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            //System.out.println("Phase 2");
            System.err.println("Login error: " + e.getMessage());
            request.setAttribute("error", "Login failed. Please try again later.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private User authenticateUser(Connection conn, String username, String password) throws SQLException {
        String checkQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
        //System.out.println("phase -5");
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Create User object from database results
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
}