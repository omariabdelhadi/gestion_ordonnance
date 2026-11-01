package com.abdelhadielomari.analyticsservice.events;


import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentConsommationRepository;
import com.abdelhadielomari.analyticsservice.repositories.MedicamentStatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
@RequiredArgsConstructor
public class OrdonnanceEventListener {
    private static final JsonMapper MAPPER = JsonMapper.builder().build();
    private final MedicamentConsommationRepository consommationRepo;
    private final MedicamentStatRepository repo;

    @KafkaListener(topics = "ordonnance-creee")
    @Transactional
    public void onOrdonnance(String message) {
        OrdonnanceCreeEvent event = MAPPER.readValue(message, OrdonnanceCreeEvent.class);
        for (LigneEvent l : event.lignes()) {
            MedicamentStat stat = repo.findById(l.medicamentId())
                    .orElse(new MedicamentStat(l.medicamentId(), 0, 0.0));
            stat.setQuantiteTotale(stat.getQuantiteTotale() + l.quantite());
            stat.setMontantTotal(stat.getMontantTotal() + l.prix());
            repo.save(stat);

            MedicamentConsommation consommation = new MedicamentConsommation(
                    null,
                    l.medicamentId(),
                    event.ordonnanceId(),
                    l.quantite(),
                    event.dateCreation()
            );
            consommationRepo.save(consommation);
        }
    }

}
