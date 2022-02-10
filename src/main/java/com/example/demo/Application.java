package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		MyController myController = new MyController();
		myController.getAllUsers();

		System.out.println("Часть 1:");
		System.out.println(myController.add());
		System.out.println("-----------------");

		System.out.println("Часть 2:");
		System.out.println(myController.updateUser());
		System.out.println("-----------------");

		System.out.println("Часть 3:");
		System.out.println(myController.deleteUser());
		System.out.println("-----------------");
	}

}
