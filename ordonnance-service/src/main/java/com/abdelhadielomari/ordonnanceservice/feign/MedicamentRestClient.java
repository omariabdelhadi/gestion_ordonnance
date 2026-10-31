package com.abdelhadielomari.ordonnanceservice.feign;


import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "medicament-service")
public interface MedicamentRestClient {
    @GetMapping("medicaments/{id}")
    @CircuitBreaker(name = "medicament-service",fallbackMethod = "getDefaultMedicament")
    Medicament getMedicament(@PathVariable Long id);

    @PutMapping("UpdateStock/{NewStock}/{idMedicam}")
    void UpdateStock(@PathVariable int NewStock,@PathVariable Long idMedicam);

    default Medicament getDefaultMedicament(Long id,Exception exception){
        exception.printStackTrace();
        Medicament medicament=new Medicament();
        medicament.setId(id);
        medicament.setNom("dN");
        medicament.setExpiration(null);
        medicament.setLaboratory("DA");
        medicament.setPrice_unit(0.0);
        return medicament;
    }

}
