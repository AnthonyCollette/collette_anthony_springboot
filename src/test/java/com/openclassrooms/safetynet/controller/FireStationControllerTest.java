package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FireStationService fireStationService;

    @Test
    void testAddFireStation() throws Exception {
        FireStation fireStation = new FireStation("1509 Culver St", "80");

        when(fireStationService.addFireStation(any(FireStation.class))).thenReturn(fireStation);

        mockMvc.perform(post("/firestation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateFireStation() throws Exception {
        FireStation fireStation = new FireStation("1509 Culver St", "80");

        when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(true);

        mockMvc.perform(put("/firestation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFireStation() throws Exception {
        FireStation fireStation = new FireStation("1509 Culver St", "3");

        when(fireStationService.deleteFireStation(any(FireStation.class))).thenReturn(true);

        mockMvc.perform(delete("/firestation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetPersonsByStationNumber() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk());
    }

}
