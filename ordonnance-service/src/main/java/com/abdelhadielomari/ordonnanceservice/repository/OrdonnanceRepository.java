package com.abdelhadielomari.ordonnanceservice.repository;


import com.abdelhadielomari.ordonnanceservice.entities.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Long> {

}
