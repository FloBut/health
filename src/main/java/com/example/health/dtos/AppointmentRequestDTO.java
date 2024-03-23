package com.example.health.dtos;

import jakarta.persistence.Column;

import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class AppointmentRequestDTO {
    private Long appointmentId;
    private Date startDate;
    private Date endDate;
    private Date createDateApp;
    private Double price;

    public AppointmentRequestDTO(Long appointmentId, Date startDate, Date endDate, Date createDateApp, Double price) {
        this.appointmentId= appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDateApp = createDateApp;
        this.price = price;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
