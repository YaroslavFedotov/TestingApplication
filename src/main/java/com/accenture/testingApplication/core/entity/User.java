package com.accenture.testingApplication.core.entity;

public class User {
    private String login;
    private String name;
    private String password;
    private boolean admin_status;

    public User() {}

    public String getLogin() {
        return login;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public boolean getAdmin_status() {
        return admin_status;
    }
    public void setLogin(String login) { this.login = login; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin_status(boolean admin_status) {
        this.admin_status = admin_status;
    }
}
