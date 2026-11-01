package com.abdelhadielomari.analyticsservice.events;

public record LigneEvent(Long medicamentId, int quantite, double prix) {
}
