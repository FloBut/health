package com.example.health.dtos;

import com.example.health.entities.Appointment;
import com.example.health.entities.Review;
import com.example.health.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.List;

public class PatientRequestDTO {
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private Date dateOfBirth;

    public PatientRequestDTO() {
    }

    public PatientRequestDTO(String name, String address, String phoneNo, String email, Date dateOfBirth) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

//{
    //    "id": 8,
    //    "name": null,
    //    "password": null,
    //    "hospitals": null,
    //    "dateOfBirth": "1990-01-01",
    //    "address": "address1",
    //    "appointments": null,
    //    "reviews": null
    //}
}
