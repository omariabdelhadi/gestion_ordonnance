package com.abdelhadielomari.ordonnanceservice.events;

import java.util.Date;
import java.util.List;

public record OrdonnanceCreeEvent(Long ordonnanceId, Long patientId, Date dateCreation, List<LigneEvent> lignes) {
}
