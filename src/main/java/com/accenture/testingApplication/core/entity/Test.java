package com.accenture.testingApplication.core.entity;

import com.accenture.testingApplication.core.connection.ConnectionDataBase;
import com.accenture.testingApplication.core.сonstant.StructureDataBaseConstant;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    private String name;
    private String questions_list;

    public Test(String name) {
        this.name = name;
    }
    public Test() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuestions_list() {
        return questions_list;
    }
    public void setQuestions_list(String questions_list) {
        this.questions_list = questions_list;
    }


    public void addTest(Test test) throws SQLException  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = connectionDataBase.getDataBaseConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.TESTS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.TESTS_TABLE + "(" +
                StructureDataBaseConstant.TESTS_ID + "," + StructureDataBaseConstant.TESTS_NAME + "," +
                StructureDataBaseConstant.TESTS_QUESTIONS_LIST + ")" +
                "VALUES(?, ?, ?)";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, test.getName());
            prSt.setString(3, test.getQuestions_list());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
    }

    public String getTest(Test test) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.TESTS_TABLE + " WHERE " +
                StructureDataBaseConstant.TESTS_NAME + " = ?";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(select);
            prSt.setString(1, test.getName());
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.next();
            result = rs.getString(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
        return result;
    }

    public void eraseTest(Test test) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        String searchSequence = getTest(test);
        String erase = "DELETE FROM " + StructureDataBaseConstant.TESTS_TABLE + " WHERE " +
                StructureDataBaseConstant.TESTS_QUESTIONS_LIST + " = ?";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(erase);
            prSt.setString(1, searchSequence);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
    }
}
