package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping
    public boolean deletePerson(@RequestBody Person person) {
        return personService.deletePerson(person);
    }

    @PutMapping
    public boolean updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

}
