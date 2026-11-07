package com.abdelhadielomari.ordonnanceservice.events;

public record LigneEvent(Long medicamentId, int quantite, double prix) {
}
