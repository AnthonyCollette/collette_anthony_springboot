package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void testGetResidentsByAddress() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetResidentsByAddress_whenError() throws Exception {
        when(personService.getResidentsInfosByAddress("1509 Culver St")).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isInternalServerError());
    }

}
