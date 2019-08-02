package com.deportes.deportes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
//@ComponentScan({"com.deportes.deportes_api.cargadores"})
//@EntityScan("com.deportes.deportes_api.tablas")
//@EnableJpaRepositories({"com.deportes.deportes_api.repositorios","com.deportes.deportes_api.controller"})
public class DeportesApiApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DeportesApiApplication .class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DeportesApiApplication.class, args);
	}
}
