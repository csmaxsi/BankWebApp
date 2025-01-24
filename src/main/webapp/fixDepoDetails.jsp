
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.bank.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: maxsi
  Date: 1/24/25
  Time: 02:51 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Fixed Deposit Details</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      font-size: small;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f0f0f0;
    }
    form {
      width: 300px;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      background-color: white;
    }
    .form-group {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
    }
    label {
      width: 100px;
      padding-right: 10px;
      text-align: start;
      margin-bottom: 5px;
    }
    .view-field {
      width: 100%;
      padding: 8px;
      font-size: 14px;
      margin-bottom: 15px;
      border-radius: 4px;
      border: 1px solid #ddd;
      background-color: #f9f9f9;
      color: #333;
      text-align: center;
    }
    .error-message {
      color: red;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid red;
      background-color: #ffe6e6;
    }
    .error-container {
      text-align: center; /* Center the content */
      margin-bottom: 20px; /* Space between error message and form */
    }
    .form-container {
      display: flex; /* Use flexbox for alignment */
      flex-direction: column; /* Stack items vertically */
      justify-content: center; /* Center items vertically */
      align-items: center; /* Center items horizontally */
      min-height: 100vh; /* Minimum height of 100% of the viewport height */
    }
  </style>
</head>
<body>
<div class="form-container">
  <%-- Display error message if it exists --%>
  <%
    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage != null) {
  %>
  <div class="error-container">
    <div class="error-message">
      <%= errorMessage %> <!-- Using scriptlet to display the error message -->
    </div>
  </div>
  <%
    }
    Account account = (Account) request.getAttribute("account");

    // Extract account details if available
    String accountNumber = (account != null) ? account.getAccountId() : "N/A";
    BigDecimal balance = (account != null) ? account.getBalance() : BigDecimal.ZERO;

  %>

  <form action="fixDepoDetails" method="POST">
    <h2 style="margin-bottom: 10px; text-align: center;"> Fixed Deposit Details </h2>

    <div class="form-group">
      <label class="label" for="accountNumber">Account Number:</label><br>
      <div class="view-field" id="accountNumber">
      //  <%= accountNumber %> <!-- The account number will be set dynamically -->
      </div>
    </div>

    <div class="form-group">
      <label class="label" for="date">Maturity Date:</label><br>
      <div class="view-field" id="date">
        //<%= balance %> <!-- The balance will be set dynamically -->
      </div>
    </div>

    <div class="form-group">
      <label class="label" for="interest">Interest Earned:</label><br>
      <div class="view-field" id="interest">
       // <%= balance %> <!-- The balance will be set dynamically -->
      </div>
    </div>

  </form>
</div>
</body>
</html>


