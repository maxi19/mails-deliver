package com.turnero.manager;


import com.turnero.dto.JwtRequest;
import com.turnero.dto.PersonalDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserManager {

    public String autenticar(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response  ) throws Exception;

    public void registrarUsuario(PersonalDto personalDto) throws Exception;

    public void editarUsuario(Integer id,PersonalDto personalDto) throws Exception;

    public void apagarUsuario(Integer id) throws Exception;

    public PersonalDto buscarUsuario(String username) throws Exception;

    public List<PersonalDto> listarPersonal() throws  Exception;

    public Page<PersonalDto> listarPersonalV2(Integer pageNo, Integer pageSize, String sortBy ) throws Exception;
}
