package com.example.health.services;

import com.example.health.dtos.AppointmentRequestDTO;
import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.*;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.*;
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
   private PatientRepository patientRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    @Transactional
    public Doctor createDoctor(DoctorRequestDTO doctorRequestDTO) {
        //ar trebui sa verific daca doctorul exista deja
        Doctor doctor = new Doctor();
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Appointment addAppointmentToDoctor(AppointmentRequestDTO appointmentRequestDTO) {
        Patient patient = patientRepository.findPatientById(appointmentRequestDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Id not found"));
        //verific in lista de programari a doctorului daca programarea exista deja

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

        //TODO cauat pacientu dupa pacientID din dto si il setezi la appointemnt
        appointment.setStartDate(appointmentRequestDTO.getStartDate());
        appointment.setEndDate(endDateTime);
        appointment.setCreateDateApp(LocalDateTime.now());
        appointment.setPrice(appointmentRequestDTO.getPrice());


        doctor.getAppointments().add(appointment);;
        appointment.setDoctor(doctor);
        //TODO send mail cu detaliile programarii apcientului si mail si la doctor
        //mailService.sendMail(apointment);


        return appointmentRepository.save(appointment);
    }
    public boolean appointmentExistsForDoctor(Long doctorId, Long appointmentId) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor Id not Found"));
        return doctor.getAppointments().stream()
                .anyMatch(appointment -> appointment.getId().equals(appointmentId));
    }

}
