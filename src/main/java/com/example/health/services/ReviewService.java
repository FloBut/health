package com.example.health.services;

import com.example.health.dtos.ReviewRequestDTO;
import com.example.health.entities.Doctor;
import com.example.health.entities.Patient;
import com.example.health.entities.Review;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.DoctorRepository;
import com.example.health.repositories.PatientRepository;
import com.example.health.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public ReviewService(ReviewRepository reviewRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.reviewRepository = reviewRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
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
