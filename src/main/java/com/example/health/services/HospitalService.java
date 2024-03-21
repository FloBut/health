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
    public Hospital addHospitalToUserId (Long userId, HospitalRequestDTO hospitalRequestDTO) {
//        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findUserByName(loggedInUserName).orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
    public Hospital addDoctorToHospital(Long hospitalId, DoctorRequestDTO doctorRequestDTO) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));
        Doctor doctor = new Doctor();
        doctor.setId(doctorRequestDTO.getDoctorId());
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        boolean doctorExists = hospital.getDoctors().stream()
                .anyMatch(d -> d.getId().equals(doctorRequestDTO.getDoctorId()));
        if (!doctorExists) {
            doctor = doctorRepository.save(doctor);
            if (hospital.getDoctors() == null) {
                hospital.setDoctors(new ArrayList<>());
            }
            hospital.getDoctors().add(doctor);
            return hospitalRepository.save(hospital);
        } else {
            throw new RuntimeException("Doctor already exists in the hospital.");
        }
    }
}
