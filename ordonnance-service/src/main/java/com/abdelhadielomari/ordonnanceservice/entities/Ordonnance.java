package com.abdelhadielomari.ordonnanceservice.entities;

import com.abdelhadielomari.ordonnanceservice.models.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ordonnance {
    @Id
    @GeneratedValue

    private Long id;
    private Date ordDate;
    private Long patientId;
    @OneToMany(mappedBy = "ordonnance")
    private List<MedicamentItem> medicamentItems;
    @Transient
    private Patient patient;

}
