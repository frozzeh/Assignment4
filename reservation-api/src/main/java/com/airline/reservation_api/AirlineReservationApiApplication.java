package com.airline.reservation_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlineReservationApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationApiApplication.class, args);
		System.out.println("🚀 Airline API running on http://localhost:8080");
	}
}