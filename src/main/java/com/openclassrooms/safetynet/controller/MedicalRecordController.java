package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord mr = medicalRecordService.addMedicalRecord(medicalRecord);
        return ResponseEntity.ok(mr);
    }

    @PutMapping
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Boolean updated = medicalRecordService.updateMedicalRecord(medicalRecord);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Boolean deleted = medicalRecordService.deleteMedicalRecord(medicalRecord);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
