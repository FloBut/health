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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
   private DoctorRepository doctorRepository;
   private AppointmentRepository appointmentRepository;
   private UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public Doctor createDoctor(DoctorRequestDTO doctorRequestDTO) {
        //ar trebui sa verific daca doctorul exista deja
        Doctor doctor = new Doctor();
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        return doctorRepository.save(doctor);
    }
    @Transactional
    public Appointment addAppointmentToDoctor(Long doctorId, AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Id not Found"));
        if (appointmentExistsForDoctor(doctorId, appointmentRequestDTO.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment already exists for the doctor.");
        }
        LocalDateTime startDateTime = appointmentRequestDTO.getStartDate();
        LocalDateTime endDateTime = startDateTime.plusMinutes(30);
        if (!startDateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date must be after the current date.");
        }
        LocalDateTime expectedEndDateTime = startDateTime.plusMinutes(30);
        if (!endDateTime.isAfter(expectedEndDateTime)) {
            throw new IllegalArgumentException("End date must be after 30 minutes of the start date.");
        }
        appointment.setStartDate(appointmentRequestDTO.getStartDate());
        appointment.setEndDate(endDateTime);
        appointment.setCreateDateApp(LocalDateTime.now());
        appointment.setPrice(appointmentRequestDTO.getPrice());

        doctor.getAppointments().add(appointment);;
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }
    public boolean appointmentExistsForDoctor(Long doctorId, Long appointmentId) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor Id not Found"));
        return doctor.getAppointments().stream()
                .anyMatch(appointment -> appointment.getId().equals(appointmentId));
    }

}
