package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.DataWrapper;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    private final DataUtils dataUtils;

    public PersonRepository(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public List<Person> findAll() {
        return dataUtils.getPersons();
    }

    public Person save(Person person) {
        dataUtils.getPersons().add(person);
        dataUtils.saveData();
        return person;
    }

}
