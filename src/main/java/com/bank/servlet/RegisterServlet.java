package com.bank.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

import com.bank.util.DBUtilities;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

public class RegisterServlet extends HttpServlet {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confPass = request.getParameter("confPass");
        String email = request.getParameter("email");

        String error = validateInput(username, password, confPass, email);

        if(error != null) {
            //System.out.println("Phase 1 bug");
            System.err.println("Validation Error: " + error); // Add this line
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register").forward(request, response);
            System.out.println("Phase 2 bug");
            return;
        }


        try (Connection conn = DBUtilities.getConnection()) {
            User user = new User(username, password, email);
            if(isUserExist(conn, user.getUsername(),user.getPassword())) {
                //System.out.println("User exist");
                request.setAttribute("error", "Username or Email is already taken");
                request.getRequestDispatcher("/register").forward(request, response);
                return;
            }

            if(saveUser(conn, user)) {
                //System.out.println("save user");
                response.sendRedirect("login.jsp");
            } else {
                //System.out.println("save error");
                request.setAttribute("error", "Registration Failed!");
                request.getRequestDispatcher("/register").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("Registration error: " + e.getMessage());
            request.setAttribute("error", "Registration failed. Please try again later.");
            request.getRequestDispatcher("/register").forward(request, response);
        }
    }

    private boolean isUserExist(Connection conn, String username, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ? ";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private boolean saveUser(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            return ps.executeUpdate() > 0;
        }
    }

    private String validateInput(String username, String password, String confPass, String email) {
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return "Fill all the fields";
        }
        if(!Pattern.matches(EMAIL_REGEX, email)) {
            return "Invalid email";
        }
        if(username.length() < 3) {
            return "Username too short";
        }
        if(password.length() < 4) {
            return "Password is too short";
        }
        if(!confPass.equals(password)) {
            return "Password does not match";
        }
        return null;
    }
}