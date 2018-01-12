package com.example.kotryn.controller;

import com.example.kotryn.entity.request.AddUserRequest;
import com.example.kotryn.entity.User;
import com.example.kotryn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewWebUser(@RequestBody AddUserRequest addUserRequest) {
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setSurname(addUserRequest.getSurname());
        user.setAge(addUserRequest.getAge());
        userRepository.save(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addNewListOfWebUsers(@RequestBody List<User> addUsersRequest) {
        for (User addUser : addUsersRequest) {
            System.out.println(addUsersRequest);
            User user = new User();
            user.setName(addUser.getName());
            user.setSurname(addUser.getSurname());
            user.setAge(addUser.getAge());
            userRepository.save(user);
        }
    }
}
