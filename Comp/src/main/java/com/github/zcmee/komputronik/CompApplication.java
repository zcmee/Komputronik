package com.github.zcmee.komputronik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CompApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompApplication.class, args);
	}
}
