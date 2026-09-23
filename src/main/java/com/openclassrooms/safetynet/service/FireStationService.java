package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public FireStation addFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepository.update(fireStation);
    }

    public boolean deleteFireStation(FireStation fireStation) {
        if (fireStation.getAddress() != null && !fireStation.getAddress().isBlank()) {
            return fireStationRepository.deleteByAddress(fireStation.getAddress());
        }

        if (fireStation.getStation() != null && !fireStation.getStation().isBlank()) {
            return fireStationRepository.deleteByStationNumber(fireStation.getStation());
        }

        return false;
    }

}
