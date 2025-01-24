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
        // Retrieve the selected operation from the form
        String operation = request.getParameter("operation");

        // Check if the operation is valid
        if (operation == null || operation.isEmpty()) {
            // Set an error message if no operation is selected
            request.setAttribute("error", "Please select a valid operation.");
            request.getRequestDispatcher("/fixAccount").forward(request, response);
            return;
        }

        switch (operation) {
            case "transaction":
                // Handle transaction operation logic here
                // For example, you might call a service to process a transaction
                // Redirect or forward to a success page or another servlet
                response.sendRedirect("transaction");
                System.out.println("Transaction Successfull");
                break;

            case "balanceInquiry":
                // Handle balance inquiry logic here
                // For example, you might call a service to get the account balance
                response.sendRedirect("balanceInq");
                break;

                case "fixedBalance":
                // Handle balance inquiry logic here
                // For example, you might call a service to get the account balance
                response.sendRedirect("fixDepoDetails");
                break;


            default:
                // In case of an invalid operation
                request.setAttribute("error", "Invalid operation selected.");
                break;
        }
    }
}