package com.abdelhadielomari.ordonnanceservice.entities;


import com.abdelhadielomari.ordonnanceservice.models.Medicament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentItem {
    @Id
    @GeneratedValue

    private Long id;
    private Long medicamentId;
    private int quantity;
    private Double price;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ordonnance ordonnance;
    @Transient
    private Medicament medicament;

}
