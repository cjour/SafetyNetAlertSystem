package com.cjour.SafetyNetAlert.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.Person;

public class PersonServiceTest {

	public static final PersonServiceImpl personService = new PersonServiceImpl();
	public static final ArrayList<Person> persons = new ArrayList<>();
	
	@BeforeAll
	public static void setUp(){
		persons.add(new Person("Clement", "Ribas", "41 rue de la Chapelle", "Noyers sur Cher", "41140", "01.02.03.04.05", "jour.clement@gmail.com"));
		persons.add(new Person("Clement", "Jourdain", "41 rue de la Chapelle", "Noyers sur Cher", "41140", "05.06.07.08.09", "jour.clement@gmail.com"));
		persons.add(new Person("Mouchette", "Jourdain-Ribas", "SPA", "Sassay", "41140", "unknown", "mouche@gmail.com"));
		persons.get(0).setFireStation(new FireStation("Chicago Fire, engine company 51", 51));
		persons.get(1).setFireStation(new FireStation("Boston Fire, truck company 81", 81));
		persons.get(2).setFireStation(new FireStation("Washington D.C Fire, ambulance 61", 61));

		PersonServiceImpl.persons = persons;
	}
	
	@Test
	public void getChildTest() {
		
		//GIVEN		
		String address = "41 rue de la Chapelle";
		for (Person person : persons) {
			if(person.getAddress().equals(address)) {
				person.setAge(10);
			}
		}
		
		//WHEN
		ArrayList<PersonDTOChild> childs = personService.getChild(address);
		
		//THEN
		for (PersonDTOChild child : childs) {
			assertEquals("Clement", child.getFirstName());
		}
	}
	
	@Test
	public void getEmailTest() {
		//GIVEN
		String city = "Noyers sur Cher";
		
		//WHEN
		ArrayList<PersonDTOEmail> emails = personService.getEmail(city);
		
		//THEN
		for (PersonDTOEmail email : emails) {
				assertEquals("jour.clement@gmail.com", email.getEmail());
		}
	}
	
	@Test
	public void getPersonRelatedToFireStationTest() {
		//GIVEN
		int station = persons.get(0).getFireStation().getStation();
		
		//WHEN
		ArrayList<Object> list = personService.getPersonRelatedToFireStation(station);
		
		//THEN
		ArrayList<PersonDTOFireStation> listOfPerson = (ArrayList<PersonDTOFireStation>) list.get(0);
		
		for (PersonDTOFireStation person : listOfPerson) {
			assertEquals("Clement", person.getFirstName());
			assertEquals("Ribas", person.getLastName());
			assertEquals("41 rue de la Chapelle", person.getAddress());
			assertEquals("01.02.03.04.05", person.getPhone());
		}
	}
	
	@Test public void getPhoneNumberForSpecificFirestationTest() {
		
		//GIVEN
		int station = persons.get(0).getFireStation().getStation();
		
		//WHEN
		ArrayList<PersonDTOPhone> listOfPerson = personService.getPhoneNumberForSpecificFirestation(station);
		
		//THEN
		for (PersonDTOPhone personDTOPhone : listOfPerson) {
			assertEquals("01.02.03.04.05", personDTOPhone.getPhone());
		}
	}
	
	@Test public void getPersonByTheirFirstNameAndLastNameTest() {
		//GIVEN
		String firstName = "Mouchette";
		String lastName = "Jourdain-Ribas";
		
		//WHEN
		ArrayList<PersonDTOInfo> listOfPerson = personService.getPersonByTheirFirstNameAndLastName(lastName, firstName);
		
		//THEN
		for (PersonDTOInfo personDTOInfo : listOfPerson) {
			assertEquals(lastName, personDTOInfo.getLastName());
		}
	}	
}
