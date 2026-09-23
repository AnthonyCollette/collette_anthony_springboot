package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationDTO;
import com.openclassrooms.safetynet.dto.PersonDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    private int calculateAge(String birthdate) {
        LocalDate birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public FireStation addFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepository.update(fireStation);
    }

    public boolean deleteFireStation(FireStation fireStation) {
        if (fireStation.getAddress() != null && !fireStation.getAddress().isBlank()) {
            return fireStationRepository.deleteByAddress(fireStation.getAddress());
        }

        if (fireStation.getStation() != null && !fireStation.getStation().isBlank()) {
            return fireStationRepository.deleteByStationNumber(fireStation.getStation());
        }

        return false;
    }

    public FireStationDTO getPersonsByStationNumber(String stationNumber) {

        int adultCount = 0;
        int childCount = 0;

        List<String> addresses = fireStationRepository.findAddressesByStationNumber(stationNumber);
        List<Person> persons = personRepository.findByAddresses(addresses);
        List<PersonDTO> personDTOs = persons.stream()
                .map(person -> new PersonDTO(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getAddress(),
                        person.getPhone()
                ))
                .toList();

        for (Person person : persons) {
            MedicalRecord mr = medicalRecordRepository.findByName(person.getFirstName(), person.getLastName());

            if (mr != null) {
                int age = calculateAge(mr.getBirthdate());
                if (age >= 18) {
                    adultCount++;
                } else {
                    childCount++;
                }
            }
        }

        FireStationDTO fireStationDTO = new FireStationDTO();
        fireStationDTO.setPersons(personDTOs);
        fireStationDTO.setAdultCount(adultCount);
        fireStationDTO.setChildCount(childCount);

        return fireStationDTO;
    }

}
