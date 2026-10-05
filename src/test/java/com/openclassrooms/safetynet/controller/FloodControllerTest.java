package com.openclassrooms.safetynet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPersonsByStationNumbers() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk());
    }

}
