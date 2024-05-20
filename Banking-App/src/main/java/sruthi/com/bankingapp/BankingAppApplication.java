package sruthi.com.bankingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to the Banking Application!");
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
