package com.turnero.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.Collection;
import com.turnero.config.JwtTokenUtil;
import com.turnero.dto.JwtRequest;
import com.turnero.dto.JwtResponse;
import com.turnero.dto.UserDto;
import com.turnero.entity.Personal;


@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static final Logger log =  LoggerFactory.getLogger(JwtController.class);

	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authentication",
				token);

		return new ResponseEntity<JwtResponse>(new JwtResponse(token),responseHeaders,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			log.info("Se autentico usuario {} ", username);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUser(Authentication auth)
			throws Exception {
		log.info("El usuario en sesion es {} ", auth.getName());
		Set<String> credentials  = new HashSet<String>();
		credentials.add(auth.getCredentials().toString());
		return new ResponseEntity<UserDto>(new UserDto(null,auth.getName(), null, null, null, credentials), HttpStatus.OK );
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(UserDto userDto)
			throws Exception {
		
		//log.info("El usuario tiene los roles {} ", auth.getCredentials());
		
		return new ResponseEntity<> (null, HttpStatus.OK );
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Object> getCredentials(Authentication auth)
			throws Exception {
		
		log.info("El usuario tiene los roles {} ", auth.getCredentials());
		
		return new ResponseEntity<> (auth.getCredentials(), HttpStatus.OK );
	}
	
	@GetMapping(value =  "/roles-test" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<?>  getRoles(Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		return new ResponseEntity<>(userDetails.getAuthorities(), HttpStatus.OK);
	}
	
	
}
