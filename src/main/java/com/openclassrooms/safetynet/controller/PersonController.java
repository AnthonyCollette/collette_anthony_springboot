package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("Received POST request for /person with person: {}", person);

        try {
            Person result = personService.addPerson(person);

            log.info("Successfully processed /person POST request for person: {}", person);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while processing /person for person: {} - Message: {}", person, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
        log.info("Received DELETE request for /person with person: {}", person);

        try {
            Boolean deleted = personService.deletePerson(person);

            log.info("Successfully processed /person DELETE request for person: {}", person);

            if (!deleted) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /person for person: {} - Message: {}", person, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
        log.info("Received PUT request for /person with person: {}", person);

        try {
            Boolean updated = personService.updatePerson(person);
            log.info("Successfully processed /person PUT request for person: {}", person);
            if (!updated) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error occurred while processing /person for person: {} - Message: {}", person, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }


}
