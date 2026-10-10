package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonFireDTO;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/flood")
public class FloodController {

    private final PersonService personService;

    public FloodController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/stations")
    public ResponseEntity<Map<String, List<PersonFireDTO>>> getPersonsByStationNumbers(@RequestParam List<String> stations) {
        log.info("Received GET request for /stations with stations: {}", stations);
        try {
            Map<String, List<PersonFireDTO>> persons = personService.getPersonsByStationNumbers(stations);

            log.info("Successfully processed /stations request for stations: {}", stations);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            log.error("Error occurred while processing /stations for address: {} - Message: {}", stations, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
