package com.turnero.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.turnero.entity.User;

import java.util.List;

@Service
public interface UserService {

    public void registrarUsuario(User user)throws Exception;

    public void actualizarUser(User user)throws Exception;

    public List<User> listarUsuario(String role) throws Exception;
    
    public Page<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) throws  Exception;

    public User findByEmail(String email) throws Exception;

    public User findByUserName(String UserName) throws Exception;
    
    public List<User> listarTodos() throws Exception;

    

}
