package com.intercorp.ms1.MS01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class EjercicioPracticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioPracticoApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper () {
		return new ObjectMapper();
	}
}
