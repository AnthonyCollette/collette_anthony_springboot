package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonFireDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private final PersonService personService;

    public FloodController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/stations")
    public Map<String, List<PersonFireDTO>> getPersonsByStationNumbers(@RequestParam List<String> stations) {
        return personService.getPersonsByStationNumbers(stations);
    }
}
