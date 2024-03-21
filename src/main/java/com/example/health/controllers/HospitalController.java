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
    //cand ma loghez ca admin as vrea sa pot sa adaug un spital
    private HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Hospital> addHospitalToUserId(@PathVariable Long userId,@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addHospitalToUserId(userId, hospitalRequestDTO));
    }
    @PostMapping("/add/{doctor}")
    public ResponseEntity<Hospital> addDoctorToHospital(@PathVariable Long hospitalId, @RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addDoctorToHospital(hospitalId, doctorRequestDTO));
    }
}
