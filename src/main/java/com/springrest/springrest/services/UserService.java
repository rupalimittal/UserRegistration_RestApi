package com.springrest.springrest.services;

import com.springrest.springrest.entities.Users;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public List<Users> getUsers();
    public Users getUser(Long userId);
    public Users addUser(Users user);
    public Users updateUser(Users user);
    public void deleteUser(Long userId);



}
