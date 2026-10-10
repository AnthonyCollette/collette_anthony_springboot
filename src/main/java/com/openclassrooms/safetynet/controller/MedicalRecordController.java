package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Received POST request for /medicalRecord with medical record: {}", medicalRecord);
        try {
            MedicalRecord mr = medicalRecordService.addMedicalRecord(medicalRecord);

            log.info("Successfully processed /medicalRecord POST request for medical record: {}", medicalRecord);
            return ResponseEntity.ok(mr);
        } catch (Exception e) {
            log.error("Error occurred while processing /medicalRecord for medical record: {} - Message: {}", medicalRecord, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Received PUT request for /medicalRecord with medical record: {}", medicalRecord);

        try {
            Boolean updated = medicalRecordService.updateMedicalRecord(medicalRecord);
            log.info("Successfully processed /medicalRecord PUT request for medical record: {}", medicalRecord);
            if (!updated) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /medicalRecord for medical record: {} - Message: {}", medicalRecord, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Received DELETE request for /medicalRecord with medical record: {}", medicalRecord);

        try {
            Boolean deleted = medicalRecordService.deleteMedicalRecord(medicalRecord);
            log.info("Successfully processed /medicalRecord DELETE request for medical record: {}", medicalRecord);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /medicalRecord for medical record: {} - Message: {}", medicalRecord, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
