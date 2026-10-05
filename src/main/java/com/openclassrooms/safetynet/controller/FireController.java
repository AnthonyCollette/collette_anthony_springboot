package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire")
public class FireController {

    private final PersonService personService;

    public FireController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<FireDTO> getResidentsByAddress(@RequestParam String address) {
        FireDTO residents = personService.getResidentsInfosByAddress(address);

        return ResponseEntity.ok(residents);
    }

}
