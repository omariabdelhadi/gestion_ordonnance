package com.abdelhadielomari.medicamentservice.service;

import com.abdelhadielomari.medicamentservice.entities.Medicament;
import com.abdelhadielomari.medicamentservice.repository.MedicamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    @McpTool(description = "get All the Medicaments")
    public List<Medicament> getAllMedicaments(){
        return medicamentRepository.findAll();
    }
    @McpTool(description = "get the medicament by his id")
    public Medicament getMedicament(@McpToolParam(description = "the id of the medicament") Long id){
        return medicamentRepository.findById(id).orElse(null);
    }
    @McpTool(description = "add an new medicament")
    public Medicament AddMadicament(@McpToolParam(description = "the medicament that we want to add, the user must give those info :nom,price_unit,laboratory,expiration,Stock") Medicament medicament){
        Medicament medicamentAdd=medicamentRepository.save(medicament);
        return medicamentAdd;
    }
}
