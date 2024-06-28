package com.stacksimplify.restservices.springboot_buildingblocks.controller;
/*
* getAllUsers       - GET    -> /api/users
* createUser        - POST   -> /api/users
* getUserById       - GET    -> /api/users/{id}
* updateUserById    - PUT    -> /api/users/{id}
* deleteUserById    - DELETE -> /api/users/{id}
* getUserByUsername - GET    -> /api/users/{username}
* */
import com.stacksimplify.restservices.springboot_buildingblocks.entity.User;
import com.stacksimplify.restservices.springboot_buildingblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    /*the name in @PathVariable("id") is not required if the parameter has the same name as the argument*/
    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    // 1. Take id from the @PathVariable
    // 2. Take the user from the @RequestBody
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") long id, @RequestBody User user){
        return userService.updateUserById(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
}
