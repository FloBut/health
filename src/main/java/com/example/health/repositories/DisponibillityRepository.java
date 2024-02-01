package com.example.health.repositories;

import com.example.health.entities.Disponibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibillityRepository extends JpaRepository<Disponibility, Long> {

}
