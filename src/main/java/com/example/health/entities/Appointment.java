package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
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
    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference("doctor_id - appointment")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference("patient_id - appointment")
    private Patient patient;


    public Appointment() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDateApp() {
        return createDateApp;
    }

    public void setCreateDateApp(Date createDateApp) {
        this.createDateApp = createDateApp;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
