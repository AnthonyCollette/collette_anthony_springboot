package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.dto.PersonFireDTO;
import com.openclassrooms.safetynet.mapper.PersonMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    private final DataUtils dataUtils;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;
    private final PersonMapper personMapper;

    public PersonRepository(DataUtils dataUtils, MedicalRecordRepository medicalRecordRepository, FireStationRepository fireStationRepository, PersonMapper personMapper) {
        this.dataUtils = dataUtils;
        this.medicalRecordRepository = medicalRecordRepository;
        this.fireStationRepository = fireStationRepository;
        this.personMapper = personMapper;
    }

    public List<Person> findAll() {
        return dataUtils.getPersons();
    }

    public Person save(Person person) {
        dataUtils.getPersons().add(person);
        dataUtils.saveData();
        return person;
    }

    public boolean delete(Person person) {

        boolean removed = dataUtils.getPersons().removeIf(p ->
                p.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
                p.getLastName().equalsIgnoreCase(person.getLastName()));

        if (removed) {
            dataUtils.saveData();
            return true;
        }

        return false;
    }

    public boolean update(Person person) {
        Optional<Person> personToUpdate = dataUtils.getPersons().stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName()) && p.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst();

        if (personToUpdate.isPresent()) {
            Person p = personToUpdate.get();
            p.setAddress(person.getAddress());
            p.setCity(person.getCity());
            p.setZip(person.getZip());
            p.setPhone(person.getPhone());
            p.setEmail(person.getEmail());

            dataUtils.saveData();
            return true;
        }
        return false;
    }

    public List<Person> findByAddresses(List<String> addresses) {
        return dataUtils.getPersons().stream()
                .filter(p -> addresses.contains(p.getAddress()))
                .toList();
    }

    public List<Person> findByLastName(String lastName) {
        return dataUtils.getPersons().stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    public List<Person> findByCity(String city) {
        return dataUtils.getPersons().stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .toList();
    }
}
