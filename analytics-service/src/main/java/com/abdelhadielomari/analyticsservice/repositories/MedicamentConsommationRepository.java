package com.abdelhadielomari.analyticsservice.repositories;

import com.abdelhadielomari.analyticsservice.entities.MedicamentConsommation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface MedicamentConsommationRepository extends JpaRepository<MedicamentConsommation,Long> {
    List<MedicamentConsommation> findByMedicamentId(Long medicamentId);
    List<MedicamentConsommation> findByMedicamentIdAndDateConsommationAfter(
            Long medicamentId, Date date
    );
}
