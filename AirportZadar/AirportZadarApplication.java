package com.example.AirportZadar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.example.AirportZadar.Repository", "com.example.AirportZadar.Auth", 
    "com.example.AirportZadar.Controller", "com.example.AirportZadar.Repository", "com.example.AirportZadar.Service"})
@EnableJpaRepositories("com.example.AirportZadar.Repository")
public class AirportZadarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportZadarApplication.class, args);
	}

}
