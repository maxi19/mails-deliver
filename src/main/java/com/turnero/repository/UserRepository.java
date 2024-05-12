package com.turnero.repository;

import com.turnero.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.turnero.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends PagingAndSortingRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.rol = ?1")
    public Optional<List<User>> findByRol(Role rol);

}
