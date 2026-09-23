package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FireStationRepository {

    private final DataUtils dataUtils;

    public FireStationRepository(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public List<FireStation> findAll() {
        return dataUtils.getFireStations();
    }

    public FireStation save(FireStation fireStation) {
        dataUtils.getFireStations().add(fireStation);
        dataUtils.saveData();
        return fireStation;
    }

    public boolean update(FireStation fireStation) {
        Optional<FireStation> fireStationToUpdate = dataUtils.getFireStations().stream()
                .filter(fs -> fs.getAddress().equalsIgnoreCase(fireStation.getAddress()))
                .findFirst();

        if (fireStationToUpdate.isPresent()) {
            FireStation fs = fireStationToUpdate.get();
            fs.setStation(fireStation.getStation());

            dataUtils.saveData();
            return true;
        }

        return false;
    }

    public boolean deleteByAddress(String address) {

        boolean removed = dataUtils.getFireStations().removeIf(fs -> fs.getAddress().equalsIgnoreCase(address));

        if (removed) {
            dataUtils.saveData();
            return true;
        }

        return false;
    }

    public boolean deleteByStationNumber(String stationNumber) {
        boolean removed = dataUtils.getFireStations().removeIf(fs -> fs.getStation().equalsIgnoreCase(stationNumber));

        if (removed) {
            dataUtils.saveData();
            return true;
        }

        return false;
    }

}
