package com.accenture.testingApplication.core.Constant;

public class DialogueConstant {
    public static final String ADMIN_PASSWORD = "1111";
    public static final String NO_ADMIN_MESSAGE = "no";
    public static final String START_USER  = "/start";
    public static final String REGISTER_USER  = "/register";
    public static final String LOGIN_USER  = "/login";
    public static final String TAKE_TEST_USER  = "/takeTest";
    public static final String LOGOUT_USER = "/logout";
    public static final String CREATE_TEST_USER = "/createTest";
    public static final String OPEN_TEST_USER = "/openTest";
    public static final String DELETE_TEST_USER = "/deleteTest";
    public static final String CREATE_QUESTION_USER = "/createQuestion";
    public static final String OPEN_QUESTION_USER = "/openQuestion";
    public static final String DELETE_QUESTION_USER = "/deleteQuestion";
    public static final String MISTAKE_MESSAGE_BOT = "я не знаю такой команды :(";
    public static final String MISTAKE_START_MESSAGE_BOT = "для начала работы введите\n" + START_USER;
    public static final String ABSENCE_USER_MESSAGE_BOT = "пользователь не найден";
    public static final String REGISTER_LOGIN_MESSAGE_BOT = "введите логин";
    public static final String REGISTER_NAME_MESSAGE_BOT = "введите имя";
    public static final String REGISTER_PASSWORD_MESSAGE_BOT = "введите пороль";
    public static final String REGISTER_COMPLETED_MESSAGE_BOT = "новый пользователь успешно зарегестрирован";
    public static final String TESTING_START_MESSAGE_BOT = "для того чтобы пройти тест введите его название";
    public static final String QUESTION_CREATE_AUTHOR_MESSAGE_BOT = "введите логин автора вопроса";
    public static final String QUESTION_CREATE_TYPE_MESSAGE_BOT = "введите тип вопроса";
    public static final String QUESTION_CREATE_DIFFICULTY_MESSAGE_BOT = "введите сложность вопроса";
    public static final String QUESTION_CREATE_TEXT_MESSAGE_BOT = "введите текст вопроса";
    public static final String QUESTION_CREATE_ANSWER_MESSAGE_BOT = "введите текст ответа на вопрос";
    public static final String QUESTION_CREATE_FINISH_MESSAGE_BOT = "вопрос успешно создан";
    public static final String QUESTION_OPEN_MESSAGE_BOT = "введите id открываемого вопроса";
    public static final String QUESTION_DELETE_СOMPLETED_MESSAGE_BOT = "вопрос успешно удалён";
    public static final String QUESTION_DELETE_MESSAGE_BOT = "введите id удаляемого вопроса";
    public static final String TEST_CREATE_NAME_MESSAGE_BOT = "введите название теста";
    public static final String TEST_OPEN_NAME_MESSAGE_BOT = "введите название открываемого теста";
    public static final String TEST_DELETE_MESSAGE_BOT = "введите название удаляемого теста";
    public static final String TEST_DELETE_СOMPLETED_MESSAGE_BOT = "тест успешно удалён";
    public static final String TEST_CREATE_TEXT_MESSAGE_BOT = "введите id вопросов через запятую";
    public static final String TEST_CREATE_FINISH_MESSAGE_BOT = "тест успешно создан";
    public static final String TEST_FINISH_MESSAGE_BOT = "тест завершен";
    public static final String QUESTION_PREPARED_STATEMENT_1 = "сложность: ";
    public static final String QUESTION_PREPARED_STATEMENT_2 = "\n\nтип: ";
    public static final String QUESTION_PREPARED_STATEMENT_3 = "\n\nавтор: ";
    public static final String QUESTION_PREPARED_STATEMENT_4 = "\n\n\nтекст вопроса:\n";
    public static final String QUESTION_PREPARED_STATEMENT_5 = "\n\nтекст ответа:\n";
    public static final String NUMBER_CORRECT_ANSWERS = "\nколличество правельных ответов: ";
    public static final String REGULAR_EXPRESSION_ANSWER = "\n\nтекст ответа:\n";
    public static final String REGULAR_EXPRESSION_QUESTION = "вопросы теста:\n";
    public static final String AUTHORIZATION_ADMIN_MESSAGE_BOT = "вы вошли как администратор\n" +
            "доступные команды:\n" + CREATE_TEST_USER + "  -создать тест\n" + OPEN_TEST_USER +
            "  -открыть тест\n" + DELETE_TEST_USER + "  -удалить тест\n" + CREATE_QUESTION_USER +
            "  -создать вопрос\n" + OPEN_QUESTION_USER + "  -открыть вопрос\n" + DELETE_QUESTION_USER +
            "  -удалить вопрос\n" + LOGOUT_USER+ "  -перезайти";
    public static final String AUTHORIZATION_USER_MESSAGE_BOT = "вы вошли как пользователь\n" +
            "доступные команды:\n" + TAKE_TEST_USER + "  -пройти тест\n" + LOGOUT_USER + "  -перезайти";
    public static final String REGISTER_STATUS_MESSAGE_BOT = "ввидите код для администраторов" +
            " или 'no' если регистрируетесь как пользователь";
    public static final String START_MESSAGE_BOT = "введити одну из команд:\n" +
            REGISTER_USER + "  -заригестрироваться\n" + LOGIN_USER + "  -войти";
}
