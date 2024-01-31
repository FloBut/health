package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String name;
// o specialitate poate avea mai multi medici?!
    @Enumerated(EnumType.STRING)
    @Column
    private Specialities specialities;
    //un doctor poate avea mai multe programari
    @OneToMany(mappedBy ="doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("doctorapp - doctor")
    private List<DoctorApp> doctorApps;

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialities getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Specialities specialities) {
        this.specialities = specialities;
    }

    public List<DoctorApp> getDoctorApps() {
        return doctorApps;
    }

    public void setDoctorApps(List<DoctorApp> doctorApps) {
        this.doctorApps = doctorApps;
    }
}
