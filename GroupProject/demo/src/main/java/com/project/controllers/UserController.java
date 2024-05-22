package com.project.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.LoginUser;
import com.project.models.Role;
import com.project.models.User;
import com.project.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Allow all origins
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User newUser, BindingResult result) {
        if (newUser.getRoles() == null || newUser.getRoles().isEmpty()) {
            newUser.getRoles().add(Role.STUDENT);
        } else {
            newUser.getRoles().add(Role.INSTRUCTOR);
        }
        
        User registeredUser = userService.register(newUser, result);
        if (registeredUser == null) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUser loginUser, BindingResult result) {
        User user = userService.login(loginUser, result);
        if (user == null) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        String redirectUrl;
        if (user.getRoles().contains(Role.INSTRUCTOR)) {
            redirectUrl = "/api/instructor";
        } else if (user.getRoles().contains(Role.STUDENT)) {
            redirectUrl = "/api/student";
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User has no valid roles.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response);
    }
}
