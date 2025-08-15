package com.snownilight.backtowork.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snownilight.backtowork.common.ApiResponse;
import com.snownilight.backtowork.model.po.Users;
import com.snownilight.backtowork.service.UserService;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping
    public ApiResponse<Users> updateUser(@RequestBody Users user) {
        Optional<Users> updatedUser = userService.updateUser(user);
        return updatedUser.isPresent() ? ApiResponse.success("User updated successfully", updatedUser.get()) : ApiResponse.error(200,"User not found");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id) ? ApiResponse.success("User deleted successfully", null) : ApiResponse.error(200, "User not found");
    }
}