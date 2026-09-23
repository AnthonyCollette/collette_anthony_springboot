package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @PostMapping
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.addFireStation(fireStation);
    }

    @PutMapping
    public boolean updateFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping
    public boolean deleteFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.deleteFireStation(fireStation);
    }

}
