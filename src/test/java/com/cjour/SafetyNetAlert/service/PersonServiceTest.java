package com.cjour.SafetyNetAlert.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.*;
import com.cjour.SafetyNetAlert.repository.Database;

@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonServiceImpl personService;

	private static ArrayList<Person> listOfPerson;
	private static ArrayList<FireStation> listOfFirestation;
	private static Person conan;

	@MockBean
	private Database database;

	@BeforeEach
	public void setUp() {

		Person james = new Person("James", "Bond", "MI5", "London", "England", "007", "JB@MI5.uk");
		james.setAge(40);
		james.setFireStation(new FireStation("London fire departement", 1));

		Person hubert = new Person("Hubert", "Bonisseur de la Bath", "OSS", "Paris", "France", "117", "HB@OSS.fr");
		hubert.setAge(40);
		hubert.setFireStation(new FireStation("Pompier de Paris", 2));

		conan = new Person("Conan", "Edogawa", "Tivedétec", "Tokyo", "Japon", "123", "Conan@Tivedétec.jp");
		conan.setAge(6);
		conan.setFireStation(new FireStation("Tokyo fire station", 3));
		conan.setMedicalRecord(
				new MedicalRecord("Conan", "Edogawa", "", new ArrayList<String>(), new ArrayList<String>()));

		listOfPerson = new ArrayList<Person>();
		listOfPerson.add(james);
		listOfPerson.add(hubert);
		listOfPerson.add(conan);

		listOfFirestation = new ArrayList<>();
		listOfFirestation.add(james.getFireStation());
		listOfFirestation.add(hubert.getFireStation());
		listOfFirestation.add(conan.getFireStation());

	}

	@Test
	public void findAllTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<Person> result = personService.findAll();
		Person james = result.get(0);
		Person hubert = result.get(1);
		Person conan = result.get(2);

		// THEN
		assertFalse(result.isEmpty());
		assertTrue(james.getAddress().equals("MI5"));
		assertTrue(hubert.getAddress().equals("OSS"));
		assertTrue(conan.getAddress().equals("Tivedétec"));
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
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOEmail> result = personService.getEmail("Unexisting city");

		// THEN
		assertTrue(result.isEmpty());
	}

	@Test
	public void emailFoundTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOEmail> result = personService.getEmail("Paris");

		// THEN
		assertFalse(result.isEmpty());
		for (PersonDTOEmail personDTOEmail : result) {
			assertTrue(personDTOEmail.getEmail().equals("HB@OSS.fr"));
		}
	}

	@Test
	public void noPersonFoundRelatedToFireStation() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(666);

		// THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result
				.get(0);
		assertTrue(listOfPersonRelatedToThisFireStation.isEmpty());

		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>) result.get(1);
		int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
		int numberOfMajor = listOfMinorAndMajor.get("Number of major");

		assertTrue(numberOfMinor == 0);
		assertTrue(numberOfMajor == 0);

	}

	@Test
	public void personFoundRelatedToFireStationAndIsMajor() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(1);

		// THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result
				.get(0);
		assertFalse(listOfPersonRelatedToThisFireStation.isEmpty());
		for (PersonDTOFireStation personDTOFireStation : listOfPersonRelatedToThisFireStation) {
			assertTrue(personDTOFireStation.getFirstName().equals("James"));
			assertTrue(personDTOFireStation.getLastName() == "Bond");
			assertTrue(personDTOFireStation.getAddress() == "MI5");
			assertTrue(personDTOFireStation.getPhone() == "007");
		}

		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>) result.get(1);
		int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
		int numberOfMajor = listOfMinorAndMajor.get("Number of major");

		assertTrue(numberOfMinor == 0);
		assertTrue(numberOfMajor == 1);

	}

	@Test
	public void personFoundRelatedToFireStationAndIsMinor() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(3);

		// THEN
		ArrayList<PersonDTOFireStation> listOfPersonRelatedToThisFireStation = (ArrayList<PersonDTOFireStation>) result
				.get(0);
		assertFalse(listOfPersonRelatedToThisFireStation.isEmpty());
		for (PersonDTOFireStation personDTOFireStation : listOfPersonRelatedToThisFireStation) {
			assertTrue(personDTOFireStation.getFirstName().equals("Conan"));
			assertTrue(personDTOFireStation.getLastName() == "Edogawa");
			assertTrue(personDTOFireStation.getAddress() == "Tivedétec");
			assertTrue(personDTOFireStation.getPhone() == "123");
		}

		HashMap<String, Integer> listOfMinorAndMajor = (HashMap<String, Integer>) result.get(1);
		int numberOfMinor = listOfMinorAndMajor.get("Number of minor");
		int numberOfMajor = listOfMinorAndMajor.get("Number of major");

		assertTrue(numberOfMinor == 1);
		assertTrue(numberOfMajor == 0);

	}

	@Test
	public void noPhoneNumberFound() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOPhone> result = personService.getPhoneNumberForSpecificFirestation(666);

		// THEN
		assertTrue(result.isEmpty());
	}

	@Test
	public void phoneNumberFound() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOPhone> result = personService.getPhoneNumberForSpecificFirestation(2);

		// THEN
		assertFalse(result.isEmpty());
		for (PersonDTOPhone personDTOPhone : result) {
			assertTrue(personDTOPhone.getPhone().equals("117"));
		}
	}

	@Test
	public void noPersonFoundForThisAddress() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<Object> result = personService.getPersonRelatedToThisAddress("OSS");
		Integer fireStation = (Integer) result.get(0);
		ArrayList<PersonDTOAddress> listOfPersonRelatedToThisAddress = (ArrayList<PersonDTOAddress>) result.get(1);
		PersonDTOAddress hubert = listOfPersonRelatedToThisAddress.get(0);

		assertTrue(fireStation.equals(2));
		assertFalse(listOfPersonRelatedToThisAddress.isEmpty());
		assertTrue(hubert.getLastName().equals("Bonisseur de la Bath"));
		assertTrue(hubert.getAge() == 40);
		assertTrue(hubert.getPhone().equals("117"));
		assertNull(hubert.getMedicalRecord());

	}

	@Test
	public void getPersonByTheirFirstNameAndLastNameTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		ArrayList<PersonDTOInfo> result = personService.getPersonByTheirFirstNameAndLastName("Edogawa", "Conan");

		// THEN
		assertFalse(result.isEmpty());
		for (PersonDTOInfo personDTOInfo : result) {
			assertTrue(personDTOInfo.getLastName().equals("Edogawa"));
			assertTrue(personDTOInfo.getAddress().equals("Tivedétec"));
			assertTrue(personDTOInfo.getAge() == 6);
			assertTrue(personDTOInfo.getEmail().equals("Conan@Tivedétec.jp"));
			assertTrue(personDTOInfo.getMedicalRecord().getAllergies().isEmpty());
			assertTrue(personDTOInfo.getMedicalRecord().getMedications().isEmpty());
		}
	}

	@Test
	public void getHomeRelatedToFireStationsTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);
		when(database.getFireStationList()).thenReturn(listOfFirestation);

		int[] fireStations = new int[] { 2, 3 };

		// WHEN
		HashMap<String, ArrayList<PersonDTOFireStations>> result = personService
				.getHomeRelatedToFireStation(fireStations);

		// THEN
		assertFalse(result.isEmpty());
		ArrayList<PersonDTOFireStations> listOfPersonForPompierDeParis = result.get("Pompier de Paris");
		assertTrue(listOfPersonForPompierDeParis.get(0).getLastName().equals("Bonisseur de la Bath"));
		assertTrue(listOfPersonForPompierDeParis.get(0).getPhone().equals("117"));
		assertTrue(listOfPersonForPompierDeParis.get(0).getAge() == 40);
		assertNull(listOfPersonForPompierDeParis.get(0).getMedicalRecord());

		ArrayList<PersonDTOFireStations> listOfPersonForTokyoFireStation = result.get("Tokyo fire station");

		assertTrue(listOfPersonForTokyoFireStation.get(0).getLastName().equals("Edogawa"));
		assertTrue(listOfPersonForTokyoFireStation.get(0).getPhone().equals("123"));
		assertTrue(listOfPersonForTokyoFireStation.get(0).getMedicalRecord().getBirthdate().equals(""));
		assertTrue(listOfPersonForTokyoFireStation.get(0).getMedicalRecord().getMedications().isEmpty());
		assertTrue(listOfPersonForTokyoFireStation.get(0).getMedicalRecord().getAllergies().isEmpty());

	}

	@Test
	public void deleteAPersonTest() {
		// GIVEN
		when(database.getPersonList()).thenReturn(listOfPerson);

		// WHEN
		boolean result = personService.delete(conan);

		// THEN
		assertTrue(result);
		for (Person person : listOfPerson) {
			assertFalse(person.getFirstName().equals("Conan"));
		}
	}

	@Test
	public void addAPerson() {
		// GIVEN
		Person person = new Person("Clément", "Jourdain", "", "", "", "", "");
		when(database.getPersonList()).thenReturn(listOfPerson);
		
		// WHEN
		boolean result = personService.addAPerson(person);

		// THEN
		assertTrue(result);
	}
	
	@Test
	public void addAPersonError() {
		// GIVEN
		Person person = new Person("Conan", "Edogawa", "", "", "", "", "");
		when(database.getPersonList()).thenReturn(listOfPerson);
		
		// WHEN
		boolean result = personService.addAPerson(person);

		// THEN
		assertFalse(result);
	}
	
	@Test
	public void updateAPersonTest() {
		
		when(database.getPersonList()).thenReturn(listOfPerson);
		Person getPerson = personService.getPerson("James", "Bond");
		Person person = new Person();
		person.setFirstName("James");
		person.setLastName("Bond");
		person.setAddress("La grange dans le cantal");
		person.setEmail("JB@MITRetired.uk");
		person.setPhone("123456");
		person.setCity("Aurillac");
		person.setZip("15");
		
		//WHEN
		boolean result = personService.updatePerson(getPerson, person);
		
		//THEN
		assertTrue(result);
	}
	
	@Test
	public void updateAPersonErrorTest() {
		
		when(database.getPersonList()).thenReturn(listOfPerson);
		Person getPerson = personService.getPerson("James", "Bond");
		Person person = new Person();
		person.setFirstName("James");
		person.setLastName("Bonisseur de la bath");
		person.setAddress("La grange dans le cantal");
		person.setEmail("JB@MITRetired.uk");
		person.setPhone("123456");
		person.setCity("Aurillac");
		person.setZip("15");
		
		//WHEN
		boolean result = personService.updatePerson(getPerson, person);
		
		//THEN
		assertFalse(result);
	}
}
