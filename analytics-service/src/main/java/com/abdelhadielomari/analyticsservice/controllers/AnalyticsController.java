package com.abdelhadielomari.analyticsservice.controllers;


import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;
import com.abdelhadielomari.analyticsservice.feign.MedicamentRestClient;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentStatRepository;
import com.abdelhadielomari.analyticsservice.services.Analyticsservices;

import com.abdelhadielomari.analyticsservice.services.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    // H
    private final Analyticsservices analyticsservices;
    private final MedicamentConsommationRepository medicamentConsommationRepository;
    private final MedicamentRestClient medicamentRestClient;
    private final PredictionService predictionService;


    @GetMapping("/medicaments")
    public List<MedicamentStat> stats(){
        return analyticsservices.getAll();
    }
    @GetMapping("/medicaments/{id}")
    public MedicamentStat stat(@PathVariable Long id){
        return analyticsservices.getById(id);
    }
    @GetMapping("/medicament/{id}/historique")
    public List<MedicamentConsommation> historiquemedicament(@PathVariable Long id){
        return medicamentConsommationRepository.findByMedicamentId(id);
    }
    @GetMapping("/medicament/{id}/predection")
    public double PredectionEpuise(@PathVariable Long id){
       return predictionService.estimerJoursAvantRupture(medicamentRestClient.getStock(id),predictionService.calculerConsommationMoyenne(id));
    }
}
