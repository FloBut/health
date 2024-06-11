package com.example.health.services;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.entities.Appointment;
import com.example.health.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentReminderService {
    private AppointmentRepository appointmentRepository;
    private PatientService patientService;
    private ReviewService reviewService;
    @Scheduled(cron = "0 0 9 * * *")// de la ora 9:00 va incepe sa trimita reminder
    public void sendAppointmentReminders() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        List<Appointment> appointments = appointmentRepository.findByStartDateBetween(
                tomorrow.toLocalDate().atStartOfDay(),
                tomorrow.toLocalDate().atTime(23,59, 59));
        appointments.forEach(appointment -> patientService.sendWhatsAppReminder(appointment.getPatient(), appointment));
    }

    @Scheduled(cron = " 0 0 18 * * *")
    public void sendReviewRequest() {
        LocalDateTime yesterday  = LocalDateTime.now().minusDays(1);
        List<Appointment> appointments = appointmentRepository.findByEndDateBetween(
                yesterday.toLocalDate().atStartOfDay(),
                yesterday.toLocalDate().atTime(23, 59, 59));
        appointments.forEach(appointment -> reviewService.requestReview(appointment.getDoctor(), appointment.getPatient()));

    }



}
