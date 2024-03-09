package com.example.health.repositories;

import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findUserById(Long id);
}
