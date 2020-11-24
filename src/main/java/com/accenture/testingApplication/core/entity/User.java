package com.accenture.testingApplication.core.entity;

import com.accenture.testingApplication.core.connection.ConnectionDataBase;
import com.accenture.testingApplication.core.сonstant.StructureDataBaseConstant;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ResultSet getUser(User user) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.USERS_TABLE + " WHERE " +
                StructureDataBaseConstant.USERS_LOGIN + " = ? AND " + StructureDataBaseConstant.USERS_PASSWORD +
                " = ? AND " + StructureDataBaseConstant.USERS_ADMIN_STATUS + "= ?";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setBoolean(3, user.getAdmin_status());
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
        return rs;
    }

    public void registerUser(User user) throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = connectionDataBase.getDataBaseConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.USERS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.USERS_TABLE + "(" +
                StructureDataBaseConstant.USERS_ID + "," + StructureDataBaseConstant.USERS_LOGIN + "," +
                StructureDataBaseConstant.USERS_NAME + "," + StructureDataBaseConstant.USERS_PASSWORD + "," +
                StructureDataBaseConstant.USERS_ADMIN_STATUS + ")" +
                "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getName());
            prSt.setString(4, user.getPassword());
            prSt.setBoolean(5, user.getAdmin_status());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
    }
}
