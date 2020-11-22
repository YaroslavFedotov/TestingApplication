package com.accenture.testingApplication.core.connection;

import com.accenture.testingApplication.core.Constant.DialogueConstant;
import com.accenture.testingApplication.core.Constant.StructureDataBaseConstant;
import com.accenture.testingApplication.core.Constant.ConfigDataBaseConstant;
import com.accenture.testingApplication.core.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import static com.accenture.testingApplication.core.entity.Type.*;

public class ConnectionDataBase {

    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassCastException, SQLException {
        String ConnectionString = "jdbc:h2:" + ConfigDataBaseConstant.Path + "/" + ConfigDataBaseConstant.Name;
        try {
            Class.forName(ConfigDataBaseConstant.ClassDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(ConnectionString, ConfigDataBaseConstant.User, ConfigDataBaseConstant.Pass);
        return dbConnection;
    }

    public ResultSet getUser(User user) {
        ResultSet rs = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.USERS_TABLE + " WHERE " +
                StructureDataBaseConstant.USERS_LOGIN + " = ? AND " + StructureDataBaseConstant.USERS_PASSWORD +
                " = ? AND " + StructureDataBaseConstant.USERS_ADMIN_STATUS + "= ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setBoolean(3, user.getAdmin_status());
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public void registerUser(User user) throws SQLException {
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.USERS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.USERS_TABLE + "(" +
                StructureDataBaseConstant.USERS_ID + "," + StructureDataBaseConstant.USERS_LOGIN + "," +
                StructureDataBaseConstant.USERS_NAME + "," + StructureDataBaseConstant.USERS_PASSWORD + "," +
                StructureDataBaseConstant.USERS_ADMIN_STATUS + ")" +
                "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getName());
            prSt.setString(4, user.getPassword());
            prSt.setBoolean(5, user.getAdmin_status());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addTest(Test test) throws SQLException {
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.TESTS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.TESTS_TABLE + "(" +
                StructureDataBaseConstant.TESTS_ID + "," + StructureDataBaseConstant.TESTS_NAME + "," +
                StructureDataBaseConstant.TESTS_QUESTIONS_LIST + ")" +
                "VALUES(?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, test.getName());
            prSt.setString(3, test.getQuestions_list());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getTest(Test test) {
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.TESTS_TABLE + " WHERE " +
                StructureDataBaseConstant.TESTS_NAME + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
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
        return result;
    }

    public void eraseTest(Test test) {
        String searchSequence = getTest(test);
        String erase = "DELETE FROM " + StructureDataBaseConstant.TESTS_TABLE + " WHERE " +
                StructureDataBaseConstant.TESTS_QUESTIONS_LIST + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(erase);
            prSt.setString(1, searchSequence);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addQuestion(Question question, Answer answer) throws SQLException {
        String answerText;
        switch (question.getType()) {
            case single:
                answerText = answer.getSingle();
                break;
            case multiple:
                answerText = answer.getMultiple();
                break;
            case open:
                answerText = answer.getOpen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + question.getType());
        }
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.QUESTIONS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.QUESTIONS_TABLE + "(" +
                StructureDataBaseConstant.QUESTIONS_ID + "," + StructureDataBaseConstant.QUESTIONS_DIFFICULTY +
                "," + StructureDataBaseConstant.QUESTIONS_TYPE + "," + StructureDataBaseConstant.QUESTIONS_AUTHOR +
                "," + StructureDataBaseConstant.QUESTIONS_TEXT + "," + StructureDataBaseConstant.QUESTIONS_ANSWER_TEXT + ")" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, question.getDifficulty().toString());
            prSt.setString(3, question.getType().toString());
            prSt.setString(4, question.getAuthorLogin());
            prSt.setString(5, question.getQuestionText());
            prSt.setString(6, answerText);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getQuestion(String questionId) {
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.QUESTIONS_TABLE + " WHERE " +
                StructureDataBaseConstant.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, questionId);
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.next();
            result = DialogueConstant.QUESTION_PREPARED_STATEMENT_1 + rs.getString(2) +
                    DialogueConstant.QUESTION_PREPARED_STATEMENT_2 + rs.getString(3) +
                    DialogueConstant.QUESTION_PREPARED_STATEMENT_3 + rs.getString(4) +
                    DialogueConstant.QUESTION_PREPARED_STATEMENT_4 + rs.getString(5) +
                    DialogueConstant.QUESTION_PREPARED_STATEMENT_5 + rs.getString(6);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void eraseQuestion(String questionId) {
        String erase = "DELETE FROM " + StructureDataBaseConstant.QUESTIONS_TABLE + " WHERE " +
                StructureDataBaseConstant.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(erase);
            prSt.setString(1, questionId);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

