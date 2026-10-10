package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private final PersonService personService;

    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getEmailsByCity(@RequestParam String city) {
        log.info("Received GET request for /communityEmail with city: {}", city);

        try {
            List<String> emails = personService.getEmailsByCity(city);

            log.info("Successfully processed /communityEmail request for city: {} - Found {} emails", city, emails.size());
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            log.error("Error occurred while processing /communityEmail for city: {} - Message: {}", city, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }


    }

}
