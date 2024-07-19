package com.turnero.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.turnero.dto.*;
import com.turnero.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.turnero.component.UserComponent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class UsuariosController {


	private static final Logger log =  LoggerFactory.getLogger(UsuariosController.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private UserComponent userComponent;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest , HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		final String token = this.userManager.autenticar(authenticationRequest, request, response);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authentication", token);
		UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		AtomicReference<String> rol = new AtomicReference<>("");
		userDetails.getAuthorities().forEach(x->{
			rol.set(x.getAuthority());
		});
		if (userDetails.getAuthorities().stream().findFirst().isPresent()){
			log.info(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
		}
		

		return new ResponseEntity<JwtResponse>(new JwtResponse(token,rol.get(), userDetails.getUsername() , Collections.EMPTY_LIST ),responseHeaders,HttpStatus.OK);
	}


	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUser(Authentication auth)
			throws Exception {
		log.info("El usuario en sesion es {} ", auth.getName());
		Set<String> credentials  = new HashSet<String>();
		credentials.add(auth.getCredentials().toString());
		return new ResponseEntity<UserDto>(new UserDto(null,auth.getName(), null, null, null, credentials,null), HttpStatus.OK );
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


	@PostMapping( value = "/registrar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE } )
	public ResponseEntity<Void> registerPersona(@Valid @RequestBody PersonalDto personal) throws  Exception{
			userManager.registrarUsuario(personal);
		log.info("se registro personal ->{} ", personal);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@PutMapping(value =  "/editarPersonal/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Void>  editarPersonal(@PathVariable Integer  id, @RequestBody PersonalDto personal) throws Exception {

			userManager.editarUsuario(id ,personal);

		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@GetMapping(value =  "/eliminarPersonal/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Void>  eliminar(@PathVariable Integer id  ) throws Exception {
		// TODO: 08/05/2024 se deshabilita o se elimina al usuario, terminar
		userManager.apagarUsuario(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}



	@GetMapping(value =  "/buscarPersonal/{username}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<PersonalDto> buscarPersonal(@PathVariable String username ) throws Exception {
		log.info("Se busca personal por usuario ");
		return new ResponseEntity<>(this.userManager.buscarUsuario(username),HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value =  "/listar" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<List<PersonalDto>>  listar() throws Exception {
		log.info("Se invoca listado");
		return new ResponseEntity<List<PersonalDto>>(userManager.listarPersonal(), HttpStatus.OK);
	}

	@GetMapping(value =  "/usuarios" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Page<PersonalDto>> getAllEmployeesV2(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) throws Exception {
		Page<PersonalDto> page = userManager.listarPersonalV2(pageNo,pageSize,sortBy);
		return new ResponseEntity<Page<PersonalDto> >(page, HttpStatus.OK);
	}

	
	
	@GetMapping(value =  "/isEmpty" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Boolean> getAmount(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) throws Exception {
		Boolean isEmpty =  userManager.listarPersonal().isEmpty();
		return new ResponseEntity<Boolean>(isEmpty, HttpStatus.OK);
	}

	
	@GetMapping(value =  "/listarPorNombres" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<List<PersonalAbreviadoDto>>  listarXNombres() throws Exception {
		log.info("Se invoca listado ");
		return new ResponseEntity<List<PersonalAbreviadoDto>>(userManager.listarPersonalNombres(), HttpStatus.OK);
	}
	
	@GetMapping(value =  "/logout" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<?>  logOut(HttpServletRequest servletRequest) throws Exception {
		String username =userComponent.getUser(servletRequest);
		this.userManager.logOut(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value =  "/permisos" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<UserDto>  consultarPermiso(HttpServletRequest servletRequest) throws Exception {
		String username =userComponent.getUser(servletRequest);
		return new ResponseEntity<>(this.userManager.consultarPermiso(username),HttpStatus.OK);
	}
}
