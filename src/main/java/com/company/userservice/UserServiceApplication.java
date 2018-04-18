package com.company.userservice;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("com.company.userservice")
@Configuration
public class UserServiceApplication {

	public static void main(String[] args) {
	    migration();
	    SpringApplication.run(UserServiceApplication.class, args);
	}

	private static void migration() {
	    Flyway flyway = new Flyway();
	    flyway.setDataSource("jdbc:h2:file:~/user", "root", "root");
            flyway.migrate();
    }

}
