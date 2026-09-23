package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {this.medicalRecordRepository = medicalRecordRepository;}

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.update(medicalRecord);
    }

    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.delete(medicalRecord);
    }

}
