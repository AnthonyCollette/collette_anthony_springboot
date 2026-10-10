package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonInfoDTO;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PersonInfoController {

    private final PersonService personService;

    public PersonInfoController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/personInfolastName={lastName}")
    public ResponseEntity<List<PersonInfoDTO>> getPersonInfoByLastName(@PathVariable("lastName") String lastName) {
        log.info("Received GET request for /personInfolastName with last name: {}", lastName);
        try {
            List<PersonInfoDTO> personInfos = personService.getPersonInfosByLastName(lastName);
            log.info("Successfully processed /personInfolastName request for last name: {}", lastName);
            return ResponseEntity.ok(personInfos);
        } catch (Exception e) {
            log.error("Error occurred while processing /personInfolastName for last name: {} - Message: {}", lastName, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
