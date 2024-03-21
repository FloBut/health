//package com.example.health.services;
//
//import com.example.health.dtos.AuthRequestDTO;
//import com.example.health.entities.Doctor;
//import com.example.health.entities.Patient;
//import com.example.health.entities.User;
//import com.example.health.exceptions.ResourceNotFoundException;
//import com.example.health.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//    private UserRepository userRepository;
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepository.findUserByName(userName).orElseThrow(()-> new ResourceNotFoundException("user not found"));
//        String userType = getUserType(user);
//        return new org.springframework.security.core.userdetails.User (user.getName(), user.getPassword(), getAuthorities(user));
//
////        List<GrantedAuthority> authorities = new ArrayList<>();
////        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
//    }
//    private String getUserType(User user) {
//        if (user instanceof Doctor) {
//            return "DOCTOR";
//        } else if (user instanceof Patient) {
//            return "PATIENT";
//        } else {
//            return "USER"; // Default or handle other user types
//        }
//    }
//
//    @Transactional
//    public List<GrantedAuthority> getAuthorities(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (user instanceof Doctor) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));
//        } else if (user instanceof Patient) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_PACIENT"));
//        }
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return authorities;
//    }
//
//
//
//
//}
