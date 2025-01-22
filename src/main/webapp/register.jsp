<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration Form</title>
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
        input[type="text"], input[type="email"], input[type="password"] {
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
        .login-link {
            text-align: center;
            margin-top: 5px;
        }
        .login-link a {
            text-decoration: none;
            color: #007bff;
        }
        .login-link a:hover {
            color: #0056b3;
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
        <%-- Add this near the top of your form --%>
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

        <form action="register" method="POST">
            <h2 style="margin-bottom: 10px; text-align: center; "> User Registration </h2>

            <div class="form-group">
                <label for="username">User Name:</label>
                <input type="text" id="username" name="username" placeholder="Enter Username" />
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Enter Password" />
            </div>

            <div class="form-group">
                <label for="confPassword">Confirm Password:</label>
                <input type="password" id="confPassword" name="confPass" required placeholder="Confirm Password" />
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="Enter Email" />
            </div>

            <button type="submit">Register</button>

            <div class="login-link">
                <p style="margin-top: 5px;">Already a user? <a href="login.jsp">Login here</a></p>
            </div>
        </form>
    </div>
</body>
</html>
