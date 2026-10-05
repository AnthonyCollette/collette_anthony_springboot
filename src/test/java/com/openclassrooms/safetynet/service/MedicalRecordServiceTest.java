package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    private MedicalRecord medicalRecord;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    void setUp() {
        medicalRecord = new MedicalRecord("Clive", "Ferguson", "03/06/1994", List.of(), List.of());
    }

    @Test
    void testAddMedicalRecord() {
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        MedicalRecord result = medicalRecordService.addMedicalRecord(medicalRecord);

        assertEquals(medicalRecord, result);
        verify(medicalRecordRepository).save(medicalRecord);
    }

    @Test
    void testUpdateMedicalRecord() {
        when(medicalRecordRepository.update(any(MedicalRecord.class))).thenReturn(true);

        Boolean updated = medicalRecordService.updateMedicalRecord(medicalRecord);

        assertTrue(updated);
        verify(medicalRecordRepository).update(medicalRecord);
    }

    @Test
    void testDeleteMedicalRecord() {
        when(medicalRecordRepository.delete(any(MedicalRecord.class))).thenReturn(true);

        Boolean deleted = medicalRecordService.deleteMedicalRecord(medicalRecord);

        assertTrue(deleted);
        verify(medicalRecordRepository).delete(medicalRecord);
    }

}
