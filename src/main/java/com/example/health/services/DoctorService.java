package com.example.health.services;

import com.example.health.dtos.AppointmentRequestDTO;
import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.Appointment;
import com.example.health.entities.Doctor;
import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.AppointmentRepository;
import com.example.health.repositories.DoctorRepository;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
   private DoctorRepository doctorRepository;
   private AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }
    @Transactional
    public Doctor createDoctor(DoctorRequestDTO doctorRequestDTO) {
        //ar trebui sa verific daca doctorul exista deja
        if (doctorRequestDTO.getDoctorId() == null ||  doctorRequestDTO.getSpecialty() == null) {
            throw new IllegalArgumentException("DoctorId and Specialty and UserId must be provided.");
        }
        Doctor doctor = new Doctor();
        doctor.setId(doctorRequestDTO.getDoctorId());
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        return doctorRepository.save(doctor);
    }
    @Transactional
    public Appointment addAppointmentToDoctor(Long doctorId, AppointmentRequestDTO appointmentRequestDTO) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Id not Found"));
        if (appointmentExistsForDoctor(doctorId, appointmentRequestDTO.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment already exists for the doctor.");
        }
        Appointment appointment = new Appointment();
        appointment.setStartDate(appointmentRequestDTO.getStartDate());
        appointment.setEndDate(appointmentRequestDTO.getEndDate());
        appointment.setCreateDateApp(appointmentRequestDTO.getCreateDateApp());
        appointment.setPrice(appointmentRequestDTO.getPrice());

        doctor.getAppointments().add(appointment);;
        appointment.setDoctor(doctor);;

        return appointmentRepository.save(appointment);
    }
    public boolean appointmentExistsForDoctor(Long doctorId, Long appointmentId) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor Id not Found"));
        return doctor.getAppointments().stream()
                .anyMatch(appointment -> appointment.getId().equals(appointmentId));
    }

}
