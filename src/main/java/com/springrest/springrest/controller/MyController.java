package com.springrest.springrest.controller;

import com.springrest.springrest.services.UserService;
import com.springrest.springrest.entities.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private UserService userService;
    // or :
    //@RequestMapping(path="/users",method = RequestMethod.GET)
    @GetMapping("/users")
    public List<Users> getUsers()
    {
        return this.userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public Users getUser(@PathVariable String userId)
    {
        return this.userService.getUser(Long.parseLong(userId));
    }

    @PostMapping("/users") // default is also application/json
    public Users addUser(@RequestBody Users user)
    {
        return this.userService.addUser(user);
    }

    @PutMapping("/users")
    public Users updateUsers(Users user)
    {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId)
    {
        try
        {
            this.userService.deleteUser(Long.parseLong(userId));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
