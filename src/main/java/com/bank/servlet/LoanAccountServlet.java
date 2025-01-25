package com.bank.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoanAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("loanAccount.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null || operation.isEmpty()) {
            request.setAttribute("error", "Please select a valid operation.");
            request.getRequestDispatcher("loanAccount.jsp").forward(request, response);
            return;
        }

        switch (operation) {
            case "transaction":
                response.sendRedirect("transaction");
                System.out.println("Transaction Successfull");
                break;

            case "balanceInquiry":
                response.sendRedirect("balanceInq");
                break;

            case "loanRepay":
                response.sendRedirect("loanRepay");
                break;

            default:
                request.setAttribute("error", "Invalid operation selected.");
                break;
        }
    }
}