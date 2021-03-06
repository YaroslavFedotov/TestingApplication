package com.accenture.testingApplication.core;

import com.accenture.testingApplication.core.entity.Question;
import com.accenture.testingApplication.core.logic.AdminController;
import com.accenture.testingApplication.core.logic.InputController;
import com.accenture.testingApplication.core.logic.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		SpringApplication.run(TestingApplication.class, args);
		while (true) {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			InputController inputController = context.getBean("inputController", InputController.class);
			AdminController adminController = context.getBean("adminController", AdminController.class);
			UserController userController = context.getBean("userController", UserController.class);
			System.out.println(inputController.processingUserInput(in.nextLine(), adminController, userController));
			context.close();
		}
	}


}
