package com.abdelhadielomari.medicamentservice;

import com.abdelhadielomari.medicamentservice.entities.Medicament;
import com.abdelhadielomari.medicamentservice.repository.MedicamentRepository;
import com.abdelhadielomari.medicamentservice.service.MedicamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MedicamentServiceApplicationTests {

    @Mock
    private MedicamentRepository medicamentRepository;
    @InjectMocks
    private MedicamentService medicamentService;

    @Test
    void ajouterMedicament() {
        Medicament medicament=new Medicament();
        medicament.setNom("m3");
        medicament.setStock(13);
        medicament.setExpiration(new Date());
        medicament.setPrice_unit(25.0);
        medicament.setLaboratory("Aboratoir1");

        when(medicamentRepository.save(any(Medicament.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Medicament medicament1=medicamentService.AddMadicament(medicament);
        assertNotNull(medicament1);
        assertEquals("m3",medicament1.getNom());
        verify(medicamentRepository,times(1)).save(medicament);
    }

    @Test
    void get_Medicament_existent(){
        Medicament medicament=new Medicament();
        medicament.setNom("m1");

        when(medicamentRepository.findById(1L)).thenReturn(Optional.of(medicament));
        Medicament medicament1=medicamentService.getMedicament(1L);
        assertNotNull(medicament1);
        assertEquals("m1",medicament1.getNom());
        verify(medicamentRepository,times(1)).findById(1L);
    }

    @Test
    void get_Medicament_not_existent(){
        when(medicamentRepository.findById(99L)).thenReturn(Optional.empty());
        Medicament medicament=medicamentService.getMedicament(99L);
        assertNull(medicament);
        verify(medicamentRepository,times(1)).findById(99L);
    }

    @Test
    void get_All_Medicament(){
        when(medicamentRepository.findAll()).thenReturn(List.of(new Medicament(),new Medicament()));
        List<Medicament> medicamentList=medicamentService.getAllMedicaments();
        assertEquals(2,medicamentList.size());
        verify(medicamentRepository,times(1)).findAll();
    }
}
