package com.example.health.services;

import com.example.health.dtos.AuthRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
@Autowired
    public UserService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //fac autentificarea parola din postaman trebuie sa fie parola encriptata din baza de date
    @Transactional
    public String authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getName(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getName());
        return jwtTokenService.generateToken(userDetails);
    }
    //vreau sa ma inregisterz pe baza userului si a parolei
    @Transactional
    public User register(AuthRequestDTO authRequestDTO) {
        //imi caut userul in baza de date
        Optional<User> userOptional = userRepository.findUserByName(authRequestDTO.getName());
        if (userOptional.isPresent()) {
            throw new ResourceNotFoundException("User is already registered");
        }
        User user = new User();
        //as trebui sa fac verific daca userul este doctor sau pacient dar nu am tipul userului
        //if (authRequestDTO.getUserType.eqauls("Doctor")) {
            //Doctor doctor = new Doctor();
            // newDoctor.setSpecialties(authRequestDTO.getSpecialty());
            // user = newDoctor;
        //} else {
            //Patient newPatient = new Patient();
            //Set patient-specific fields
            //newPatient.setDateOfBirth(authRequestDTO.getDateOfBirth());
            //newPatient.setAddress(authRequestDTO.getAddress());
            //  user = newPatient;
        //}
        user.setName(authRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        return userRepository.save(user);
    }

}
