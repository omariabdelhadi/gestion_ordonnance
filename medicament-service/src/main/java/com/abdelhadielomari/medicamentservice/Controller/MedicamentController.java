package com.abdelhadielomari.medicamentservice.Controller;


import com.abdelhadielomari.medicamentservice.entities.Medicament;
import com.abdelhadielomari.medicamentservice.repository.MedicamentRepository;
import com.abdelhadielomari.medicamentservice.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicamentController {
    @Autowired
    private MedicamentRepository medicamentRepository;
    @Autowired
    private MedicamentService medicamentService;

    @PutMapping("/UpdateStock/{NewStock}/{idMedicam}")
    public void UpdateStock(@PathVariable int NewStock, @PathVariable Long idMedicam){
        Medicament medicament=medicamentRepository.findById(idMedicam).orElse(null);
        medicament.setStock(NewStock);
        medicamentRepository.save(medicament);
    }
    @PostMapping("/AjouterMedicament")
    public Medicament AddMedicament(@RequestParam Medicament medicament){
        return medicamentService.AddMadicament(medicament);
    }
}
