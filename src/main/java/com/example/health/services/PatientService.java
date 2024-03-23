package com.example.health.services;

import com.example.health.dtos.PatientRequestDTO;
import com.example.health.entities.Patient;
import com.example.health.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
@Transactional
    public Patient createPatient (PatientRequestDTO patientRequestDTO) {
//ar trebui sa verific daca pacientul exista deja
        Patient patient = new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        return patientRepository.save(patient);


    }

}
