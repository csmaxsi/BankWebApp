<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login Form</title>
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
        input[type="email"], input[type="password"] {
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
        .register-link {
            text-align: center;
            margin-top: 5px;
        }
        .register-link a {
            text-decoration: none;
            color: #007bff;
        }
        .register-link a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>

<form action="" method="post">
    <h2 style="margin-bottom: 10px; text-align: center;"> User Login </h2>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <button type="submit">Login</button>

    <div class="register-link">
        <p style="margin-top: 5px;">Not a user? <a href="register.jsp">Register here</a></p>
    </div>
</form>

</body>
</html>

