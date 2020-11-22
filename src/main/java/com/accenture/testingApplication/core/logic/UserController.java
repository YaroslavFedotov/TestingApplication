package com.accenture.testingApplication.core.logic;

import com.accenture.testingApplication.core.Constant.DialogueConstant;
import com.accenture.testingApplication.core.connection.ConnectionDataBase;
import com.accenture.testingApplication.core.entity.Test;

import java.util.ArrayList;

public class UserController {
    private static int questionNumberInTheTest;
    private static int numberOfCorrectAnswers;
    private static ArrayList questionAnswerParts = new ArrayList();
    private static String questionMessage;
    private static ArrayList testQuestionId = new ArrayList();
    private static int testQuestionIdSize;

    public static String testPerformer(String userMessage) {
        String answer;
        ConnectionDataBase connectionDB = new ConnectionDataBase();
        if (questionNumberInTheTest == 0) {
            Test test = new Test(userMessage);
            for (String question : connectionDB.getTest(test).replace(DialogueConstant.REGULAR_EXPRESSION_QUESTION, "").split(",")) {
                testQuestionId.add(question);
                testQuestionIdSize++;

            }
        }
        String[] arrSplit;
        if (questionNumberInTheTest == 0) {
            questionMessage = connectionDB.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
            questionNumberInTheTest++;
            arrSplit = questionMessage.split(DialogueConstant.REGULAR_EXPRESSION_ANSWER);
            return arrSplit[0];
        } else {
            arrSplit = questionMessage.split(DialogueConstant.REGULAR_EXPRESSION_ANSWER);
            answer = arrSplit[1];
            checkAnswer(userMessage, answer);
            if (testQuestionIdSize == questionNumberInTheTest) {
                InputController.TestingFlowStopper();
                testQuestionId = new ArrayList();
                questionAnswerParts = new ArrayList();
                questionNumberInTheTest = 0;
                int AnVar = numberOfCorrectAnswers;
                numberOfCorrectAnswers = 0;
                testQuestionIdSize = 0;
                return DialogueConstant.TEST_FINISH_MESSAGE_BOT +
                        DialogueConstant.NUMBER_CORRECT_ANSWERS +
                        AnVar;
            } else {
                questionMessage = connectionDB.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
                arrSplit = questionMessage.split(DialogueConstant.REGULAR_EXPRESSION_ANSWER);
                questionNumberInTheTest++;
                return arrSplit[0];
            }
        }
    }

    public static void checkAnswer(String userAnswer, String answer) {
        if (userAnswer.equals(answer)) {
            numberOfCorrectAnswers++;
        }
    }
}
