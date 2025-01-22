<%--
  Created by IntelliJ IDEA.
  User: maxsi
  Date: 1/22/25
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: maxsi
  Date: 1/22/25
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: maxsi
  Date: 1/22/25
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Options</title>
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
            flex-direction: column; /* Stack items vertically */
            align-items: flex-start; /* Align items to the start */
        }
        label {
            margin-bottom: 5px;
        }
        button {
            width: 100%;
            height: 35px;
            margin: 10px 0; /* Spacing between buttons */
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
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
    <!-- Display error message if it exists -->
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

    <form action="loaAccount" method="POST">
        <h2 style="margin-bottom: 10px; text-align: center;"> Transaction Options </h2>

        <div class="form-group">
            <label>Select an operation:</label>

            <button type="submit" name="operation" value="transaction">Transaction Operation</button>

            <button type="submit" name="operation" value="balanceInquiry">Balance Inquiry</button>

            <button type="submit" name="operation" value="loanRepay">Loan Repayment</button>

        </div>
    </form>
</div>

</body>
</html>



