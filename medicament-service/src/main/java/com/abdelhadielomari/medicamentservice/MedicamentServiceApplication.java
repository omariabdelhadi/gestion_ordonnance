package com.abdelhadielomari.medicamentservice;

import com.abdelhadielomari.medicamentservice.entities.Medicament;
import com.abdelhadielomari.medicamentservice.repository.MedicamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MedicamentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicamentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MedicamentRepository medicamentRepository){
        return args -> {
            medicamentRepository.save(Medicament.builder().nom("medicament1").Stock(10).expiration(new Date()).laboratory("labo1").price_unit(50.0).build());
            medicamentRepository.save(Medicament.builder().nom("medicament2").Stock(15).expiration(new Date()).laboratory("labo2").price_unit(40.0).build());
        };
    }

}
