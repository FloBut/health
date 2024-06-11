package com.example.health.services;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.dtos.HospitalRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.DoctorRepository;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {
    private HospitalRepository hospitalRepository;
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, UserRepository userRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public Hospital addHospitalToUserId(Long userId, HospitalRequestDTO hospitalRequestDTO) {
        User user = userRepository.findUsersById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequestDTO.getName());
        hospital.setAddress(hospitalRequestDTO.getAddress());
        hospital.setCity(hospitalRequestDTO.getCity());
        hospital.setPhoneNo(hospitalRequestDTO.getPhoneNo());
        hospital.setUser(user);

        List<Hospital> hospitals = user.getHospitals();
        hospitals.add(hospital);
        userRepository.save(user);
        return hospitalRepository.save(hospital);
    }


    @Transactional
    public Hospital addDoctorToHospital(Long hospitalId, DoctorRequestDTO doctorRequestDTO) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));
        Doctor doctor;
        if (doctorRequestDTO.getId() != null) {
            doctor = doctorRepository.findDoctorById(doctorRequestDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        } else {
            doctor = new Doctor();
        }
        doctor.setName(doctorRequestDTO.getName());
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        doctor.setEmail(doctorRequestDTO.getEmail());
        doctor.setPassword(doctor.getEmail());

        doctor = doctorRepository.save(doctor);
        if (!hospital.getDoctors().contains(doctor)) {
            hospital.getDoctors().add(doctor);
        }
        return hospitalRepository.save(hospital);
    }

}
