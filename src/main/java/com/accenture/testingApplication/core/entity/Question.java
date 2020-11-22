package com.accenture.testingApplication.core.entity;

public class Question {
    private Type type;
    private String authorLogin;
    private Difficulty difficulty;
    private String questionText;
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
}
