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
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserExistException;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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

    //UriComponentsBuilder it's an injectable helper class used to construct URIs
    //This object (HttpHeaders) is used to store additional headers that will be sent along with the HTTP response.
    //"Location" header informs the client about the location of the newly created resource.
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder){
        try{
            userService.createUser(user);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
        }catch(UserExistException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    /*the name in @PathVariable("id") is not required if the parameter has the same name as the argument*/
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        try{
            return userService.getUserById(id);
        }catch(UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

    }

    // 1. Take id from the @PathVariable
    // 2. Take the user from the @RequestBody
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") long id, @RequestBody User user){
        try{
            return userService.updateUserById(id,user);
        }catch(UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") long id) throws UserNotFoundException {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
}
