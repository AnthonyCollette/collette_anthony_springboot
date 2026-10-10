package com.openclassrooms.safetynet.controller;

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
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private final PersonService personService;

    public PhoneAlertController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getPhoneNumbers(@RequestParam String firestation) {
        log.info("Received GET request for /phoneAlert with fire station: {}", firestation);
        try {
            List<String> numbers = personService.getPhoneNumbers(firestation);

            log.info("Successfully processed /phoneAlert request for fire station: {}", firestation);
            return ResponseEntity.ok(numbers);
        } catch (Exception e) {
            log.error("Error occurred while processing /phoneAlert for fire station: {} - Message: {}", firestation, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
