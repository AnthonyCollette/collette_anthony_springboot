package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordRepositoryTest {

    private MedicalRecord medicalRecord;
    private List<MedicalRecord> medicalRecords;

    @InjectMocks
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private DataUtils dataUtils;

    @BeforeEach
    void setUp() {
        medicalRecord = new MedicalRecord("Eric", "Cadigan", "08/06/1945", List.of("tradoxidine : 400mg"), List.of());
        medicalRecords = new ArrayList<>(List.of(medicalRecord));
    }

    @Test
    void testFindAll() {
        when(dataUtils.getMedicalRecords()).thenReturn(medicalRecords);

        List<MedicalRecord> result = medicalRecordRepository.findAll();

        assertEquals(result, medicalRecords);
        verify(dataUtils).getMedicalRecords();
    }

    @Test
    void testSave() {
        when(dataUtils.getMedicalRecords()).thenReturn(medicalRecords);

        MedicalRecord result = medicalRecordRepository.save(medicalRecord);

        assertEquals(result, medicalRecord);
        verify(dataUtils).saveData();
    }

    @Test
    void testUpdate() {
        when(dataUtils.getMedicalRecords()).thenReturn(medicalRecords);

        Boolean updated = medicalRecordRepository.update(medicalRecord);

        assertTrue(updated);
        verify(dataUtils).saveData();
    }

    @Test
    void testDelete() {
        when(dataUtils.getMedicalRecords()).thenReturn(medicalRecords);

        Boolean deleted = medicalRecordRepository.delete(medicalRecord);

        assertTrue(deleted);
        verify(dataUtils).saveData();
    }

}
