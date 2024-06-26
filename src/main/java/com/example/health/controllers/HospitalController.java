package com.example.health.controllers;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.dtos.HospitalRequestDTO;
import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/add/hospital/{userId}")
    public ResponseEntity<Hospital> addHospitalToUserId(@PathVariable Long userId,@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addHospitalToUserId(userId, hospitalRequestDTO));
    }
}
