package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonInfoDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {

    private final PersonService personService;

    public PersonInfoController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/personInfolastName={lastName}")
    public ResponseEntity<List<PersonInfoDTO>> getPersonInfoByLastName(@PathVariable("lastName") String lastName) {
        List<PersonInfoDTO> personInfos = personService.getPersonInfosByLastName(lastName);

        return ResponseEntity.ok(personInfos);
    }
}
