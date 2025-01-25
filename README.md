# Bank Management System

## Project Overview
A web-based finance management system built using Java, Servlets, JSP, and MySQL. The application allows user registration, account management, and basic banking operations.

## Technologies Used
- Java 8
- Jakarta Servlets
- JSP
- MySQL
- Maven

## Features
- User Registration
- User Authentication
- Account Creation
- Transaction Management
- Balance Inquiry
- Loan Repayment
- Fixed Deposit Management

## Prerequisites
- Java Development Kit (JDK) 8+
- Apache Tomcat 9
- MySQL Database
- Maven

## Database Setup
1. Create database:
```sql
CREATE DATABASE bank_management;
USE bank_management;
```
## Installation Steps

1.Clone the repository
2.Configure database connection in DBUtilities.java
3.Build project: mvn clean package
4.Deploy WAR file to Tomcat

## Configuration

1.Update database credentials in DBUtilities.java
2.Modify pom.xml for dependency management

## Security Considerations

1.Implement password hashing
2.Add input validation
3.Use prepared statements

## Future Enhancements

3.Add two-factor authentication
4.Implement advanced security features
5.Create more robust error handling

## Contributing

1.Fork the repository
2.Create feature branch
3.Commit changes
4.Push to branch
5.Create pull request
