package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildDTO;
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
@RequestMapping("/childAlert")
public class ChildAlertController {

    private final PersonService personService;

    public ChildAlertController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<ChildDTO>> getChildrenByAddress(@RequestParam String address) {
        log.info("Received GET request for /childAlert with address: {}", address);

        try {
            List<ChildDTO> children = personService.getChildrenByAddress(address);

            log.info("Successfully processed /childAlert request for address: {} - Found {} child(ren)", address, children.size());
            return ResponseEntity.ok(children);
        } catch (Exception e) {
            log.error("Error occurred while processing /childAlert for address: {} - Message: {}", address, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }


    }

}
