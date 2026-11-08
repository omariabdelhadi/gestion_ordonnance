package com.abdelhadielomari.analyticsservice;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import com.abdelhadielomari.analyticsservice.services.PredictionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AnalyticsServiceApplicationTests {

    @Mock
    private MedicamentConsommationRepository repository;

    @InjectMocks
    private PredictionService predictionService;

    @Test
    void calculerConsommationMoyenne(){
        Long medicamentId=1L;
        Date dateDebut = Date.from(
                LocalDate.now().minusDays(30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        Date dateConsomation = Date.from(
                LocalDate.now().minusDays(15)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        MedicamentConsommation medicamentConsommation=new MedicamentConsommation();
        medicamentConsommation.setMedicamentId(medicamentId);
        medicamentConsommation.setDateConsommation(dateConsomation);
        medicamentConsommation.setQuantite(30);
        List<MedicamentConsommation> medicamentConsommationList=new ArrayList<>();
        medicamentConsommationList.add(medicamentConsommation);

        when(repository.findByMedicamentIdAndDateConsommationAfter(medicamentId,dateDebut)).thenReturn(medicamentConsommationList);

        double ConsommationMoyenne=predictionService.calculerConsommationMoyenne(medicamentId);

        assertEquals(1.0,ConsommationMoyenne);

        verify(repository,times(1)).findByMedicamentIdAndDateConsommationAfter(medicamentId,dateDebut);

    }
    @Test
    void estimerJoursAvantRupture_cas_normale(){
        int Stock=30;
        double consomationMoyenne=4.0;

        double  pred=predictionService.estimerJoursAvantRupture(Stock,consomationMoyenne);
        assertEquals(7.5,pred);
    }
    @Test
    void estimerJoursAvantRupture_cas_anormale(){
        int Stock=30;
        double consomationMoyenne=0.0;

        double  pred=predictionService.estimerJoursAvantRupture(Stock,consomationMoyenne);
        assertEquals(Double.POSITIVE_INFINITY,pred);
    }

}