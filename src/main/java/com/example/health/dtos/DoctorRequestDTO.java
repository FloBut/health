package com.example.health.dtos;

import com.example.health.entities.Specialty;

public class DoctorRequestDTO {
    Long doctorId;
    private Specialty specialty;

    public DoctorRequestDTO() {
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
}
