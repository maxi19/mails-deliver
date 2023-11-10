package com.turnero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.turnero.entity.User;
import com.turnero.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public void registrarUsuario(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepo.save(user);
    }

    public void actualizarUser(User user){

    }



}
