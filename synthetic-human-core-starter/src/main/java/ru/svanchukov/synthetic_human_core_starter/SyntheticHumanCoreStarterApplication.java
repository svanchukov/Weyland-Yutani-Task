package ru.svanchukov.synthetic_human_core_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SyntheticHumanCoreStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyntheticHumanCoreStarterApplication.class, args);
	}

}
