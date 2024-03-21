package com.example.health.services;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.DoctorRepository;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorService {
   private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public Doctor createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorRequestDTO.getDoctorId());
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        return doctorRepository.save(doctor);
    }



}
