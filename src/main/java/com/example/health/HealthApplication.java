package com.example.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//clinici,care au medici, care au disponibilitati si programari
//un user se poate loga in aplicatie ca doctor, pacient sau administrator de aplicatie
//un spital are un nume si se afla intr un oras si are o adresa
////
@SpringBootApplication
public class HealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthApplication.class, args);
	}

}
