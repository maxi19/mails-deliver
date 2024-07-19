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


	@ConditionalOnProperty(name="mock.usuarios")
	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
				return args -> {
					userService.registrarUsuario(new User("maximilianoguzman@fatimarem.edu.ar", "admin", "123456", "maxi","guzman", Role.ADMIN,"/maxi-base","/maxi-bandeja"));
					userService.registrarUsuario(new User("ivan@gmail.com", "secretaria","123456", "ivan","apaza", Role.SECRETARIA,"/secretaria-base","/secretaria-bandeja"));
					userService.registrarUsuario(new User("carola@gmail.com", "carola", "123456","carola","rojas", Role.DOCENTE,"/user-base","/user-bandeja"));

		};
	}




}
