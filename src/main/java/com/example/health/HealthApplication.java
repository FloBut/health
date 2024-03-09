package com.example.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//clinici,care au medici, care au disponibilitati si programari
//un user se poate loga in aplicatie ca doctor, pacient sau administrator de aplicatie
//un spital are un nume si se afla intr un oras si are o adresa
////
@SpringBootApplication
@EntityScan(basePackages="com.example.health")
@EnableJpaRepositories(basePackages="com.example.health")
public class HealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthApplication.class, args);
	}

}
