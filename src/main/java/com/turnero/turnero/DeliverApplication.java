package com.turnero.turnero;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.turnero.entity.User;
import com.turnero.enums.Role;
import com.turnero.service.UserService;

@SpringBootApplication
@EnableJpaRepositories("com.turnero.repository")
@EntityScan("com.turnero.entity")   
@ComponentScan(basePackages = { "com.turnero.*"})
public class DeliverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverApplication.class, args);
	}

	//habilitar la carga de este usuario en el archivo properties (mock.usuarios)
	@ConditionalOnProperty(name="mock.usuarios")
	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
			userService.registrarUsuario(new User("maxi@gmail.com", "123456", "mrunix",  Role.DOCENTE) );
		};
	}
}
