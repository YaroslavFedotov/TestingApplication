package com.accenture.testingApplication.core;

import com.accenture.testingApplication.core.logic.InputController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;


@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		SpringApplication.run(TestingApplication.class, args);
		while (true) {
			System.out.println(InputController.processingUserInput(in.nextLine()));
		}
	}


}
