package com.abdelhadielomari.analyticsservice.services;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final MedicamentConsommationRepository repository;

    @McpTool(description = "calculer la Consommation Moyenne d'un medicament")
    public double calculerConsommationMoyenne(@McpToolParam(description = "l'id de medicament qu'on veut obtient ca consomation moyenne") Long medicamentId) {

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

    @McpTool(description = "cette methode est pour fait une prediction de combient de jour qui rest pour que le stock d'un medicament soit terminer")
    public double estimerJoursAvantRupture(@McpToolParam(description = "le stock actuel de medicament, en la recuperer par la methode getMedicament qui demande l'id de medicament et qui retourn les details de medicament inclue son stock actuelle") int stockActuel,@McpToolParam(description = "la consmmation moyenne de medicament , a calculer par la methode calculerConsommationMoyenne a partire de l'id de medicament") double consommationMoyenne) {

        if (consommationMoyenne <= 0) {
            return Double.POSITIVE_INFINITY;
        }

        return stockActuel / consommationMoyenne;
    }
}