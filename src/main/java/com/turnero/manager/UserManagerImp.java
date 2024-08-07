package com.turnero.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.turnero.config.JwtTokenUtil;
import com.turnero.dto.JwtRequest;
import com.turnero.dto.PersonalAbreviadoDto;
import com.turnero.dto.PersonalDto;
import com.turnero.dto.UserDto;
import com.turnero.entity.Docente;
import com.turnero.entity.User;
import com.turnero.enums.Role;
import com.turnero.exceptions.DeliverException;
import com.turnero.redis.Session;
import com.turnero.redis.SessionDao;
import com.turnero.service.UserService;
import com.turnero.utils.RamdomNumber;

@Service
public class UserManagerImp implements UserManager {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SessionDao sessionDao;

    private static final Logger log =  LoggerFactory.getLogger(UserManagerImp.class);

    @Autowired
    private ModelMapper modelMapper;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public String autenticar(JwtRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response    ) throws Exception {

        Authentication autenteication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());


        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        try {
            User user =  userService.findByEmail(authenticationRequest.getUsername());
        }catch (Exception e){
            log.info("se va buscar por email");
            userService.findByUserName(authenticationRequest.getUsername());
        }


        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(autenteication);
        SecurityContextHolder.setContext(context);


        //persistimos datos en session
        User user =  userService.findByUserName(authenticationRequest.getUsername());
        if (user == null) {
			user = userService.findByEmail(authenticationRequest.getUsername());
		}

        sessionDao.saveSession(new Session(user.getUsername(), user.getFolderEntrada(), user.getFolderBandeja(), user.getRol()));

        return token;

    }

    @Override
    public void registrarUsuario(UserDto userDto) throws Exception {
        this.userService.registrarUsuario(new User(userDto.getEmail(),
                userDto.getUsername(),"", userDto.getFirstName(),userDto.getLastName(), Role.getRole(userDto.getRol()) ));
    }

    @Override
    public void editarUsuario( Integer id ,PersonalDto personalDto) throws Exception {

        User user = this.userService.findByUserName(personalDto.getUsername());
        String nombres = personalDto.getNombres();
        String apellidos = personalDto.getApellidos();
        String patron = apellidos.concat(",").concat(nombres).concat("_").concat(".+").concat(".pdf").replaceAll(" ", "").toUpperCase();
        user.setFirstName(nombres);
        user.setFirstName(apellidos);
        userService.actualizarUser(user);
        log.info("se actualizo usuario {}",user);
    }

    @Override
    public void apagarUsuario(Integer id) throws Exception {

    }

    @Override
    public UserDto buscarUsuario(String username) throws Exception {
        User  user =  this.userService.findByUserName(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, user);
        return userDto;
    }

    @Override
    public List<PersonalDto> listarPersonal() throws Exception {

        List<User> usuarios =  this.userService.listarUsuario(Role.SECRETARIA.rol);
        List<PersonalDto> usuariosDto = new ArrayList<>();

        for(User user: usuarios){
            PersonalDto dto = new PersonalDto();
            dto.setNombres( user.getFirstName());
            dto.setApellidos(user.getLastName());
            dto.setEmail(user.getEmail());
            usuariosDto.add(dto);
            }

        return usuariosDto;
    }

    @Override
    public Page<PersonalDto> listarPersonalV2(Integer pageNo, Integer pageSize, String sortBy ) throws Exception {
        Page<User> page = this.userService.getAllUsers(pageNo, pageSize, sortBy);
        Page<PersonalDto>  pageDto = page.map(new Function<User, PersonalDto>() {

            @Override
            public PersonalDto apply(User user) {
                PersonalDto personalDto = new PersonalDto();
                personalDto.setApellidos(user.getLastName());
                personalDto.setNombres(user.getFirstName());
                personalDto.setEmail(user.getEmail());
                personalDto.setUsername(user.getUsername());
                return personalDto;
            }
        });

        return pageDto;
    }




    private Authentication authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Se autentico usuario {} ", username);
            return authentication;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new DeliverException("Credencial invalida", e);
        }
    }

	@Override
	public List<PersonalAbreviadoDto> listarPersonalNombres() throws Exception {
		List<PersonalAbreviadoDto> personal = new ArrayList<>();
		userService.listarTodos().forEach( p -> {
			PersonalAbreviadoDto dto = this.modelMapper.map(p, PersonalAbreviadoDto.class);
			dto.completarNombre();
			personal.add(dto);
		});

		return personal;
	}

	@Override
	public void logOut(String username) throws Exception {
		sessionDao.deleteSession(username);
	}

	@Override
	public UserDto consultarPermiso(String username) throws Exception {
		if (sessionDao.existeSession(username)) {

		     List<String> scopes  = sessionDao.getOneSession(username).getScopes();
		     String usuario = sessionDao.getOneSession(username).getUsuario();
		     UserDto userDto = new UserDto();

		     userDto.setScopes(scopes);
		     userDto.setUsername(usuario);

			return userDto;

		}
		throw new DeliverException("permiso denegado");
	}

}
