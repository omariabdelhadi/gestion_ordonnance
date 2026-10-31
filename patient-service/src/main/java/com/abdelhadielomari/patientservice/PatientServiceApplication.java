package com.abdelhadielomari.patientservice;

import com.abdelhadielomari.patientservice.entities.Patient;
import com.abdelhadielomari.patientservice.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(Patient.builder().age(18).nom("hamada").build());
            patientRepository.save(Patient.builder().age(17).nom("jawad").build());
        };
    }

}
