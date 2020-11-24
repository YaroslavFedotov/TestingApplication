package com.accenture.testingApplication.core.entity;

import com.accenture.testingApplication.core.connection.ConnectionDataBase;
import com.accenture.testingApplication.core.сonstant.DialogueConstant;
import com.accenture.testingApplication.core.сonstant.StructureDataBaseConstant;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {

    private Type type;
    private String authorLogin;
    private Difficulty difficulty;
    private String questionText;
    private String answerText;

    public Question() {};
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getAuthorLogin() {
        return authorLogin;
    }
    public void setAuthorLogin(String authorLogin) { this.authorLogin = authorLogin; }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public String getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answerText) { this.answerText = answerText; }

    public void addQuestion(Question question) throws SQLException  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = connectionDataBase.getDataBaseConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + StructureDataBaseConstant.QUESTIONS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + StructureDataBaseConstant.QUESTIONS_TABLE + "(" +
                StructureDataBaseConstant.QUESTIONS_ID + "," + StructureDataBaseConstant.QUESTIONS_DIFFICULTY +
                "," + StructureDataBaseConstant.QUESTIONS_TYPE + "," + StructureDataBaseConstant.QUESTIONS_AUTHOR +
                "," + StructureDataBaseConstant.QUESTIONS_TEXT + "," + StructureDataBaseConstant.QUESTIONS_ANSWER_TEXT + ")" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, question.getDifficulty().toString());
            prSt.setString(3, question.getType().toString());
            prSt.setString(4, question.getAuthorLogin());
            prSt.setString(5, question.getQuestionText());
            prSt.setString(6, question.getAnswerText());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
    }

    public String getQuestion(String questionId) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + StructureDataBaseConstant.QUESTIONS_TABLE + " WHERE " +
                StructureDataBaseConstant.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(select);
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
        context.close();
        return result;
    }

    public void eraseQuestion(String questionId) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionDataBase connectionDataBase = context.getBean("connectionDataBase", ConnectionDataBase.class);
        String erase = "DELETE FROM " + StructureDataBaseConstant.QUESTIONS_TABLE + " WHERE " +
                StructureDataBaseConstant.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = connectionDataBase.getDataBaseConnection().prepareStatement(erase);
            prSt.setString(1, questionId);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        context.close();
    }
}
