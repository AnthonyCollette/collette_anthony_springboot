package com.openclassrooms.safetynet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPersonInfoByLastName() throws Exception {
        mockMvc.perform(get("/personInfolastName=Boyd"))
                .andExpect(status().isOk());
    }

}
