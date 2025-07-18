package ru.svanchukov.bishop_prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {
		"ru.svanchukov.bishop_prototype",
		"ru.svanchukov.synthetic_human_core_starter"
})

public class BishopPrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BishopPrototypeApplication.class, args);
	}

}
