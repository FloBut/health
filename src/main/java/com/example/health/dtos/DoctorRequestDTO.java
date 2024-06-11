package com.example.health.dtos;

import com.example.health.entities.Specialty;

import javax.swing.plaf.TableHeaderUI;

public class DoctorRequestDTO {
    private Long Id;
    private Specialty specialty;  //daca clasa doctor extinde clasa user care are deja un field nume ar trebui sa aduag in DTO si acest camp sau nu ?

    private String name;
    private String password;
    private String email;

    public DoctorRequestDTO() {
    }

    public DoctorRequestDTO(Long Id, Specialty specialty, String name, String password, String email) {
        this.Id = Id;
        this.specialty = specialty;
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
