package br.com.glabs.equipsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories
@EnableSpringDataWebSupport
@SpringBootApplication
public class EquipsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipsysApplication.class, args);
	}

}
