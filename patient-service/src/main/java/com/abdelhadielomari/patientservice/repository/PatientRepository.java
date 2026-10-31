package com.abdelhadielomari.patientservice.repository;

import com.abdelhadielomari.patientservice.entities.Patient;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
