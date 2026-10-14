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
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void testGetEmailsByCity() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmailsByCity_whenError() throws Exception {
        when(personService.getEmailsByCity("Culver")).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isInternalServerError());
    }
}
