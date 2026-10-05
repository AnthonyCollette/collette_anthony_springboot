package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationRepositoryTest {

    private FireStation fireStation;
    private List<FireStation> fireStations;

    @InjectMocks
    private FireStationRepository fireStationRepository;

    @Mock
    private DataUtils dataUtils;

    @BeforeEach
    void setUp() {
        fireStation = new FireStation("1509 Culver St", "3");
        fireStations = new ArrayList<>();
    }

    @Test
    void testFindAll() {
        when(dataUtils.getFireStations()).thenReturn(fireStations);

        List<FireStation> result = fireStationRepository.findAll();

        assertEquals(result, fireStations);
        verify(dataUtils).getFireStations();

    }

    @Test
    void testSave() {
        when(dataUtils.getFireStations()).thenReturn(fireStations);

        FireStation result = fireStationRepository.save(fireStation);

        assertEquals(result, fireStation);
        assertTrue(fireStations.contains(fireStation));
        verify(dataUtils).saveData();
    }

    @Test
    void testUpdate() {
        when(dataUtils.getFireStations()).thenReturn(new ArrayList<>(List.of(fireStation)));

        Boolean updated = fireStationRepository.update(fireStation);

        assertTrue(updated);
        verify(dataUtils).saveData();
    }

    @Test
    void testDeleteByAddress() {

        String address = "1509 Culver St";
        when(dataUtils.getFireStations()).thenReturn(new ArrayList<>(List.of(fireStation)));

        Boolean deleted = fireStationRepository.deleteByAddress(address);

        assertTrue(deleted);
        verify(dataUtils).saveData();
    }

    @Test
    void testDeleteByStationNumber() {

        when(dataUtils.getFireStations()).thenReturn(new ArrayList<>(List.of(fireStation)));

        Boolean deleted = fireStationRepository.deleteByStationNumber("3");

        assertTrue(deleted);
        verify(dataUtils).saveData();
    }

}
