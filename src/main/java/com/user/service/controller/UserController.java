package com.user.service.controller;

import com.user.service.entities.User;
import com.user.service.service.UserService;
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

    // Add User
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // If user is null, return 400 BAD REQUEST
        }

        try {
            User savedUser = userService.addUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Return 201 CREATED on success
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle exception and return 500 INTERNAL SERVER ERROR
        }
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no users found
            }
            return new ResponseEntity<>(users, HttpStatus.OK); // Return 200 OK with the list of users
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if exception occurs
        }
    }

    // Get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Long userId) {
        try {
            User user = userService.getUserByUserId(userId);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user not found
            }
            return new ResponseEntity<>(user, HttpStatus.OK); // Return 200 OK with the user
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if exception occurs
        }
    }

    // Delete User by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUserByUserId(@PathVariable Long userId) {
        try {
            User deletedUser = userService.deleteUserByUserId(userId);
            if (deletedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user not found
            }
            return new ResponseEntity<>(deletedUser, HttpStatus.OK); // Return 200 OK with the deleted user
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if exception occurs
        }
    }
}
