package com.example.health.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @OneToMany (mappedBy = "availability", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvailabilityDoc> availabilityDocs;
    public Availability() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<AvailabilityDoc> getAvailabilityDocs() {
        return availabilityDocs;
    }

    public void setAvailabilityDocs(List<AvailabilityDoc> availabilityDocs) {
        this.availabilityDocs = availabilityDocs;
    }
}
