package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private final PersonService personService;

    public ChildAlertController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> getChildrenByAddress(@RequestParam String address) {
        List<ChildDTO> children = personService.getChildrenByAddress(address);

        return ResponseEntity.ok(children);
    }

}
