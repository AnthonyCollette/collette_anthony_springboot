package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void testGetPhoneNumbers() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=2"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPhoneNumbers_withError() throws Exception {
        when(personService.getPhoneNumbers("2")).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(get("/phoneAlert?firestation=2"))
                .andExpect(status().isInternalServerError());
    }
}
