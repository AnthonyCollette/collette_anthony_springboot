package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private final PersonService personService;

    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getEmailsByCity(@RequestParam String city) {
        List<String> emails = personService.getEmailsByCity(city);

        return ResponseEntity.ok(emails);
    }

}
