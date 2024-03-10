package com.example.health.dtos;

import jakarta.persistence.Column;

//sa nu lucrez direct cu hospital  fac un obiect ca sa il adaug in baza de date
public class HospitalRequestDTO {
    private String name;
    private String city;
    private String address;
    private String phoneNo;

    public HospitalRequestDTO() {
    }

    public HospitalRequestDTO(String name, String city, String address, String phoneNo) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
