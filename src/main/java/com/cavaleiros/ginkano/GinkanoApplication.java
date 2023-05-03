package com.cavaleiros.ginkano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class GinkanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GinkanoApplication.class, args);
	}

}
