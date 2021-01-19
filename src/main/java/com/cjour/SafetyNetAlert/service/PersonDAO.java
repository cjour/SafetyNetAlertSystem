package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.model.PersonDTOEmail;
import com.cjour.SafetyNetAlert.model.PersonDTOFireStation;

public interface PersonDAO {
	//Create and Update
	public boolean addAPerson(Person person);
	
	//Read
	public ArrayList<Person> findAll();
	public Person findDistinctByLastnameAndFirstname(String lastname);
	public ArrayList<Person> getChild(String address);
	public ArrayList<PersonDTOEmail> getEmail(String city);
	public ArrayList<PersonDTOFireStation> getPersonRelatedToFireStation(int stationNumber);
	
	//Delete
	public boolean delete(Person person);
	
}
