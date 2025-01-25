<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Creation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: small;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
            margin: 0;
        }
        .form-container {
            width: 100%;
            max-width: 400px;
        }
        form {
            width: 100%;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: white;
            box-sizing: border-box;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        select, input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button[type="submit"] {
            width: 100%;
            height: 35px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button[type="submit"]:hover {
            background-color: #0056b3;
        }
        .account-details {
            display: none;
        }
        input:invalid {
            border-color: red;
        }
    </style>
</head>
<body>
<div class="form-container">
    <form action="createAccount" method="post">
        <div class="form-group">
            <label for="accountType">Select Account Type:</label>
            <select name="accountType" id="accountType" onchange="showAccountDetails()" >
                <option value="">Select Account Type</option>
                <option value="savings">Savings Account</option>
                <option value="fixed">Fixed Deposit</option>
                <option value="loan">Loan Account</option>
            </select>
        </div>

        <div id="savingsDetails" class="account-details">
            <div class="form-group">
                <label for="savingsAmount">Initial Deposit Amount:</label>
                <input type="number" name="savingsAmount" id="savingsAmount" min="100" >
            </div>
            <div class="form-group">
                <label for="savingsInterestRate">Savings Interest Rate:</label>
                <select name="savingsInterestRate" id="savingsInterestRate" >
                    <option value="">Select Interest Rate</option>
                    <option value="3.5">3.5%</option>
                    <option value="4.0">4.0%</option>
                </select>
            </div>
        </div>

        <div id="fixedDetails" class="account-details">
            <div class="form-group">
                <label for="fixedAmount">Fixed Deposit Amount:</label>
                <input type="number" id="fixedAmount" name="fixedAmount" min="1000" >
            </div>
            <div class="form-group">
                <label for="fixedTerm">Deposit Term (months):</label>
                <select name="fixedTerm" id="fixedTerm" >
                    <option value="">Select Term</option>
                    <option value="12">1 Year</option>
                    <option value="24">2 Years</option>
                    <option value="36">3 Years</option>
                </select>
            </div>
            <div class="form-group">
                <label for="fixedInterestRate">Fixed Deposit Interest Rate:</label>
                <select name="fixedInterestRate" id="fixedInterestRate" >
                    <option value="">Select Interest Rate</option>
                    <option value="5.5">5.5%</option>
                    <option value="6.5">6.5%</option>
                </select>
            </div>
        </div>

        <div id="loanDetails" class="account-details">
            <div class="form-group">
                <label for="loanAmount">Loan Amount:</label>
                <input type="number" name="loanAmount" id="loanAmount" min="1000" >
            </div>
            <div class="form-group">
                <label for="loanTerm">Loan Term (months):</label>
                <select name="loanTerm" id="loanTerm" >
                    <option value="">Select Term</option>
                    <option value="12">1 Year</option>
                    <option value="24">2 Years</option>
                    <option value="36">3 Years</option>
                </select>
            </div>
            <div class="form-group">
                <label for="loanInterestRate">Loan Interest Rate:</label>
                <select name="loanInterestRate" id="loanInterestRate" >
                    <option value="">Select Interest Rate</option>
                    <option value="8.5">8.5%</option>
                    <option value="9.5">9.5%</option>
                </select>
            </div>
        </div>

        <button type="submit">Create Account</button>
    </form>
</div>

<script>
    function showAccountDetails() {
        const accountType = document.getElementById('accountType').value;
        console.log(accountType);
        const detailsSections = document.querySelectorAll('.account-details');

        detailsSections.forEach(section => {
            section.style.display = 'none';
        });

        switch (accountType) {
            case 'savings':
                document.getElementById('savingsDetails').style.display = 'block';
                break;
            case 'fixed':
                document.getElementById('fixedDetails').style.display = 'block';
                break;
            case 'loan':
                document.getElementById('loanDetails').style.display = 'block';
                break;
        }

    }

</script>
</body>
</html>