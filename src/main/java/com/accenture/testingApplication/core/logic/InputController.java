package com.accenture.testingApplication.core.logic;

import com.accenture.testingApplication.core.сonstant.DialogueConstant;
import com.accenture.testingApplication.core.entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;

public class InputController {

    private static boolean startModOn;
    private static boolean createQuestionModOn;
    private static boolean openQuestionModOn;
    private static boolean deleteQuestionModOn;
    private static boolean createTestModOn;
    private static boolean openTestModOn;
    private static boolean deleteTestModOn;
    private static boolean testingModOn;
    private static boolean registrationFlow;
    private static boolean authorizationFlow;
    private static boolean userFlow;
    private static boolean administrationFlow;
    private static int flowStep = 0;
    private static User user = new User();

    public String processingUserInput(String userInput, AdminController adminController,
                                      UserController userController) {
        if (registrationFlow) {
            return registerNewUserProcess(userInput);
        } else if (authorizationFlow) {
            return authorizationUserProcess(userInput);
        } else if (userFlow) {
            if (testingModOn) {
                return userController.testPerformer(userInput);
            } else {
                return userProcess(userInput); }
        } else if (administrationFlow) {
            administrationProcess(userInput);
            if (createQuestionModOn) {
                return (adminController.createQuestion(userInput));
            } else if (openQuestionModOn) {
                return (adminController.openQuestion(userInput));
            } else if (deleteQuestionModOn) {
                return adminController.deleteQuestion(userInput);
            } else if (createTestModOn) {
                return adminController.createTest(userInput);
            } else if (openTestModOn) {
                return adminController.openTest(userInput);
            } else if (deleteTestModOn) {
                return adminController.deleteTest(userInput);
            } else if (!administrationFlow) {
                return DialogueConstant.START_MESSAGE_BOT;
            }
        } else {
            switch (userInput) {
                case DialogueConstant.START_USER:
                    startModOn = true;
                    return DialogueConstant.START_MESSAGE_BOT;
                case DialogueConstant.LOGIN_USER:
                    if (startModOn) {
                        authorizationFlow = true;
                        return authorizationUserProcess(userInput);
                    } else {
                        return DialogueConstant.MISTAKE_START_MESSAGE_BOT;
                    }
                case DialogueConstant.REGISTER_USER:
                    if (startModOn) {
                        registrationFlow = true;
                        return registerNewUserProcess(userInput);
                    } else {
                        return DialogueConstant.MISTAKE_START_MESSAGE_BOT;
                    }
                default:
                    return DialogueConstant.MISTAKE_MESSAGE_BOT;
            }
        }
        return DialogueConstant.START_MESSAGE_BOT;
    }

    private String registerNewUserProcess(String currentMessage) {
        switch (flowStep) {
            case 0:
                flowStep++;
                return (DialogueConstant.REGISTER_LOGIN_MESSAGE_BOT);
            case 1:
                user.setLogin(currentMessage);
                flowStep++;
                return (DialogueConstant.REGISTER_NAME_MESSAGE_BOT);
            case 2:
                user.setName(currentMessage);
                flowStep++;
                return (DialogueConstant.REGISTER_PASSWORD_MESSAGE_BOT);
            case 3:
                user.setPassword(currentMessage);
                flowStep++;
                return (DialogueConstant.REGISTER_STATUS_MESSAGE_BOT);
            default:
                if (currentMessage.equals(DialogueConstant.ADMIN_PASSWORD)) {
                    user.setAdmin_status(true);
                } else if (currentMessage.equals(DialogueConstant.NO_ADMIN_MESSAGE)) {
                    user.setAdmin_status(false);
                } else {
                    return (DialogueConstant.MISTAKE_MESSAGE_BOT);
                }
                try {
                    user.registerUser(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                flowStopper();
                return (DialogueConstant.REGISTER_COMPLETED_MESSAGE_BOT +
                        "\n\n" + DialogueConstant.START_MESSAGE_BOT);
        }
    }

    private String authorizationUserProcess(String currentMessage) {
        switch (flowStep) {
            case 0:
                flowStep++;
                return (DialogueConstant.REGISTER_LOGIN_MESSAGE_BOT);
            case 1:
                user.setLogin(currentMessage);
                flowStep++;
                return (DialogueConstant.REGISTER_PASSWORD_MESSAGE_BOT);
            default:
                user.setPassword(currentMessage);
                user.setAdmin_status(false);
                ResultSet rs = user.getUser(user);
                boolean occurrence = false;
                try {
                    while (rs.next()) {
                        occurrence = true;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!occurrence) {
                    user.setAdmin_status(true);
                    rs = user.getUser(user);
                    boolean adminOccurrence = false;
                    try {
                        while (rs.next()) {
                            adminOccurrence = true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (adminOccurrence) {
                        flowStopper();
                        administrationFlow = true;
                        return DialogueConstant.AUTHORIZATION_ADMIN_MESSAGE_BOT;
                    } else {
                        flowStopper();
                        modesOff();
                        return DialogueConstant.ABSENCE_USER_MESSAGE_BOT;
                    }
                }
                flowStopper();
                userFlow = true;
                return DialogueConstant.AUTHORIZATION_USER_MESSAGE_BOT;
        }
    }

    private void administrationProcess (String currentMessage) {
        if (currentMessage.equals(DialogueConstant.CREATE_TEST_USER)) {

        } else if (currentMessage.equals(DialogueConstant.OPEN_TEST_USER)) {
            openTestModOn  = true;
        } else if (currentMessage.equals(DialogueConstant.DELETE_TEST_USER)) {
            deleteTestModOn = true;
        } else if (currentMessage.equals(DialogueConstant.CREATE_QUESTION_USER)) {
            createQuestionModOn = true;
        } else if (currentMessage.equals(DialogueConstant.OPEN_QUESTION_USER)) {
            openQuestionModOn  = true;
        } else if (currentMessage.equals(DialogueConstant.DELETE_QUESTION_USER)) {
            deleteQuestionModOn = true;
        } else if (currentMessage.equals(DialogueConstant.LOGOUT_USER)) {
            logout();
        }
    }

    private String userProcess (String currentMessage) {
        switch (currentMessage) {
            case DialogueConstant.TAKE_TEST_USER:
                testingModOn = true;
                return DialogueConstant.TESTING_START_MESSAGE_BOT;
            case DialogueConstant.LOGOUT_USER:
                logout();
                return DialogueConstant.START_MESSAGE_BOT;
            default:
                return DialogueConstant.MISTAKE_MESSAGE_BOT;
        }
    }

    private void logout() {
        authorizationFlow = false;
        administrationFlow = false;
        userFlow = false;
    }

    private void flowStopper () {
        administrationFlow = false;
        authorizationFlow = false;
        registrationFlow = false;
        userFlow = false;
        flowStep = 0;
    }

    public static void TestingFlowStopper() {
        testingModOn = false;
    }

    public static void modesOff() {
        createQuestionModOn = false;
        openQuestionModOn = false;
        deleteQuestionModOn = false;
        createTestModOn = false;
        openTestModOn = false;
        deleteTestModOn = false;
    }
}
