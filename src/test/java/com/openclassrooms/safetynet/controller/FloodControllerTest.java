package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void testGetPersonsByStationNumbers() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonsByStationNumbers_withError() throws Exception {
        when(personService.getPersonsByStationNumbers(List.of("1", "2"))).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isInternalServerError());
    }

}
