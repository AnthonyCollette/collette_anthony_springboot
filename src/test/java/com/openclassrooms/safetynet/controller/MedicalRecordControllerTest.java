package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MedicalRecordService medicalRecordService;

    @Test
    void testAddMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("Paul", "Jones", "02/18/1990",
                List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"));

        mockMvc.perform(post("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"), List.of());

        when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(true);

        mockMvc.perform(put("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("peanut", "apple"));

        when(medicalRecordService.deleteMedicalRecord(any(MedicalRecord.class))).thenReturn(true);

        mockMvc.perform(delete("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isNoContent());
    }

}
