package com.example.health.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class AppointmentRequestDTO {
    private Long patientId;
    private Long appointmentId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDateApp;
    private Double price;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Long patientId, Long appointmentId, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createDateApp, Double price) {
        this.patientId = patientId;
        this.appointmentId= appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDateApp = createDateApp;
        this.price = price;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreateDateApp() {
        return createDateApp;
    }

    public void setCreateDateApp(LocalDateTime createDateApp) {
        this.createDateApp = createDateApp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
