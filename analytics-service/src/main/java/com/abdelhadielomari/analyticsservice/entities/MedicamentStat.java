package com.abdelhadielomari.analyticsservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MedicamentStat {
    @Id
    private Long medicamentId;
    private int quantiteTotale;
    private double montantTotal;

    @Version
    private Long version;

    public MedicamentStat(Long medicamentId, int quantiteTotale, double montantTotal) {
        this.medicamentId = medicamentId;
        this.quantiteTotale = quantiteTotale;
        this.montantTotal = montantTotal;
    }
}
