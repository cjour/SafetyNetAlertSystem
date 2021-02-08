package com.cjour.SafetyNetAlert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cjour.SafetyNetAlert.repository.Database;


@SpringBootApplication
public class SafetyNetAlertSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertSystemApplication.class, args);
		Database db = new Database();
	}
}
