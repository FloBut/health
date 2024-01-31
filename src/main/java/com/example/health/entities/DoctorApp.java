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

    @ManyToOne
    @JoinColumn(name = "doctorapp_id")
    @JsonBackReference("doctorapp - doctor")
    private Doctor doctor;


    public DoctorApp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(Long appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
