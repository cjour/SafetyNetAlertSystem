package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.*;

public interface PersonDAO {
	//Create and Update
	public boolean addAPerson(Person person);
	
	//Read
	public ArrayList<Person> findAll();
	public Person findDistinctByLastnameAndFirstname(String lastname);
	public ArrayList<PersonDTOChild> getChild(String address);
	public ArrayList<PersonDTOEmail> getEmail(String city);
	public ArrayList<PersonDTOFireStation> getPersonRelatedToFireStation(int stationNumber);
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int station_number);
	public ArrayList<PersonDTOAddress> getPersonRelatedToThisAddress(String address);
	
	//Delete
	public boolean delete(Person person);
	
}
