package com.abdelhadielomari.ordonnanceservice;

import com.abdelhadielomari.ordonnanceservice.entities.MedicamentItem;
import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import com.abdelhadielomari.ordonnanceservice.repository.MedicamentItemRepository;
import com.abdelhadielomari.ordonnanceservice.repository.OrdonnanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;


@EnableFeignClients
@SpringBootApplication
public class OrdonnanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdonnanceServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(OrdonnanceRepository ordonnanceRepository, MedicamentItemRepository medicamentItemRepository){
        return args -> {
            List<Long> medicamentId= List.of(1L,2L);
            List<Long> patientId=List.of(1L,2L);
            patientId.forEach(pId->{
                Ordonnance ordonnance=new Ordonnance();
                ordonnance.setPatientId(pId);
                ordonnance.setOrdDate(new Date());
                ordonnanceRepository.save(ordonnance);
                medicamentId.forEach(mId->{
                    MedicamentItem medicamentItem=new MedicamentItem();
                    medicamentItem.setMedicamentId(mId);
                    medicamentItem.setQuantity(5);
                    if (mId==1L){
                        medicamentItem.setPrice(5*50.0);
                    } else if (mId==2L) {
                        medicamentItem.setPrice(5*40.0);
                    }
                    medicamentItem.setOrdonnance(ordonnance);
                    medicamentItemRepository.save(medicamentItem);
                });
            });

        };
    }

}
