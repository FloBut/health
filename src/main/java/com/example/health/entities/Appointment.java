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
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_id - appointment")
    private User user;
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("Doctorapp - appointment")
    private List<DoctorApp> doctorApps;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DoctorApp> getDoctorApps() {
        return doctorApps;
    }

    public void setDoctorApps(List<DoctorApp> doctorApps) {
        this.doctorApps = doctorApps;
    }
}
