package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long appointmentNo;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private Date createDateApp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_id - appointment")
    private User user;

    //o programare se poate face pentru un doctor sau o lista de doctori
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("doctor - appointment")
    private List<Doctor> doctors;

    //o programare poate avea o lista de doctori - programari
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("DoctorApp - appointment")
    private List<DoctorApp> appointments;





}
