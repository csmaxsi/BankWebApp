package com.bank.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String userId;


    public User(String username, String password, String email) {
        this.username= username;
        this.password= password;
        this.email= email;
    }
    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public String getEmail() {

        return email;
    }

    public String getUserId() {
        return userId;
    }

    public void  setUserId(String userId) {
        this.userId = userId;
    }
}