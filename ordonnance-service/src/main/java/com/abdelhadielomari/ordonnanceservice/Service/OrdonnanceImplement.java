package com.abdelhadielomari.ordonnanceservice.Service;

import com.abdelhadielomari.ordonnanceservice.Exception.StockInsuffisantException;
import com.abdelhadielomari.ordonnanceservice.entities.MedicamentItem;
import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import com.abdelhadielomari.ordonnanceservice.feign.MedicamentRestClient;
import com.abdelhadielomari.ordonnanceservice.feign.PatientRestClient;
import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import com.abdelhadielomari.ordonnanceservice.models.Patient;
import com.abdelhadielomari.ordonnanceservice.repository.MedicamentItemRepository;
import com.abdelhadielomari.ordonnanceservice.repository.OrdonnanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrdonnanceImplement implements OrdonnanceService{
    @Autowired
    private PatientRestClient patientRestClient;
    @Autowired
    private MedicamentRestClient medicamentRestClient;
    @Autowired
    private MedicamentItemRepository medicamentItemRepository;
    @Autowired
    private OrdonnanceRepository ordonnanceRepository;
    @Override

    @McpTool(description = "Get the details of an Ordonnance")
    public Ordonnance getOrdonnance(@McpToolParam(description = "the Ordonnance id") Long id) {
        Ordonnance ordonnances=ordonnanceRepository.findById(id).orElse(null);
        Patient patient=patientRestClient.getPatient(ordonnances.getPatientId());
        ordonnances.setPatient(patient);
        List<MedicamentItem> medicamentItems=medicamentItemRepository.findByOrdonnanceId(id);
        medicamentItems.forEach(m->{
            Medicament medicament=medicamentRestClient.getMedicament(m.getMedicamentId());
            m.setMedicament(medicament);
            medicamentItemRepository.save(m);
        });
        ordonnances.setMedicamentItems(medicamentItems);
        ordonnanceRepository.save(ordonnances);
        return ordonnances;
    }

    @Override
    public Ordonnance AjouteOrdonnance(Ordonnance ordonnance, Long IdPatient) {
        List<MedicamentItem> medicamentItems=ordonnance.getMedicamentItems();
        for (MedicamentItem m:medicamentItems){
            Medicament medicament = medicamentRestClient.getMedicament(m.getMedicamentId());
            if (medicament.getStock() < m.getQuantity()) {
                throw new StockInsuffisantException(
                        "Stock insuffisant pour " + medicament.getNom()
                                + " : demandé " + m.getQuantity()
                                + ", disponible " + medicament.getStock());
            }
            medicamentRestClient.UpdateStock(medicament.getStock()-m.getQuantity(),m.getMedicamentId());
        }
        ordonnance.setPatientId(IdPatient);
        ordonnanceRepository.save(ordonnance);
        medicamentItems.forEach(m->{
            m.setOrdonnance(ordonnance);
            medicamentItemRepository.save(m);
        });
        return ordonnance;
    }
}
