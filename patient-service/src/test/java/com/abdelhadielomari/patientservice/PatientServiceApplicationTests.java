package com.abdelhadielomari.patientservice;

import com.abdelhadielomari.patientservice.entities.Patient;
import com.abdelhadielomari.patientservice.repository.PatientRepository;
import com.abdelhadielomari.patientservice.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PatientServiceApplicationTests {

    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private PatientService patientService;

    @Test
    void testAddPatient(){
        Patient patient=new Patient();
        patient.setId(1L);
        patient.setAge(20);
        patient.setNom("Abdelhadi");

        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patient resultat=patientService.AddPatient(patient);
        assertNotNull(resultat);
        assertEquals("Abdelhadi",resultat.getNom());
        verify(patientRepository,times(1)).save(patient);
    }
}
