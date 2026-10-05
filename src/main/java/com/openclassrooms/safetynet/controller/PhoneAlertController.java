package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private final PersonService personService;

    public PhoneAlertController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getPhoneNumbers(@RequestParam String firestation) {

        List<String> numbers = personService.getPhoneNumbers(firestation);

        return ResponseEntity.ok(numbers);
    }

}
