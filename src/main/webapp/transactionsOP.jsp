
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions Operation Form</title>
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
%>

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
%>
    <form action="transaction" method="POST">
        <h2 style="margin-bottom: 10px; text-align: center;"> Transaction Operations </h2>

        <div class="form-group">
            <label for="accountId">Account Number :</label>
            <input type="text" id="accountId" name="accountId" value="${accountId}" readonly>
        </div>

        <div class="form-group">
            <label for="transactionType">Transaction Operations:</label>
            <select id="transactionType" name="transactionType" required>
                <option value="" disabled selected>Select Transaction Type</option>
                <option value="Withdraw">Withdraw</option>
                <option value="Deposit">Deposit</option>
            </select>
        </div>

        <div class="form-group">
            <label for="amount">Amount :</label>
            <input type="number" id="amount" name="amount" required placeholder="Enter Amount" step="0.0001"/>
        </div>

        <button type="submit">Create</button>
    </form>
</div>
</body>
</html>

