package com.abdelhadielomari.medicamentservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Medicament {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private Double price_unit;
    private String laboratory;
    private Date expiration;
    private int Stock;
}
