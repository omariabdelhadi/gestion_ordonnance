package com.abdelhadielomari.ordonnanceservice.repository;


import com.abdelhadielomari.ordonnanceservice.entities.MedicamentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MedicamentItemRepository extends JpaRepository<MedicamentItem,Long> {
    List<MedicamentItem> findByOrdonnanceId(Long id);
}
