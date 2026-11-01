package com.abdelhadielomari.analyticsservice.services;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final MedicamentConsommationRepository repository;

    public double calculerConsommationMoyenne(Long medicamentId) {

        Date dateDebut = Date.from(
                LocalDate.now().minusDays(30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        List<MedicamentConsommation> consommations =
                repository.findByMedicamentIdAndDateConsommationAfter(
                        medicamentId, dateDebut
                );

        int quantiteTotale = consommations.stream()
                .mapToInt(MedicamentConsommation::getQuantite)
                .sum();

        return quantiteTotale / 30.0;
    }

    public double estimerJoursAvantRupture(int stockActuel, double consommationMoyenne) {

        if (consommationMoyenne <= 0) {
            return Double.POSITIVE_INFINITY;
        }

        return stockActuel / consommationMoyenne;
    }
}