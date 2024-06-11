package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
    @Column
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonManagedReference("appointment - doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference("review - doctor")
    private List<Review> reviews;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public void setAvailabilityDocs(List<AvailabilityDoc> availabilityDocs) {
        this.availabilityDocs = availabilityDocs;

    }
}
