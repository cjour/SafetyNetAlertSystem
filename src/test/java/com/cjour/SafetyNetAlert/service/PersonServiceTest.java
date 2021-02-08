package com.cjour.SafetyNetAlert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjour.SafetyNetAlert.DTO.PersonDTOChild;
import com.cjour.SafetyNetAlert.DTO.PersonDTOEmail;
import com.cjour.SafetyNetAlert.DTO.PersonDTOFireStation;
import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.repository.Database;

@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonServiceImpl personService;

	private static ArrayList<Person> listOfPerson;

	@MockBean
	// Pourquoi @Bean ne fonctionne pas mais @MockBean fonctionne ??????
	private Database database;

	@BeforeAll
	public static void setUp() {
		
		Person james = new Person("James", "Bond", "MI5", "London", "England", "007", "JB@MI5.uk");
		james.setAge(40);
		james.setFireStation(new FireStation("London fire departement", 1));
		
		Person hubert = new Person("Hubert", "Bonisseur de la Bath", "OSS", "Paris", "France", "117", "HB@OSS.fr");
		hubert.setAge(40);
		hubert.setFireStation(new FireStation("Pompier de Paris", 2));
		
		Person conan = new Person("Conan", "Edogawa", "Tivedétec", "Tokyo", "Japon", "123", "Conan@Tivedétec.jp");
		conan.setAge(6);
		conan.setFireStation(new FireStation("Tokyo fire station", 3));
		
		listOfPerson = new ArrayList<Person>();
		listOfPerson.add(james);
		listOfPerson.add(hubert);
		listOfPerson.add(conan);
		
	}

	@Test
	public void getPersonTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		Person result = personService.getPerson("James", "Bond");

		// THEN
		assertTrue(result.getFirstName() == "James");
		assertTrue(result.getLastName() == "Bond");
		assertTrue(result.getAddress() == "MI5");
		assertTrue(result.getCity() == "London");
		assertTrue(result.getZip() == "England");
		assertTrue(result.getPhone() == "007");
		assertTrue(result.getEmail() == "JB@MI5.uk");
	}

	@Test
	public void noChildFoundTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOChild> result = personService.getChild("MI5");

		// THEN
		assertTrue(result.isEmpty());
	}

	@Test
	public void childFoundTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOChild> result = personService.getChild("Tivedétec");

		// THEN
		assertFalse(result.isEmpty());
		for (PersonDTOChild personDTOChild : result) {
			assertTrue(personDTOChild.getFirstName().equals("Conan"));
			assertTrue(personDTOChild.getLastName().equals("Edogawa"));
			assertTrue(personDTOChild.getAge() <= 18);
		}
	}
	
	@Test
	public void noEmailFoundTest() {
		//GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);
		
		//WHEN
		ArrayList<PersonDTOEmail> result = personService.getEmail("Unexisting city");
		
		//THEN
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void emailFoundTest() {
		//GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);
		
		//WHEN
		ArrayList<PersonDTOEmail> result = personService.getEmail("Paris");
		
		//THEN
		assertFalse(result.isEmpty());
		for (PersonDTOEmail personDTOEmail : result) {
			assertTrue(personDTOEmail.getEmail().equals("HB@OSS.fr"));
		}
	}
	
	@Test
	public void noPersonFoundRelatedToFireStation() {
		//GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);	
		
		//WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(666);
		
		//THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result.get(0);
		assertTrue(listOfPersonRelatedToThisFireStation.isEmpty());
		
		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>)result.get(1);
        int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
        int numberOfMajor = listOfMinorAndMajor.get("Number of major");
        
        assertTrue(numberOfMinor == 0);
        assertTrue(numberOfMajor == 0);
		
	}
	
	@Test
	public void personFoundRelatedToFireStationAndIsMajor() {
		//GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);	
		
		//WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(1);
		
		//THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result.get(0);
		assertFalse(listOfPersonRelatedToThisFireStation.isEmpty());
		for (PersonDTOFireStation personDTOFireStation : listOfPersonRelatedToThisFireStation) {
			assertTrue(personDTOFireStation.getFirstName().equals("James"));
			assertTrue(personDTOFireStation.getLastName() == "Bond");
			assertTrue(personDTOFireStation.getAddress() == "MI5");
			assertTrue(personDTOFireStation.getPhone() == "007");	
		}
		
		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>)result.get(1);
        int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
        int numberOfMajor = listOfMinorAndMajor.get("Number of major");
        
        assertTrue(numberOfMinor == 0);
        assertTrue(numberOfMajor == 1);
		
	}
	
	@Test
	public void personFoundRelatedToFireStationAndIsMinor() {
		//GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);	
		
		//WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(3);
		
		//THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result.get(0);
		assertFalse(listOfPersonRelatedToThisFireStation.isEmpty());
		for (PersonDTOFireStation personDTOFireStation : listOfPersonRelatedToThisFireStation) {
			assertTrue(personDTOFireStation.getFirstName().equals("Conan"));
			assertTrue(personDTOFireStation.getLastName() == "Edogawa");
			assertTrue(personDTOFireStation.getAddress() == "Tivedétec");
			assertTrue(personDTOFireStation.getPhone() == "123");	
		}
		
		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>)result.get(1);
        int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
        int numberOfMajor = listOfMinorAndMajor.get("Number of major");
        
        assertTrue(numberOfMinor == 1);
        assertTrue(numberOfMajor == 0);
		
	}
}
