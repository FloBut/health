package com.example.health.controllers;

import com.example.health.entities.Hospital;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import com.example.health.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @DeleteMapping("/{userId}/hospitals/{hospitalId}")
    public ResponseEntity<Void> removeHospitalFromUser(@PathVariable Long userId, @PathVariable Long hospitalId) {
        boolean removed = userService.removeHospitalFromUser(userId, hospitalId);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
