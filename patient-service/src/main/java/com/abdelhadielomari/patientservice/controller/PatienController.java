package com.abdelhadielomari.patientservice.controller;

import com.abdelhadielomari.patientservice.entities.Patient;
import com.abdelhadielomari.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatienController {
    @Autowired
    PatientService patientService;
    @PostMapping("/AddPatient")
    public Patient AddPatient(@RequestParam Patient patient){
        return patientService.AddPatient(patient);
    }
}
