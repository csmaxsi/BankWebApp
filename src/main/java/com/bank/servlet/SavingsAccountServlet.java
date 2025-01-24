package com.bank.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SavingsAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("savingsAccount.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected operation from the form
        String operation = request.getParameter("operation");

        // Check if the operation is valid
        if (operation == null || operation.isEmpty()) {
            // Set an error message if no operation is selected
            request.setAttribute("error", "Please select a valid operation.");
            request.getRequestDispatcher("/savAccount").forward(request, response);
            return;
        }

        switch (operation) {
            case "transaction":
                response.sendRedirect("transaction");
                break;

            case "balanceInquiry":
                response.sendRedirect("balanceInq");
                break;

            default:
                request.setAttribute("error", "Invalid operation selected.");
                break;
        }
    }

}