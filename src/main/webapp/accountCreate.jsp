<%--
  Created by IntelliJ IDEA.
  User: maxsi
  Date: 1/21/25
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Account Creation Form</title>
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
    select, input[type="text"], input[type="number"] {
      width: 100%;
      height: 20px;
      margin: 15px;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    button[type="submit"] {
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
  %>

  <form action="account" method="POST">
    <h2 style="margin-bottom: 10px; text-align: center;"> Account Creation </h2>

    <div class="form-group">
      <label for="accountType">Account Type:</label>
      <select id="accountType" name="accountType" required>
        <option value="" disabled selected>Select Account Type</option>
        <option value="create">Create Account</option>
        <option value="savings">Savings Account</option>
        <option value="loan">Loans Account</option>
        <option value="fixed">Fixed Account</option>
      </select>
    </div>

    <div class="form-group">
      <label for="initialDeposit">Initial Deposit:</label>
      <input type="number" id="initialDeposit" name="initialDeposit" required placeholder="Enter Amount" step="0.0001"/>
    </div>

    <button type="submit">Create</button>
  </form>
</div>
</body>
</html>
