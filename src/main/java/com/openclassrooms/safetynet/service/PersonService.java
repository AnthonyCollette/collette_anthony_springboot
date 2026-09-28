package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.ChildDTO;
import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.dto.PersonFireDTO;
import com.openclassrooms.safetynet.dto.PersonInfoDTO;
import com.openclassrooms.safetynet.mapper.PersonMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final DataUtils dataUtils;
    private final FireStationRepository fireStationRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, DataUtils dataUtils, FireStationRepository fireStationRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.dataUtils = dataUtils;
        this.fireStationRepository = fireStationRepository;
        this.personMapper = personMapper;
    }

    /**
     * Adds a new person.
     * @param person
     * @return {@link Person}
     */

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Deletes a person.
     * @param person
     * @return {@link Boolean}
     */

    public boolean deletePerson(Person person) { return personRepository.delete(person); }

    /**
     * Updates an existent person.
     * @param person
     * @return {@link Boolean}
     */

    public boolean updatePerson(Person person) { return personRepository.update(person); }

    /**
     * Gets the list of children living at the given address.
     * @param address the address to search for
     * @return the {@link List} of {@link ChildDTO} containing children and other household members
     */

    public List<ChildDTO> getChildrenByAddress(String address) {

        List<Person> houseHoldPersons = personRepository.findByAddresses(List.of(address));
        List<ChildDTO> childrenList = new ArrayList<>();

        for (Person person : houseHoldPersons) {
            MedicalRecord mr = medicalRecordRepository.findByName(person.getFirstName(), person.getLastName());

            if (mr != null) {
                int age = dataUtils.calculateAge(mr.getBirthdate());

                if (age < 18) {
                    List<Person> otherPersons = new ArrayList<>();
                    for (Person member : houseHoldPersons) {
                        if (!member.equals(person)) {
                            otherPersons.add(member);
                        }
                    }

                    ChildDTO childDTO = new ChildDTO(
                            person.getFirstName(),
                            person.getLastName(),
                            age,
                            otherPersons
                    );

                    childrenList.add(childDTO);
                }
            }
        }

        return childrenList;

    }

    /**
     * Gets the list of phone numbers covered by the given fire station.
     * @param fireStation the fire station number to search for
     * @return the {@link List} of {@link String} representing phone numbers
     */

    public List<String> getPhoneNumbers(String fireStation) {
        List<String> addresses = fireStationRepository.findAddressesByStationNumber(fireStation);
        List<Person> persons = personRepository.findByAddresses(addresses);
        List<String> phoneNumbers = new ArrayList<>();

        for ( Person person : persons) {
            phoneNumbers.add(person.getPhone());
        }

        return phoneNumbers;
    }

    /**
     * Gets the map of the station number along with the list of persons covered by the fire station.
     * @param stationNumbers the list of fire station numbers
     * @return a {@link Map} containing station numbers as keys
     * and a {@link List} of {@link  PersonFireDTO} as values
     *
     */

    public Map<String, List<PersonFireDTO>> getPersonsByStationNumbers(List<String> stationNumbers) {

        List<String> addresses = stationNumbers.stream().flatMap(sn -> fireStationRepository.findAddressesByStationNumber(sn).stream())
        .distinct().toList();

        Map<String, List<PersonFireDTO>> households = new HashMap<>();


        for (String address : addresses) {

            List<Person> persons = personRepository.findByAddresses(List.of(address));

            List<PersonFireDTO> residents = persons.stream().map(person -> {
                MedicalRecord mr = medicalRecordRepository.findByName(person.getFirstName(), person.getLastName());
                return personMapper.toPersonFireDTO(person, mr);
            }).filter(Objects::nonNull).toList();

            households.put(address, residents);
        }

        return households;
    }

    /**
     * Gets residents information living at the given address along with the serving fire station number.
     * @param address the address to search for
     * @return a {@link FireDTO} containing residents medical details and the station number
     */

    public FireDTO getResidentsInfosByAddress(String address) {
        List<Person> persons = personRepository.findByAddresses(List.of(address));
        FireDTO fireDTO = new FireDTO();

        for (Person person : persons) {
            MedicalRecord mr = medicalRecordRepository.findByName(person.getFirstName(), person.getLastName());

            PersonFireDTO personFireDTO = personMapper.toPersonFireDTO(person, mr);

            if (personFireDTO != null) {
                fireDTO.getPersonFireDTOs().add(personFireDTO);
            }
        }

        fireDTO.setStationNumber(fireStationRepository.findStationNumberByAddress(address));

        return fireDTO;
    }

    /**
     * Gets person information corresponding to the given last name.
     * @param lastName the last name to search for
     * @return a {@link List} of {@link PersonInfoDTO} containing personal and medical details
     */

    public List<PersonInfoDTO> getPersonInfosByLastName(String lastName) {
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();

        List<Person> persons = personRepository.findByLastName(lastName);

        for (Person person : persons) {
            MedicalRecord mr = medicalRecordRepository.findByName(person.getFirstName(), person.getLastName());

            PersonInfoDTO dto = personMapper.toPersonInfoDTO(person, mr);
            personInfoDTOs.add(dto);
        }

        return personInfoDTOs;
    }

    /**
     * Gets email addresses of persons living in the given city.
     * @param city the city to search for
     * @return a {@link List} of {@link String} representing email addresses
     */

    public List<String> getEmailsByCity(String city) {
        List<String> emails = new ArrayList<>();

        List<Person> persons = personRepository.findByCity(city);

        for (Person person : persons) {
            emails.add(person.getEmail());
        }

        return emails;
    }
}
