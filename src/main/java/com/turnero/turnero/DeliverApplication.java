package com.turnero.turnero;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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

	@ConditionalOnProperty(name="mock.usuarios")
	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
				return args -> {
					userService.registrarUsuario(new User("maximilianoguzman@fatimarem.edu.ar", "123456", "admin", Arrays.asList(Role.ADMIN)));
					userService.registrarUsuario(new User("secretario@gmail.com", "123456", "secretario", Arrays.asList(Role.SECRETARIO)));
					userService.registrarUsuario(new User("usuario@gmail.com", "123456", "usuario", Arrays.asList(Role.USER)));

		};
	}
	

}
