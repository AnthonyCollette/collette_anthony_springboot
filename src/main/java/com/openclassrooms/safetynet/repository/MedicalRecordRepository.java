package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository {

    private final DataUtils dataUtils;

    public MedicalRecordRepository(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public List<MedicalRecord> findAll() {
        return dataUtils.getMedicalRecords();
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        dataUtils.getMedicalRecords().add(medicalRecord);
        dataUtils.saveData();
        return medicalRecord;
    }

    public boolean update(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> medicalRecordToUpdate = dataUtils.getMedicalRecords().stream()
                .filter(mr -> mr.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
                && mr.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))
                .findFirst();

        if (medicalRecordToUpdate.isPresent()) {
            MedicalRecord mr = medicalRecordToUpdate.get();
            mr.setMedications(medicalRecord.getMedications());
            mr.setAllergies(medicalRecord.getAllergies());
            mr.setBirthdate(medicalRecord.getBirthdate());

            dataUtils.saveData();
            return true;
        }

        return false;
    }

    public boolean delete(MedicalRecord medicalRecord) {
        boolean removed = dataUtils.getMedicalRecords().removeIf(mr ->
                mr.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName()) && mr.getLastName().equalsIgnoreCase(medicalRecord.getLastName()));

        if (removed) {
            dataUtils.saveData();
            return true;
        }

        return false;
    }
}
