package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.cjour.SafetyNetAlert.DTO.*;

public interface PersonService {
	//Create
	
	//Update	
	
	//Read
	public ArrayList<PersonDTOChild> getChild(String address);
	public ArrayList<PersonDTOEmail> getEmail(String city);
	public ArrayList<Object> getPersonRelatedToFireStation(int stationNumber);
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int station_number);
	public HashMap<String, Object> getPersonRelatedToThisAddress(String address);
	public ArrayList<PersonDTOInfo> getPersonByTheirFirstNameAndLastName(String lastName, String firstName);
	public HashMap<String, Object> getHomeRelatedToFireStation(int[] station_numbers);

	
	//Delete
	
}
