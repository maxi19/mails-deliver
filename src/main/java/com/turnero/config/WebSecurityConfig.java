package com.turnero.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private CustomBearerTokenAccessDeniedHandler CustomBearerTokenAccessDeniedHandler;

	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {


		httpSecurity.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/usuarios/listar").permitAll()
				.antMatchers("/usuarios/logout").permitAll()
				.antMatchers("/usuarios/authenticate").permitAll()
				.antMatchers("/usuarios/user").permitAll()
				.antMatchers("/usuarios/usuarios").permitAll()
				.antMatchers("/usuarios/registrar").permitAll()
				.antMatchers("/usuarios/permisos").permitAll()
				.antMatchers("/usuarios/listarPorNombres").permitAll()
				.antMatchers("/email/enviar").permitAll()
				.antMatchers("/upload").permitAll()
				.antMatchers("/files**").permitAll()
				.antMatchers("/delete/**").permitAll()
				.antMatchers("/recibos/procesarArchivos").permitAll()
				.antMatchers("/recibos/bandeja").permitAll()

				.anyRequest().authenticated()
				.and()

				.httpBasic();

		httpSecurity.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);

	}
}