package com.stacksimplify.restservices.springboot_buildingblocks.service;

import com.stacksimplify.restservices.springboot_buildingblocks.entity.User;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserExistException;
import com.stacksimplify.restservices.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springboot_buildingblocks.repo.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public User createUser(User u) throws UserExistException{
        //validate if username already exist
        User usernameExist = userJPARepository.findByUsername(u.getUsername());
        //handle exception if the username already exist
        if(usernameExist != null){
            throw new UserExistException("User already exist in the repository");
        }
        //If the username is not found save it (create)
        return userJPARepository.save(u);
    }

    public Optional<User> getUserById(long id) throws UserNotFoundException {
        Optional<User> user = userJPARepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found - Please provide the correct user id");
        }
        return user;
    }

    public User updateUserById(long id, User user) throws UserNotFoundException{
        Optional<User> optionalUser = userJPARepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found - Please provide the correct user id");
        }
        //This is no ideal, but the idea is just to overwrite the user object that matches with the id
        user.setId(id);
        return userJPARepository.save(user);
    }

    public void deleteUserById(long id) throws UserNotFoundException{
        if(!userJPARepository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found - Please provide the correct user id to delete");
        userJPARepository.deleteById(id);
    }

    public User getUserByUsername(String username){
        return userJPARepository.findByUsername(username);
    }
}
