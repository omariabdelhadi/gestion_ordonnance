package com.abdelhadielomari.patientservice.service;

import com.abdelhadielomari.patientservice.entities.Patient;
import com.abdelhadielomari.patientservice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @McpTool(description = "get All the patients")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    @McpTool(description = "get the patient by his id")
    public Patient getPatient(@McpToolParam(description = "the id of the patient") Long id){
        return patientRepository.findById(id).orElse(null);
    }
    public Patient AddPatient(Patient patient){
        Patient patientAdd=patientRepository.save(patient);
        return patientAdd;
    }
}
