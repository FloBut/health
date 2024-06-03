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
   private HospitalRepository hospitalRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    @Transactional
    public Doctor createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();
        doctor.setSpecialities(doctorRequestDTO.getSpecialty());
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Appointment addAppointmentToDoctor(AppointmentRequestDTO appointmentRequestDTO, Long doctorId) {
        Patient patient = patientRepository.findPatientById(appointmentRequestDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Id not found"));        Appointment appointment = new Appointment();
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Id not Found"));
        if (appointmentExistsForDoctor(doctorId, appointmentRequestDTO.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment already exists for the doctor.");
        }
        LocalDateTime startDateTime = appointmentRequestDTO.getStartDate();
        LocalDateTime endDateTime = startDateTime.plusMinutes(30);
        if (!startDateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date must be after the current date.");
        }
        appointment.setStartDate(appointmentRequestDTO.getStartDate());
        appointment.setEndDate(endDateTime);
        appointment.setCreateDateApp(LocalDateTime.now());
        appointment.setPrice(appointmentRequestDTO.getPrice());
        appointment.setPatient(patient);
        doctor.getAppointments().add(appointment);;
        appointment.setDoctor(doctor);
        //TODO send mail cu detaliile programarii apcientului si mail si la doctor
        //mailService.sendMail(apointment);
        return appointmentRepository.save(appointment);
    }
    @Transactional
    public boolean appointmentExistsForDoctor(Long doctorId, Long appointmentId) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor Id not Found"));
        return doctor.getAppointments().stream()
                .anyMatch(appointment -> appointment.getId().equals(appointmentId));
    }
    @Transactional
    public void removeDoctorFromHospital(Long doctorId, Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not Found"));
        Doctor doctor = doctorRepository.findDoctorById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        hospital.getDoctors().remove(doctor);
        doctorRepository.save(doctor);
        hospitalRepository.save(hospital);
    }

    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }
}
