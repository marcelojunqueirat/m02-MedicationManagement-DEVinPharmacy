package com.devinpharmacy.medicationManagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedicationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicationManagementApplication.class, args);
	}

	@Bean
	public ModelMapper obterModelMapper() {
		return new ModelMapper();
	}
}
