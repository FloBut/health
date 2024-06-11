package com.example.health.services;

import com.example.health.dtos.DoctorRequestDTO;
import com.example.health.dtos.PatientRequestDTO;
import com.example.health.dtos.ReviewRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.Patient;
import com.example.health.entities.Review;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.DoctorRepository;
import com.example.health.repositories.PatientRepository;
import com.example.health.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private PatientService patientService;
    private ReviewRepository reviewRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public void requestReview (DoctorRequestDTO doctorRequestDTO, PatientRequestDTO patientRequestDTO) {
        String messageBody = "Please leave a review for yor recent appointment with Dr. " + doctorRequestDTO.getName();
        patientService.sendWhatsAppMessage(patientRequestDTO.getPhoneNo(), messageBody);
    }

@Transactional
    public Review submitReviewToDoctor(String doctorName, String patientName, ReviewRequestDTO reviewRequestDTO) {
        Doctor doctor = doctorRepository.findDoctorByName(doctorName).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with name: " + doctorName));
        Patient patient = patientRepository.findPatientByName(patientName).orElseThrow(() -> new ResourceNotFoundException("Patient not found with name: " + patientName));

        Review review = new Review();
        review.setReview(reviewRequestDTO.getReview());
        review.setDoctor(doctor);
        review.setPatient(patient);

        return reviewRepository.save(review);
    }

}
