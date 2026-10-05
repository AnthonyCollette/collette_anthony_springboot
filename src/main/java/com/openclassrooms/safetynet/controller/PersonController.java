package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person result = personService.addPerson(person);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
        Boolean deleted = personService.deletePerson(person);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
        Boolean updated = personService.updatePerson(person);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}
