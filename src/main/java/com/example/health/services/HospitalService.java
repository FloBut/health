package com.example.health.services;

import com.example.health.dtos.HospitalRequestDTO;
import com.example.health.entities.Hospital;
import com.example.health.repositories.HospitalRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalService {
    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Hospital addHospital(HospitalRequestDTO hospitalRequestDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequestDTO.getName());
        hospital.setAddress(hospitalRequestDTO.getAddress());
        hospital.setCity(hospitalRequestDTO.getCity());
        hospital.setPhoneNo(hospitalRequestDTO.getPhoneNo());
        return hospitalRepository.save(hospital);
    }
}
