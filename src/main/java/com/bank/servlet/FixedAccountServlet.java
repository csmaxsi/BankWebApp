package com.bank.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FixedAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/fixedAccount.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null || operation.isEmpty()) {
            request.setAttribute("error", "Please select a valid operation.");
            request.getRequestDispatcher("/fixAccount").forward(request, response);
            return;
        }

        if(operation.equals("fixedBalance")){
            response.sendRedirect("fixDepoDetails");
        }else{
            request.setAttribute("error", "Select an operation.");
        }

    }
}