package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                "97451", "841-874-6513", "drk@email.com");
    }

    @Test
    void testAddPerson() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.addPerson(person);

        assertEquals(result, person);
        verify(personRepository).save(person);
    }

    @Test
    void testDeletePerson() {
        when(personRepository.delete(any(Person.class))).thenReturn(true);

        Boolean result = personService.deletePerson(person);

        assertTrue(result);
        verify(personRepository).delete(person);
    }

    @Test
    void testUpdatePerson() {
        when(personRepository.update(any(Person.class))).thenReturn(true);

        Boolean result = personService.updatePerson(person);

        assertTrue(true);
        verify(personRepository).update(person);
    }

}
