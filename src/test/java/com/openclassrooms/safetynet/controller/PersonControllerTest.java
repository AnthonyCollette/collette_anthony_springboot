package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PersonService personService;

    @Test
    void testAddPerson() throws Exception {
        Person person = new Person("Marie", "Curie", "1509 Culver St", "Culver", "97451",
                "841-874-6612", "mcurie@email.com");

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddPerson_withError() throws Exception {
        Person person = new Person("Marie", "Curie", "1509 Culver St", "Culver", "97451",
                "841-874-6612", "mcurie@email.com");

        when(personService.addPerson(person)).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDeletePerson() throws Exception {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd2@email.com");

        when(personService.deletePerson(any(Person.class))).thenReturn(true);

        mockMvc.perform(delete("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePerson_notFound() throws Exception {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd2@email.com");

        when(personService.deletePerson(any(Person.class))).thenReturn(false);

        mockMvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePerson_withError() throws Exception {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd2@email.com");

        when(personService.deletePerson(any(Person.class))).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person("John", "Boyd", "1510 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd@email.com");

        when(personService.updatePerson(any(Person.class))).thenReturn(true);

        mockMvc.perform(put("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdatePerson_notFound() throws Exception {
        Person person = new Person("John", "Boyd", "1510 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd@email.com");

        when(personService.updatePerson(any(Person.class))).thenReturn(false);

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdatePerson_withError() throws Exception {
        Person person = new Person("John", "Boyd", "1510 Culver St", "Culver", "97451",
                "841-874-6512", "jaboyd@email.com");

        when(personService.updatePerson(any(Person.class))).thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isInternalServerError());
    }



}
