package com.example.health.controllers;

import com.example.health.dtos.ReviewRequestDTO;
import com.example.health.entities.Review;
import com.example.health.repositories.ReviewRepository;
import com.example.health.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {
    private ReviewService reviewService;
@Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/add/{doctorName}/ {patientName}")
    ResponseEntity<Review> submitReviewToDoctor(@PathVariable String doctorName, @PathVariable String patientName, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.submitReviewToDoctor(doctorName, patientName, reviewRequestDTO));
    }
}
