package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.cjour.SafetyNetAlert.DTO.PersonDTOChild;
import com.cjour.SafetyNetAlert.DTO.PersonDTOEmail;
import com.cjour.SafetyNetAlert.DTO.PersonDTOInfo;
import com.cjour.SafetyNetAlert.DTO.PersonDTOPhone;
import com.cjour.SafetyNetAlert.model.*;

public interface PersonDAO {
	//Create
	public boolean addAPerson(Person person);
	
	//Update	
	public ArrayList<Person> updatePerson(String firstName, String lastName, String email);
	
	//Read
	public ArrayList<PersonDTOChild> getChild(String address);
	public ArrayList<PersonDTOEmail> getEmail(String city);
	public ArrayList<Object> getPersonRelatedToFireStation(int stationNumber);
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int station_number);
	public HashMap<String, Object> getPersonRelatedToThisAddress(String address);
	public ArrayList<PersonDTOInfo> getPersonByTheirFirstNameAndLastName(String lastName, String firstName);
	public HashMap<String, Object> getHomeRelatedToFireStation(int[] station_numbers);

	
	//Delete
	public void deletePerson(String firstName, String lastName);
	
}
