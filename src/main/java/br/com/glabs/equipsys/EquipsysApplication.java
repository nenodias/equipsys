package br.com.glabs.equipsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class EquipsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipsysApplication.class, args);
	}

}
