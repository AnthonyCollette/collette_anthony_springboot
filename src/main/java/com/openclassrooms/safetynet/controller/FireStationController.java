package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireStationDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @PostMapping
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) {

        FireStation fs = fireStationService.addFireStation(fireStation);
        return ResponseEntity.status(HttpStatus.CREATED).body(fs);
    }

    @PutMapping
    public ResponseEntity<Void> updateFireStation(@RequestBody FireStation fireStation) {
        Boolean updated = fireStationService.updateFireStation(fireStation);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFireStation(@RequestBody FireStation fireStation) {
        Boolean deleted = fireStationService.deleteFireStation(fireStation);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<FireStationDTO> getPersonsByStationNumber(@RequestParam String stationNumber) {

        FireStationDTO dto = fireStationService.getPersonsByStationNumber(stationNumber);

        return ResponseEntity.ok(dto);
    }

}
