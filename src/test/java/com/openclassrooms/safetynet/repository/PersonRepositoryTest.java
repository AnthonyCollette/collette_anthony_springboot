package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    private Person person;
    private List<Person> persons;

    @InjectMocks
    private PersonRepository personRepository;

    @Mock
    private DataUtils dataUtils;

    @BeforeEach
    void setUp() {
        person = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                "841-874-6513", "drk@email.com");
        persons = new ArrayList<>(List.of(person));
    }


    @Test
    void testFindAll() {
        when(dataUtils.getPersons()).thenReturn(persons);

        List<Person> result = personRepository.findAll();

        assertEquals(result, persons);
        verify(dataUtils).getPersons();
    }

    @Test
    void testSave() {
        when(dataUtils.getPersons()).thenReturn(persons);

        Person result = personRepository.save(person);

        assertEquals(result, person);
        verify(dataUtils).saveData();
    }

    @Test
    void testDelete() {
        when(dataUtils.getPersons()).thenReturn(persons);

        Boolean deleted = personRepository.delete(person);

        assertTrue(deleted);
        verify(dataUtils).saveData();
    }

    @Test
    void testUpdate() {
        when(dataUtils.getPersons()).thenReturn(persons);

        Boolean updated = personRepository.update(person);

        assertTrue(updated);
        verify(dataUtils).saveData();
    }

}
