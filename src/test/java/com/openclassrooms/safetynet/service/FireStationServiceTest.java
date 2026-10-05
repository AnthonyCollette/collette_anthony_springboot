package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private DataUtils dataUtils;

    @InjectMocks
    private FireStationService fireStationService;

    private FireStation fireStation;
    private Person adult;
    private Person child;


    @BeforeEach
    void setUp() {
        fireStation = new FireStation("1509 Culver St", "80");
        adult = new Person("John", "Boyd", "1509 Culver St", "Culver",
                "97451", "841-874-6512", "jaboyd@email.com");
        child = new Person("Tenley", "Boyd", "1509 Culver St", "Culver",
                "97451", "841-874-6512", "tenz@email.com");
    }

    @Test
    void testAddFireStation() {
        when(fireStationRepository.save(any(FireStation.class))).thenReturn(fireStation);

        FireStation result = fireStationService.addFireStation(fireStation);

        assertEquals(fireStation, result);
        verify(fireStationRepository).save(fireStation);
    }

    @Test
    void testUpdateFireStation() {
        when(fireStationRepository.update(any(FireStation.class))).thenReturn(true);

        Boolean updated = fireStationService.updateFireStation(fireStation);

        assertTrue(updated);
        verify(fireStationRepository).update(fireStation);
    }

    @Test
    void testDeleteFireStationByAddress() {
        when(fireStationRepository.deleteByAddress(anyString())).thenReturn(true);

        Boolean deleted = fireStationService.deleteFireStation(fireStation);

        assertTrue(deleted);
        verify(fireStationRepository).deleteByAddress(anyString());
    }

    @Test
    void testDeleteFireStationByStationNumber() {
        fireStation.setAddress(null);

        when(fireStationRepository.deleteByStationNumber(anyString())).thenReturn(true);

        Boolean deleted = fireStationService.deleteFireStation(fireStation);

        assertTrue(deleted);
        verify(fireStationRepository).deleteByStationNumber(anyString());
    }

    @Test
    void testGetPersonsByStationNumber() {
        List<String> addresses = List.of("1509 Culver St");

        when(fireStationRepository.findAddressesByStationNumber("3")).thenReturn(addresses);
        when(personRepository.findByAddresses(addresses)).thenReturn(List.of(adult, child));
        when(medicalRecordRepository.findByName("John", "Boyd")).thenReturn(new MedicalRecord("John", "Boyd", "03/06/1984", List.of(), List.of()));
        when(medicalRecordRepository.findByName("Tenley", "Boyd")).thenReturn(new MedicalRecord("Tenley", "Boyd", "02/18/2012", List.of(), List.of()));
        when(dataUtils.calculateAge("03/06/1984")).thenReturn(42);
        when(dataUtils.calculateAge("02/18/2012")).thenReturn(14);

        FireStationDTO result = fireStationService.getPersonsByStationNumber("3");

        assertEquals(2, result.getPersons().size());
        assertEquals(1, result.getAdultCount());
        assertEquals(1, result.getChildCount());
    }

}
