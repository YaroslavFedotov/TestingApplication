package com.accenture.testingApplication.core.logic;

import com.accenture.testingApplication.core.Constant.DialogueConstant;
import com.accenture.testingApplication.core.connection.ConnectionDataBase;
import com.accenture.testingApplication.core.entity.*;

import java.sql.SQLException;

public class AdminController {
    private static Test test = new Test();
    private static Question question = new Question();
    private static int progressСounter = 0;

    public static String createQuestion(String adminMessage) {

        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.QUESTION_CREATE_AUTHOR_MESSAGE_BOT;
        } else if (progressСounter == 1) {
            progressСounter++;
            question.setAuthorLogin(adminMessage);
            return DialogueConstant.QUESTION_CREATE_TYPE_MESSAGE_BOT;
        } else if (progressСounter == 2) {
            progressСounter++;
            question.setType(Type.valueOf(adminMessage));
            return DialogueConstant.QUESTION_CREATE_DIFFICULTY_MESSAGE_BOT;
        } else if (progressСounter == 3) {
            progressСounter++;
            question.setDifficulty(Difficulty.valueOf(adminMessage));
            return DialogueConstant.QUESTION_CREATE_TEXT_MESSAGE_BOT;
        } else if (progressСounter == 4) {
            progressСounter++;
            question.setQuestionText(adminMessage);
            return DialogueConstant.QUESTION_CREATE_ANSWER_MESSAGE_BOT;
        } else {
            question.setAnswerText(adminMessage);
            ConnectionDataBase connectionDataBase = new ConnectionDataBase();
            try {
                connectionDataBase.addQuestion(question);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            question = new Question();
            progressСounter = 0;
            InputController.modesOff();
            return DialogueConstant.QUESTION_CREATE_FINISH_MESSAGE_BOT;
        }
    }
    public static String openQuestion(String adminMessage) {
        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.QUESTION_OPEN_MESSAGE_BOT;
        } else {
            ConnectionDataBase connectionDB = new ConnectionDataBase();
            progressСounter = 0;
            InputController.modesOff();
            return connectionDB.getQuestion(adminMessage);
        }
    }

    public static String deleteQuestion(String adminMessage) {
        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.QUESTION_DELETE_MESSAGE_BOT;
        } else {
            ConnectionDataBase connectionDataBase = new ConnectionDataBase();
            connectionDataBase.eraseQuestion(adminMessage);
            progressСounter = 0;
            InputController.modesOff();
            return DialogueConstant.QUESTION_DELETE_СOMPLETED_MESSAGE_BOT;
        }
    }

    public static String createTest(String adminMessage) {
        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.TEST_CREATE_NAME_MESSAGE_BOT;
        } else if (progressСounter == 1) {
            progressСounter++;
            test.setName(adminMessage);
            return DialogueConstant.TEST_CREATE_TEXT_MESSAGE_BOT;
        } else {
            progressСounter++;
            test.setQuestions_list(adminMessage);
            try {
                ConnectionDataBase connectionDataBase = new ConnectionDataBase();
                connectionDataBase.addTest(test);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            progressСounter = 0;
            InputController.modesOff();
            return DialogueConstant.TEST_CREATE_FINISH_MESSAGE_BOT;
        }
    }

    public static String openTest(String adminMessage) {
        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.TEST_OPEN_NAME_MESSAGE_BOT;
        } else {
            ConnectionDataBase connectionDataBase = new ConnectionDataBase();
            progressСounter = 0;
            InputController.modesOff();
            test.setName(adminMessage);
            return DialogueConstant.REGULAR_EXPRESSION_QUESTION + connectionDataBase.getTest(test);
        }
    }

    public static String deleteTest(String adminMessage) {
        if (progressСounter == 0) {
            progressСounter++;
            return DialogueConstant.TEST_DELETE_MESSAGE_BOT;
        } else {
            ConnectionDataBase connectionDataBase = new ConnectionDataBase();
            progressСounter = 0;
            InputController.modesOff();
            test.setName(adminMessage);
            connectionDataBase.eraseTest(test);
            return DialogueConstant.TEST_DELETE_СOMPLETED_MESSAGE_BOT;
        }
    }
}
