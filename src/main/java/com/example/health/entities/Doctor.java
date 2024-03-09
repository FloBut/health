package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
    @Column
    private Specialty specialty;
    //un doctor poate avea mai multe programari
//un user are deja one to many cu spitalul
//    @ManyToMany(mappedBy = "doctors")
//    @JsonBackReference
//    private List<Hospital> hospitals;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.ALL})
    @JsonManagedReference("appointment - doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference("review - doctor")
    private List<Review> reviews;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvailabilityDoc> availabilityDocs;

    public Doctor() {

        super();
    }
    public Specialty getSpecialities() {
        return specialty;
    }

    public void setSpecialities(Specialty specialities) {
        this.specialty = specialty;
    }


    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<AvailabilityDoc> getAvailabilityDocs() {
        return availabilityDocs;
    }

    public void setAvailabilityDocs(List<AvailabilityDoc> availabilityDocs) {
        this.availabilityDocs = availabilityDocs;
    }
}
