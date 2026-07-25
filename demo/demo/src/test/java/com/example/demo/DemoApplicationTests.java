package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
class DemoApplicationTests {

	public static void main (String[] args){
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/helo")
	public String helo(@RequestParam(value = "name", defaultValue = "Word") String name) {
		return String.format("Hello %s!", name);
	}

}
