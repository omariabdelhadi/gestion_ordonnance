package com.abdelhadielomari.ordonnanceservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicament {
    private Long id;
    private String nom;
    private Double price_unit;
    private String laboratory;
    private Date expiration;
    private int Stock;
}
