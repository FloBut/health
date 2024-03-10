package com.example.health.services;

import com.example.health.dtos.HospitalRequestDTO;
import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {
    private HospitalRepository hospitalRepository;
    private UserRepository userRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, UserRepository userRepository) {
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Hospital addHospitalToUserId (HospitalRequestDTO hospitalRequestDTO) {
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByName(loggedInUserName).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequestDTO.getName());
        hospital.setAddress(hospitalRequestDTO.getAddress());
        hospital.setCity(hospitalRequestDTO.getCity());
        hospital.setPhoneNo(hospitalRequestDTO.getPhoneNo());

        List<Hospital> hospitals = user.getHospitals();
        hospitals.add(hospital);
        userRepository.save(user);
        return hospital;
    }
}
