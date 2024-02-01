package com.example.health.repositories;

import com.example.health.entities.DoctorApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppRepository extends JpaRepository<DoctorApp, Long> {
}
