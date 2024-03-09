package com.example.health.controllers;

import com.example.health.dtos.AuthRequestDTO;
import com.example.health.services.UserDetailsServiceImpl;
import com.example.health.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/authenticate")
        public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(userService.authenticate(authRequestDTO));

    }
}
