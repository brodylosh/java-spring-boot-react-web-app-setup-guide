package com.setup.spring.boot.react.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootReactWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactWebAppApplication.class, args);
	}

	@GetMapping("/hello")
	public List<String> hello() {
		return List.of("Hello", "World");
	}

}
