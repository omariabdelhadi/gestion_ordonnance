package com.abdelhadielomari.ordonnanceservice.Service;


import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;

import java.util.List;

public interface OrdonnanceService {
    Ordonnance getOrdonnance(Long id);
    Ordonnance AjouteOrdonnance(Ordonnance ordonnance,Long IdPatient);
}
