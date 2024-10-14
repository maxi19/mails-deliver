package com.turnero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.turnero.entity.User;
import com.turnero.enums.Role;
import com.turnero.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepo){
        this.userRepository = userRepo;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public void registrarUsuario(User user)throws Exception{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
        logger.info("se registro personal ->{} ", user);
    }

    @Override
    public void actualizarUser(User user)throws Exception{
       // userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> listarUsuario(String role) throws Exception {
            Optional<List<User>> usuarios  = userRepository.findByRol(Role.SECRETARIA);
            return usuarios.get();
    }




    @Override
    public Page<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) throws  Exception{

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return  userRepository.findAll(paging);
    }


    @Override
    public User findByEmail(String email) throws Exception {
       return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserName(String UserName) throws Exception {
        return userRepository.buscarPorUsuario(UserName);
    }

	@Override
	public List<User> listarTodos() throws Exception {
		List<User> usuarios = new ArrayList<>();
		userRepository.findAll().forEach(x ->{
			usuarios.add(x);
		});
		return usuarios;
	}



}
