package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.models.User;


@SpringBootApplication()
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	@Bean(name = "USER_BEAN)")
	public User setUser() {
		User u = new User();
		
		u.setUsername("admin");
		u.setPassword("admin123");
		return u;
	}
	
}
