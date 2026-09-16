package com.openclassrooms.safetynet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataWrapper {
    @JsonProperty("persons")
    private List<Person> persons;
    @JsonProperty("firestations")
    private List<FireStation> fireStations;
    @JsonProperty("medicalrecords")
    private List<MedicalRecord> medicalRecords;
}