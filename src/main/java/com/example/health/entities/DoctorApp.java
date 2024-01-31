package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class DoctorApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long appointmentNo;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    @JsonBackReference("doctorapp - appointment")
    private Appointment appointment;



}
