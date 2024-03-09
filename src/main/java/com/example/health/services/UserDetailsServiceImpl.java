package com.example.health.services;

import com.example.health.dtos.AuthRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.Patient;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(userName).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User (user.getName(), user.getPassword(), getAuthorities(user));

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
    }

    @Transactional
    public List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Doctor) {
            authorities.add(new SimpleGrantedAuthority("DOCTOR"));
        } else if (user instanceof Patient) {
            authorities.add(new SimpleGrantedAuthority("PACIENT"));
        }
        //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
    //@Service
    //public class CustomUserDetailsService implements UserDetailsService {
    //
    //    @Autowired
    //    private UserRepository userRepository;
    //
    //    @Override
    //    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //        User user = userRepository.findByUsername(username);
    //        if (user == null) {
    //            throw new UsernameNotFoundException("User not found with username: " + username);
    //        }
    //
    //        // Determine the role based on the type of user
    //        Set<GrantedAuthority> authorities = new HashSet<>();
    //        if (user instanceof Doctor) {
    //            authorities.add(new SimpleGrantedAuthority("DOCTOR"));
    //        } else if (user instanceof Admin) {
    //            authorities.add(new SimpleGrantedAuthority("ADMIN"));
    //        }
    //        // Add additional roles for other types of users if needed
    //
    //        return new org.springframework.security.core.userdetails.User(
    //            user.getUsername(), user.getPassword(), authorities);
    //    }
    //}
//    @Transactional
//    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user){
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
//                .collect(Collectors.toList());
//    }



}
