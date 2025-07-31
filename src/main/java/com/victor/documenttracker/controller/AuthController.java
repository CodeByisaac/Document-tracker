package com.victor.documenttracker.controller;

import com.victor.documenttracker.dto.LoginRequest;
import com.victor.documenttracker.dto.RegisterRequest;
import com.victor.documenttracker.service.UserService;
import com.victor.documenttracker.model.User;
import com.victor.documenttracker.repository.UserRepository;
import com.victor.documenttracker.security.JwtUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth") //base path for login routes
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public String login(@RequestBody LoginRequest loginRequest){
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()){
            User user = userOptional.get();

            if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
                return jwtUtil.generateToken(user.getUsername());
            }

        }
        return "Invalid username or password";

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
