package com.bank.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtilities extends HttpServlet {

    public static Integer getUserIdFromSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = null;

        try {
            userId = Integer.parseInt((String) session.getAttribute("user_id"));
            //System.out.println(userId);
        } catch (Exception e) {
            request.setAttribute("error", "Your session has timed out. Please log in again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

        return userId;
    }
}
