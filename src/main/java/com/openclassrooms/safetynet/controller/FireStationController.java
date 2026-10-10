package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireStationDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @PostMapping
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) {
        log.info("Received POST request for /firestation with fire station: {}", fireStation);

        try {
            FireStation fs = fireStationService.addFireStation(fireStation);

            log.info("Successfully processed /firestation GET request for fire station: {}", fireStation);
            return ResponseEntity.status(HttpStatus.CREATED).body(fs);
        } catch (Exception e) {
            log.error("Error occurred while processing /firestation for fire station: {} - Message: {}", fireStation, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateFireStation(@RequestBody FireStation fireStation) {
        log.info("Received PUT request for /firestation with fire station: {}", fireStation);

        try {
            Boolean updated = fireStationService.updateFireStation(fireStation);
            log.info("Successfully processed /firestation PUT request for fire station: {}", fireStation);
            if (!updated) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /firestation for fire station: {} - Message: {}", fireStation, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFireStation(@RequestBody FireStation fireStation) {
        log.info("Received DELETE request for /firestation with fire station: {}", fireStation);

        try {
            Boolean deleted = fireStationService.deleteFireStation(fireStation);

            log.info("Successfully processed /firestation DELETE request for fire station: {}", fireStation);

            if (!deleted) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /firestation for fire station: {} - Message: {}", fireStation, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<FireStationDTO> getPersonsByStationNumber(@RequestParam String stationNumber) {
        log.info("Received GET request for /firestation with fire station number: {}", stationNumber);
        try {
            FireStationDTO dto = fireStationService.getPersonsByStationNumber(stationNumber);
            log.info("Successfully processed /firestation GET request for fire station number: {}", stationNumber);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("Error occurred while processing /firestation for fire station number: {} - Message: {}", stationNumber, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
