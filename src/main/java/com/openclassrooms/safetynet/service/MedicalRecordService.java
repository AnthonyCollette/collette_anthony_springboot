package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {this.medicalRecordRepository = medicalRecordRepository;}

    /**
     * Adds a new medical record.
     * @param medicalRecord
     * @return {@link MedicalRecord}
     */

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    /**
     * Updates an existent medical record.
     * @param medicalRecord
     * @return {@link Boolean}
     */

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.update(medicalRecord);
    }

    /**
     * Deletes a medical record.
     * @param medicalRecord
     * @return {@link Boolean}
     */

    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.delete(medicalRecord);
    }

}
