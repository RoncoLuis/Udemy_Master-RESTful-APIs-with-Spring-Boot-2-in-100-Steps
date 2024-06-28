package com.stacksimplify.restservices.springboot_buildingblocks.service;

import com.stacksimplify.restservices.springboot_buildingblocks.entity.User;
import com.stacksimplify.restservices.springboot_buildingblocks.repo.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
 * getAllUsers       - GET    -> /api/users
 * createUser        - POST   -> /api/users
 * getUserById       - GET    -> /api/users/{id}
 * updateUserById    - PUT    -> /api/users/{id}
 * deleteUserById    - DELETE -> /api/users/{id}
 * getUserByUsername - GET    -> /api/users/{username}
 * */
@Service
public class UserService {

    private UserJPARepository userJPARepository;

    @Autowired
    public UserService(UserJPARepository userJPARepository){
        this.userJPARepository=userJPARepository;
    }

    public List<User> getAllUsers(){
        return userJPARepository.findAll();
    }

    public User createUser(User u){
        return userJPARepository.save(u);
    }

    public Optional<User> getUserById(long id){
        //Note: Optional return null in case of not found your object
        //Optional<User> user = userJPARepository.findById(id);
        return userJPARepository.findById(id);
    }

    public User updateUserById(long id, User user){
        //This is no ideal, but the idea is just to overwrite the user object that matches with the id
        user.setId(id);
        return userJPARepository.save(user);
    }

    public void deleteUserById(long id){
        //check if user exist
        //delete user by id (method exist in jpa repository)
        if(userJPARepository.findById(id).isPresent()){
            userJPARepository.deleteById(id);
        }
    }

    public User getUserByUsername(String username){
        return userJPARepository.findByUsername(username);
    }
}
