package com.abdelhadielomari.analyticsservice.services;

import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;

import java.util.List;

public interface Analyticsservices {
    List<MedicamentStat> getAll();
    MedicamentStat getById(Long id);
}
