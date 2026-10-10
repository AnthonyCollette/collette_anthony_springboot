package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/fire")
public class FireController {

    private final PersonService personService;

    public FireController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<FireDTO> getResidentsByAddress(@RequestParam String address) {
        log.info("Received GET request for /fire with address: {}", address);

        try {
            FireDTO residents = personService.getResidentsInfosByAddress(address);

            log.info("Successfully processed /fire request for address: {}", address);
            return ResponseEntity.ok(residents);
        } catch (Exception e) {
            log.error("Error occurred while processing /fire for address: {} - Message: {}", address, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
