package com.sparta.assignment1profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Assignment1ProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1ProfileApplication.class, args);
	}

}
