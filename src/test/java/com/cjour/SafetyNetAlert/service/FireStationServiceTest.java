package com.cjour.SafetyNetAlert.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.repository.Database;

@SpringBootTest
public class FireStationServiceTest {

	@Autowired
	FireStationServiceImpl fireStationService;
	
	@MockBean
	private Database database;
	
	private ArrayList<FireStation> listOfFireStations = new ArrayList<>();

	private FireStation fireStation1 = new FireStation("Delhi", 1);
	private FireStation fireStation2 = new FireStation("Agra", 2);
	private FireStation fireStation3 = new FireStation("Udaipur", 3);
	
	@BeforeEach
	public void setUp() {

		listOfFireStations.add(fireStation1);
		listOfFireStations.add(fireStation2);
		listOfFireStations.add(fireStation3);
	}
	
	@Test
	public void findAllTest() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		
		//WHEN
		ArrayList<FireStation> result = fireStationService.findAll();
		
		//THEN
		assertFalse(result.isEmpty());
		assertTrue(result.get(0).equals(fireStation1));
		assertTrue(result.get(1).equals(fireStation2));
		assertTrue(result.get(2).equals(fireStation3));		
	}
	
	@Test
	public void getFireStationTest() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		
		//WHEN
		FireStation result = fireStationService.getFireStation("Delhi");
		
		//THEN
		assertTrue(result.equals(fireStation1));
	}
	
	@Test
	public void getFireStationWithNumber() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		
		//WHEN
		ArrayList<FireStation> result = fireStationService.getFireStationWithNumber(3);
		
		//THEN
		assertFalse(result.isEmpty());
		assertTrue(result.get(0).equals(fireStation3));
	}
	
	@Test
	public void addAFireStationTest() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		FireStation fireStation4 = new FireStation("Jaipur", 4);
		
		//WHEN
		boolean result = fireStationService.addAFireStation(fireStation4);
		
		//THEN
		assertTrue(result);
		assertTrue(database.getFireStationList().get(3).equals(fireStation4));
	}
	
	@Test
	public void deleteTest() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		
		//WHEN
		boolean result = fireStationService.delete(fireStation2);
		
		//THEN
		
		assertTrue(result);
		for (FireStation fireStation : listOfFireStations) {
			assertFalse(fireStation.equals(fireStation2));
		}
	}
	
	@Test
	public void updateFireStationTest() {
		//GIVEN
		when(database.getFireStationList()).thenReturn(listOfFireStations);
		
		
		//WHEN
		boolean result = fireStationService.updateFireStation(fireStation2, 8);

		
		//THEN
		assertTrue(result);
		assertTrue(listOfFireStations.get(1).getStation() == 8);
	}
}
