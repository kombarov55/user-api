package com.company.userservice;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
	    migration();
		SpringApplication.run(UserServiceApplication.class, args);
	}

	private static void migration() {
	    Flyway flyway = new Flyway();
	    flyway.setDataSource("jdbc:h2:file:~/test", "root", "root");
        flyway.migrate();
    }

}