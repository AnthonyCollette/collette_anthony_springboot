package com.openclassrooms.safetynet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.DataWrapper;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataUtils {

    @Value("classpath:data/data.json")
    private Resource jsonResource;

    private final ObjectMapper objectMapper;

    @Getter
    private List<Person> persons = new ArrayList<>();
    @Getter
    private List<FireStation> fireStations = new ArrayList<>();
    @Getter
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public DataUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initData() {
        try {
            DataWrapper dataWrapper = objectMapper.readValue(jsonResource.getInputStream(), DataWrapper.class);
            this.persons = dataWrapper.getPersons();
            this.fireStations = dataWrapper.getFireStations();
            this.medicalRecords = dataWrapper.getMedicalRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            DataWrapper dataWrapper = new DataWrapper();
            dataWrapper.setPersons(this.persons);
            dataWrapper.setFireStations(this.fireStations);
            dataWrapper.setMedicalRecords(this.medicalRecords);

            File file = new File("src/main/resources/data/data.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dataWrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}