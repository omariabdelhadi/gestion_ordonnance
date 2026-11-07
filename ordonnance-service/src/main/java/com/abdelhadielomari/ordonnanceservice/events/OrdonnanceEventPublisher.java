package com.abdelhadielomari.ordonnanceservice.events;

import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdonnanceEventPublisher {
    private static final JsonMapper MAPPER = JsonMapper.builder().build();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publier(Ordonnance o) throws JsonProcessingException {
        List<LigneEvent> lignes = o.getMedicamentItems().stream()
                .map(m -> new LigneEvent(m.getMedicamentId(), m.getQuantity(),
                        m.getPrice() == null ? 0.0 : m.getPrice()))
                .toList();
        OrdonnanceCreeEvent event = new OrdonnanceCreeEvent(o.getId(), o.getPatientId(),new Date(), lignes);
        kafkaTemplate.send("ordonnance-creee", String.valueOf(o.getId()), MAPPER.writeValueAsString(event));
    }
}
