package com.example.health.services;

import com.example.health.entities.Doctor;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.DoctorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DoctorUserDetailsServiceImpl implements UserDetailsService {
    private DoctorRepository doctorRepository;
    @Override
    public UserDetails loadUserByUsername(String doctorName) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findDoctorByName(doctorName).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return null; //new org.springframework.security.core.userdetails.User(doctor.getName(),doctor.getPassword(), buildSimpleGrantedAuthorities(user))
    }
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        return authorities;
    }
}
