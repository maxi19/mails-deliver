package com.turnero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turnero.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	
	public JwtUserDetailsService(UserRepository userRepository  ) {
		this.userRepo = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		UserDetails user =	userRepo.buscarPorUsuario(username);

		if (user == null){
			 user = userRepo.findByEmail(username);
		}

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	        return user;
	}


}