package com.guadgarcia.ollama;

import org.springframework.boot.SpringApplication;

public class TestOllamaApplication {

	public static void main(String[] args) {
		SpringApplication.from(OllamaApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
