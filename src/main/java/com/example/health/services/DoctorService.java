package com.example.health.services;

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
//    private UserRepository userRepository;
//    private HospitalRepository hospitalRepository;
//    private DoctorRepository doctorRepository;
//
//    //sa adaug un doctor la o lista de doctori dintr un spital
//
//    public Doctor addDoctor(Long id, Hospital hospital) {
//        //caut in baza de date userul dupa id
//        User user = userRepository.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        //caut in baza de date spitalul
//        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));
//        // Creez un nou doctor
//        Doctor doctor = new Doctor();
//        //setez numele doctorului
//        doctor.setName("Nume Doctor");
//        //setez specilitatea
//        doctor.setSpecialty(Specialty.SPECIALTY);
//        //adaug doctorul la spitalul respectiv
//        user.addDoctorToHospital(hospital, doctor);
//        // Salvăm utilizatorul și spitalul în baza de date
//        userRepository.save(user);
//        hospitalRepository.save(hospital);
//        return doctor;
//
//    }
//
//    public Doctor addDoctorToHospital(Hospital hospital, Doctor doctor) {
//
//        //verific daca utilizatorul are o lista de spitale
//        if (this.hospitals == null) {
//            this.hospitals = new ArrayList<>();
//        }
//        // Verificăm userul are acel spital in administrare
//        if (!this.hospitals.contains(hospital)) {
//            throw new IllegalArgumentException("Hospital is not associated with this user.");
//        }
//        // Adăugăm doctorul la lista de doctori a spitalului
//        hospital.getDoctors().add(doctor);
//
//        // Setăm utilizatorul ca fiind utilizatorul asociat spitalului
//        doctor.setUser(this);
//        return doctor;
//    }


}
