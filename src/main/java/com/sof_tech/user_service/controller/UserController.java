package com.sof_tech.user_service.controller;

import com.sof_tech.user_service.model.User;
import com.sof_tech.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping("/save-user")
    public ResponseEntity<?> creatingUser(@RequestBody User user) {
        User user1=this.userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    //single user get

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        User userById = this.userService.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.FOUND);
    }

    //All-User get
    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser() {
        List<User> allUser = this.userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.FOUND);

    }


    //Update User by UserId



    //Delete User
}
