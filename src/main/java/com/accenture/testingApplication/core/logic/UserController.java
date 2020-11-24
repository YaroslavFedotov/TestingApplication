package com.accenture.testingApplication.core.logic;

import com.accenture.testingApplication.core.entity.Question;
import com.accenture.testingApplication.core.сonstant.DialogueConstant;
import com.accenture.testingApplication.core.entity.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class UserController {

    private static int questionNumberInTheTest;
    private static int numberOfCorrectAnswers;
    private static ArrayList questionAnswerParts = new ArrayList();
    private static String questionMessage;
    private static ArrayList testQuestionId = new ArrayList();
    private static int testQuestionIdSize;

    public String testPerformer(String userMessage) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Question question = context.getBean("question", Question.class);
        Test test = context.getBean("test", Test.class);
        String answer;
        if (questionNumberInTheTest == 0) {
            for (String Currentquestion : test.getTest(test).replace(DialogueConstant.REGULAR_EXPRESSION_QUESTION, "").split(",")) {
                testQuestionId.add(Currentquestion);
                testQuestionIdSize++;

            }
        }
        String[] arrSplit;
        if (questionNumberInTheTest == 0) {
            questionMessage = question.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
            questionNumberInTheTest++;
            arrSplit = questionMessage.split(DialogueConstant.REGULAR_EXPRESSION_ANSWER);
            context.close();
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
                context.close();
                return DialogueConstant.TEST_FINISH_MESSAGE_BOT +
                        DialogueConstant.NUMBER_CORRECT_ANSWERS +
                        AnVar;
            } else {
                questionMessage = question.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
                arrSplit = questionMessage.split(DialogueConstant.REGULAR_EXPRESSION_ANSWER);
                questionNumberInTheTest++;
                context.close();
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
