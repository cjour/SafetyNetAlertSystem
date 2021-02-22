package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.Person;

public interface PersonService {
	//Create
	public boolean addAPerson(Person person);
	
	//Read
	public ArrayList<Person> findAll();
	public Person getPerson(String firstName, String lastName);
	public ArrayList<PersonDTOChild> getChild(String address);
	public ArrayList<PersonDTOEmail> getEmail(String city);
	public ArrayList<Object> getPersonRelatedToFireStation(int stationNumber);
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int station_number);
	public ArrayList<Object> getPersonRelatedToThisAddress(String address);
	public ArrayList<PersonDTOInfo> getPersonByTheirFirstNameAndLastName(String lastName, String firstName);
	public HashMap<String, ArrayList<PersonDTOFireStations>> getHomeRelatedToFireStation(int[] station_numbers);
	
	//Update
	public boolean updatePerson(Person getPerson, Person person);
	
	//Delete
	public boolean delete(Person person);
}
