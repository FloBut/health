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
        private Date dateOfBirth;
        private String address;


    public PatientRequestDTO(Date dateOfBirth, String address) {
        this.dateOfBirth = dateOfBirth;
        this.address = address;
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
