package com.abdelhadielomari.medicamentservice.repository;

import com.abdelhadielomari.medicamentservice.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
    List<Medicament> findByLaboratory(String laboratory);
    List<Medicament> findByNom(String Nom);
}
