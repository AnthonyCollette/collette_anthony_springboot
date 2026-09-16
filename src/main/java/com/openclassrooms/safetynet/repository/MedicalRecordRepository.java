package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.MedicalRecord;
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
public class MedicalRecordRepository {

    private final DataUtils dataUtils;

    public MedicalRecordRepository(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public List<MedicalRecord> findAll() {
        return dataUtils.getMedicalRecords();
    }

}
