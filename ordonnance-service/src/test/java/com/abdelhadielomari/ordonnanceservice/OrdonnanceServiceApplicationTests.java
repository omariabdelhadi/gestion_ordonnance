package com.abdelhadielomari.ordonnanceservice;

import com.abdelhadielomari.ordonnanceservice.Exception.StockInsuffisantException;
import com.abdelhadielomari.ordonnanceservice.Service.OrdonnanceImplement;
import com.abdelhadielomari.ordonnanceservice.Service.OrdonnanceService;
import com.abdelhadielomari.ordonnanceservice.entities.MedicamentItem;
import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import com.abdelhadielomari.ordonnanceservice.events.OrdonnanceEventPublisher;
import com.abdelhadielomari.ordonnanceservice.feign.MedicamentRestClient;
import com.abdelhadielomari.ordonnanceservice.feign.PatientRestClient;
import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import com.abdelhadielomari.ordonnanceservice.models.Patient;
import com.abdelhadielomari.ordonnanceservice.repository.MedicamentItemRepository;
import com.abdelhadielomari.ordonnanceservice.repository.OrdonnanceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class) // <-- 1. Remplace @SpringBootTest par ceci
class OrdonnanceServiceApplicationTests {

    @Mock
    private PatientRestClient patientRestClient;
    @Mock
    private MedicamentRestClient medicamentRestClient;
    @Mock
    private MedicamentItemRepository medicamentItemRepository;
    @Mock
    private OrdonnanceRepository ordonnanceRepository;
    @Mock
    private OrdonnanceEventPublisher eventPublisher;

    @InjectMocks
    private OrdonnanceImplement ordonnanceImplement;

    @Test
    void getOrdonnance() {
        Long ordonnanceId = 1L;
        Long patientId = 2L;
        Long medicamentId = 3L;

        // Préparation des données
        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setId(ordonnanceId);
        ordonnance.setPatientId(patientId);
        ordonnance.setOrdDate(new Date());

        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setNom("anas");

        Medicament medicament = new Medicament();
        medicament.setId(medicamentId);

        MedicamentItem medicamentItem = new MedicamentItem();
        medicamentItem.setMedicamentId(medicamentId);
        medicamentItem.setOrdonnance(ordonnance);

        List<MedicamentItem> medicamentItemList = new ArrayList<>();
        medicamentItemList.add(medicamentItem);

        // 2. Simulation des réponses (on enlève les Optional.of().orElse(null) inutiles)
        when(ordonnanceRepository.findById(ordonnanceId)).thenReturn(Optional.of(ordonnance));
        when(patientRestClient.getPatient(patientId)).thenReturn(patient);
        when(medicamentItemRepository.findByOrdonnanceId(ordonnanceId)).thenReturn(medicamentItemList);
        when(medicamentRestClient.getMedicament(medicamentId)).thenReturn(medicament);

        // Ces deux lignes ne sont pas obligatoires, mais c'est bien de les mettre si tes méthodes save renvoient quelque chose
        when(medicamentItemRepository.save(medicamentItem)).thenReturn(medicamentItem);
        when(ordonnanceRepository.save(ordonnance)).thenReturn(ordonnance);

        // Exécution
        Ordonnance result = ordonnanceImplement.getOrdonnance(ordonnanceId);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(patient, result.getPatient());
        assertNotNull(result.getMedicamentItems());
        assertEquals(1, result.getMedicamentItems().size());
        assertEquals(medicament, result.getMedicamentItems().get(0).getMedicament());

        verify(ordonnanceRepository, times(1)).findById(ordonnanceId);
        verify(patientRestClient, times(1)).getPatient(patientId);
        verify(medicamentItemRepository, times(1)).findByOrdonnanceId(ordonnanceId);
        verify(medicamentRestClient, times(1)).getMedicament(medicamentId);
        verify(medicamentItemRepository, times(1)).save(medicamentItem);
        verify(ordonnanceRepository, times(1)).save(ordonnance);


    }
    @Test
    void AjouteOrdonnance_exception(){
        Long PatientId=1L;
        Long MedicamentId=2L;
        Long MedicamentItemId=3L;
        int quantiterdemander=15;
        int Stock=10;

        Medicament medicament=new Medicament();
        medicament.setNom("m1");
        medicament.setId(MedicamentId);
        medicament.setStock(Stock);

        MedicamentItem medicamentItem=new MedicamentItem();
        medicamentItem.setQuantity(quantiterdemander);
        medicamentItem.setId(MedicamentItemId);
        medicamentItem.setMedicamentId(MedicamentId);

        List<MedicamentItem> items=new ArrayList<>();
        items.add(medicamentItem);

        Ordonnance ordonnance=new Ordonnance();
        ordonnance.setMedicamentItems(items);

        when(medicamentRestClient.getMedicament(MedicamentId)).thenReturn(medicament);

        StockInsuffisantException exception=assertThrows(StockInsuffisantException.class,()->{

            ordonnanceImplement.AjouteOrdonnance(ordonnance,PatientId);

        });

        assertTrue(exception.getMessage().contains("Stock insuffisant pour m1"));

        verify(medicamentRestClient,never()).UpdateStock(anyInt(),anyLong());
        verify(ordonnanceRepository,never()).save(any());
        verify(medicamentItemRepository,never()).save(any());

    }

    @Test
    void AjouteOrdonnance_SuccessEvenIfKafkaFails() throws JsonProcessingException {

        Long PatientId=1L;
        Long MedicamentId=2L;
        Long MedicamentItemId=3L;
        int quantiterdemander=15;
        int Stock=10;

        Medicament medicament=new Medicament();
        medicament.setNom("m1");
        medicament.setId(MedicamentId);
        medicament.setStock(Stock);

        MedicamentItem medicamentItem=new MedicamentItem();
        medicamentItem.setQuantity(quantiterdemander);
        medicamentItem.setId(MedicamentItemId);
        medicamentItem.setMedicamentId(MedicamentId);

        List<MedicamentItem> items=new ArrayList<>();
        items.add(medicamentItem);

        Ordonnance ordonnance=new Ordonnance();
        ordonnance.setMedicamentItems(items);

        when(medicamentRestClient.getMedicament(MedicamentId)).thenReturn(medicament);

        doThrow(new RuntimeException("Kafka est down")).when(eventPublisher).publier(any());

        Ordonnance resultat=ordonnanceImplement.AjouteOrdonnance(ordonnance,PatientId);

        assertNotNull(resultat);
        verify(ordonnanceRepository,times(1)).save(any());
        verify(medicamentItemRepository,times(1)).save(any());


    }

}
