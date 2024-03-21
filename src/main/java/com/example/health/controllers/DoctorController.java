package com.example.health.controllers;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.services.DoctorService;
import com.example.health.services.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping
    public ResponseEntity <Doctor> createDoctor (@RequestBody DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorService.createDoctor(doctorRequestDTO);
        return ResponseEntity.ok(doctor);
    }
}
