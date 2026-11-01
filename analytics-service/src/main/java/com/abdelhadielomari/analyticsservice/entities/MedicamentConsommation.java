package com.abdelhadielomari.analyticsservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentConsommation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicamentId;

    private Long ordonnanceId;

    private int quantite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConsommation;
}