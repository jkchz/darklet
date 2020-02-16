package com.darklet.eis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EisApplication.class, args);
	}

}
