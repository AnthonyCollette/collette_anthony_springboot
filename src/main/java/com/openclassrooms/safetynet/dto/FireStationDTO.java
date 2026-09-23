package com.openclassrooms.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireStationDTO {

    private List<PersonDTO> persons;
    private int adultCount;
    private int childCount;

}
