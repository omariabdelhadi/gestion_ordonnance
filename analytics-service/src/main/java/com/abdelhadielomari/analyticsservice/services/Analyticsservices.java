package com.abdelhadielomari.analyticsservice.services;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;

import java.util.List;

public interface Analyticsservices {
    List<MedicamentStat> getAll();
    MedicamentStat getStatById(Long id);
    List<MedicamentConsommation> findByMedicamentConsomationId(Long medicamentId);

}
