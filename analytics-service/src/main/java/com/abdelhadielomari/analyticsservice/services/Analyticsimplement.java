package com.abdelhadielomari.analyticsservice.services;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentStatRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class Analyticsimplement implements Analyticsservices {

    @Autowired
    private MedicamentStatRepository medicamentStatRepository;
    @Autowired
    private MedicamentConsommationRepository medicamentConsommationRepository;



    @McpTool(description="get All the medicament stats")
    @Override
    public List<MedicamentStat> getAll() {
        return medicamentStatRepository.findAll();
    }

    @McpTool(description = "get an medicament stat by his id")
    @Override
    public MedicamentStat getStatById(@McpToolParam(description = "the id of the medicament stat") Long id) {
        return medicamentStatRepository.findById(id).orElse(null);
    }

    @McpTool(description = "get the Medicament Consomation history")
    @Override
    public List<MedicamentConsommation> findByMedicamentConsomationId(@McpToolParam(description = "the id of the medicament") Long medicamentId) {
        return medicamentConsommationRepository.findByMedicamentId(medicamentId);
    }


}
