package com.example.health.controllers;

import com.example.health.dtos.HospitalRequestDTO;
import com.example.health.entities.Hospital;
import com.example.health.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hospital")
public class HospitalController {
    //cand ma loghez ca admin as vrea sa pot sa adaug un spital
    private HospitalService hospitalService;
@Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    @PostMapping
    ResponseEntity<Hospital> addHospital(@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.addHospital(hospitalRequestDTO));
    }

}