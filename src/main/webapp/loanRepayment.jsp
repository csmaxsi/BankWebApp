<%@ page import="com.bank.model.Account" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Loan Repayment Form</title>
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
      align-items: center;
    }
    label {
      padding-right: 5px;
      margin-bottom: 5px;
    }
    input[type="text"], input[type="password"] {
      width: 100%;
      height: 20px;
      margin: 15px;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    button[type="submit"] {
      margin: 15px;
      width: 100%;
      height: 35px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button[type="submit"]:hover {
      background-color: #0056b3;
    }
    .error-message {

      color: red;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid red;
      background-color: #ffe6e6;
    }
    .error-container {
      text-align: center;
      margin-bottom: 20px;
    }
    .success-message {
      color: black;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ffb700;
      background-color: #ffb700;
    }
    .success-container {
      text-align: center;
      margin-bottom: 20px;
      background-color: #ffb700;
    }
    .form-container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
    }
  </style>
</head>
<body>
<div class="form-container">

  <%
    String successMessage = (String) request.getAttribute("success");
    if (successMessage != null) {
  %>
  <div class="success-container">
    <div class="success-message">
      <%= successMessage %>
    </div>
  </div>
  <%
    }

    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage != null) {
  %>
  <div class="error-container">
    <div class="error-message">
      <%= errorMessage %>
    </div>
  </div>
  <%
    }
      Account account = (Account) request.getAttribute("account");

      String accountNumber = (account != null) ? account.getAccountId() : "N/A";
  %>

  <form action="loanRepay" method="POST">
    <h2 style="margin-bottom: 10px; text-align: center;"> Loan Repayment </h2>


    <div class="form-group">
      <label class="label" for="accountNumber">Account Number:</label><br>
      <div class="view-field" id="accountNumber">
        <%= accountNumber %>
      </div>
    </div>

    <div class="form-group">
      <label for="amount">Repay Amount:</label>
      <input type="number" id="amount" name="amount" step="0.0001" required>
    </div>

    <button type="submit">Submit</button>

  </form>
</div>

</body>
</html>


