package com.turnero.manager;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import com.turnero.dto.JwtRequest;
import com.turnero.dto.PersonalAbreviadoDto;
import com.turnero.dto.PersonalDto;
import com.turnero.dto.UserDto;

public interface UserManager {

    public String autenticar(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response  ) throws Exception;

    public void registrarUsuario(UserDto userDto) throws Exception;

    public void editarUsuario(Integer id,PersonalDto personalDto) throws Exception;

    public void apagarUsuario(Integer id) throws Exception;

    public UserDto buscarUsuario(String username) throws Exception;

    public List<PersonalDto> listarPersonal() throws  Exception;

    public Page<PersonalDto> listarPersonalV2(Integer pageNo, Integer pageSize, String sortBy ) throws Exception;

    public List<PersonalAbreviadoDto> listarPersonalNombres() throws  Exception;

    public void logOut(String username) throws  Exception;

    public UserDto consultarPermiso(String username) throws  Exception;
}
