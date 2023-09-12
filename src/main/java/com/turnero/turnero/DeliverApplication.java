package com.turnero.turnero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.turnero")
@EntityScan("com.turnero.entity")   
@ComponentScan(basePackages = { "com.turnero.*" })
public class DeliverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverApplication.class, args);
	}

}
