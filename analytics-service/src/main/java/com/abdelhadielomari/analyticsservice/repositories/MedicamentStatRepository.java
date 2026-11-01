package com.abdelhadielomari.analyticsservice.repositories;

import com.abdelhadielomari.analyticsservice.entities.MedicamentStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface MedicamentStatRepository extends JpaRepository<MedicamentStat,Long> {
}
