package com.openclassrooms.safetynet.mapper;

import com.openclassrooms.safetynet.dto.PersonFireDTO;
import com.openclassrooms.safetynet.dto.PersonInfoDTO;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.DataUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final DataUtils dataUtils;

    public PersonMapper(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public PersonFireDTO toPersonFireDTO(Person person, MedicalRecord mr) {
        if (person == null || mr == null) {
            return null;
        }

        PersonFireDTO dto = new PersonFireDTO();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setPhone(person.getPhone());
        dto.setAge(dataUtils.calculateAge(mr.getBirthdate()));
        dto.setMedications(mr.getMedications());
        dto.setAllergies(mr.getAllergies());

        return dto;
    }

    public PersonInfoDTO toPersonInfoDTO(Person person, MedicalRecord mr) {
        if (person == null || mr == null) {
            return null;
        }

        PersonInfoDTO dto = new PersonInfoDTO();
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setAge(dataUtils.calculateAge(mr.getBirthdate()));
        dto.setEmail(person.getEmail());
        dto.setMedications(mr.getMedications());
        dto.setAllergies(mr.getAllergies());

        return dto;
    }

}
