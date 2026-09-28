package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationDTO;
import com.openclassrooms.safetynet.dto.PersonDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final DataUtils dataUtils;

    public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, DataUtils dataUtils) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.dataUtils = dataUtils;
    }

    /**
     * Adds a new fire station.
     *
     * @param fireStation
     * @return {@link FireStation}
     */
    public FireStation addFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    /**
     * Updates an existent fire station.
     *
     * @param fireStation
     * @return {@link Boolean}
     */

    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepository.update(fireStation);
    }

    /**
     * Deletes a fire station.
     *
     * @param fireStation
     * @return {@link Boolean}
     */

    public boolean deleteFireStation(FireStation fireStation) {
        if (fireStation.getAddress() != null && !fireStation.getAddress().isBlank()) {
            return fireStationRepository.deleteByAddress(fireStation.getAddress());
        }

        if (fireStation.getStation() != null && !fireStation.getStation().isBlank()) {
            return fireStationRepository.deleteByStationNumber(fireStation.getStation());
        }

        return false;
    }

    /**
     * Gets the list of persons covered by station number,
     * along with the count of children and adults.
     *
     * @param stationNumber the fire station number
     * @return the {@link FireStationDTO} containing persons and demographic counts
     */

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
                int age = dataUtils.calculateAge(mr.getBirthdate());
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
