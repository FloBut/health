package com.example.health.repositories;

import com.example.health.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByEndDateBetween(LocalDateTime start, LocalDateTime end);
}
