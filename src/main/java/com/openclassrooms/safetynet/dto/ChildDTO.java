package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    private String firstName;
    private String lastName;
    private int age;
    private List<Person> houseHoldPersons;
}
