package com.abdelhadielomari.ordonnanceservice.Controller;

import com.abdelhadielomari.ordonnanceservice.Service.OrdonnanceService;
import com.abdelhadielomari.ordonnanceservice.entities.MedicamentItem;
import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import com.abdelhadielomari.ordonnanceservice.feign.MedicamentRestClient;
import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdonnanceController {

    @Autowired
    private OrdonnanceService ordonnanceService;

    @GetMapping("/ordonnances/{id}")
    Ordonnance getOrdonnance(@PathVariable Long id){
        Ordonnance ordonnance=ordonnanceService.getOrdonnance(id);
        return ordonnance;
    }

    @PostMapping("/addOrdonnance/{idPatient}")
    Ordonnance AddOrdonnance(@RequestBody Ordonnance ordonnance,@PathVariable Long idPatient){
        Ordonnance ordonnance1= ordonnanceService.AjouteOrdonnance(ordonnance,idPatient);
        Ordonnance ordonnance2=ordonnanceService.getOrdonnance(ordonnance1.getId());
        return ordonnance2;
    }
}
