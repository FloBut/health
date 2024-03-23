package com.example.health.controllers;

import com.example.health.dtos.AppointmentRequestDTO;
import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.Appointment;
import com.example.health.entities.Doctor;
import com.example.health.services.DoctorService;
import com.example.health.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }
    @PostMapping("/create")
    public ResponseEntity<Doctor> createDoctor (@RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorRequestDTO));
    }
    @PostMapping(("/{doctorId}/appointment"))
    public ResponseEntity<Appointment> addAppointmentToDoctor(@PathVariable Long doctorId, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addAppointmentToDoctor(doctorId, appointmentRequestDTO));
    }
}
