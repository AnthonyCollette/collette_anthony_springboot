package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepository {

    private final DataUtils dataUtils;

    public FireStationRepository(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public List<FireStation> findAll() {
        return dataUtils.getFireStations();
    }

}
