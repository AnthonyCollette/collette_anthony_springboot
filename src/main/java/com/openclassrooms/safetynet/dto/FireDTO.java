package com.openclassrooms.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireDTO {

    private List<PersonFireDTO> personFireDTOs = new ArrayList<>();
    private String stationNumber;

}
