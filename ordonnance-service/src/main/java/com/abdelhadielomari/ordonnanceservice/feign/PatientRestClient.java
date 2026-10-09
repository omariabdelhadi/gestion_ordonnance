package com.abdelhadielomari.ordonnanceservice.feign;

import com.abdelhadielomari.ordonnanceservice.models.Patient;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface PatientRestClient {
    @GetMapping("/patients/{id}")
    @CircuitBreaker(name = "patient-service",fallbackMethod = "getDefaultPatient")
    Patient getPatient(@PathVariable Long id);

    default Patient getDefaultPatient(Long id, Exception e){
        Patient patient=new Patient();
        patient.setId(id);
        patient.setAge(18);
        patient.setNom("Nom");
        return patient;
    }
}
