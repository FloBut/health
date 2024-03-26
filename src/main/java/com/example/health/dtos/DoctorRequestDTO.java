package com.example.health.dtos;

import com.example.health.entities.Specialty;

public class DoctorRequestDTO {
    private Long doctorId;
    private Specialty specialty;


    public DoctorRequestDTO(Long doctorId, Specialty specialty) {
        this.doctorId = doctorId;
        this.specialty = specialty;
    }


    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    //    "id": 7,
    //    "name": null,
    //    "password": null,
    //    "hospitals": null,
    //    "appointments": null,
    //    "reviews": null,
    //    "availabilityDocs": null,
    //    "specialities": null
}
