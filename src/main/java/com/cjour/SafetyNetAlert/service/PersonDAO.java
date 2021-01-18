package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.Person;

public interface PersonDAO {
	//Create and Update
	public boolean addAPerson(Person person);
	
	//Read
	public ArrayList<Person> findAll();
	public Person findDistinctByLastnameAndFirstname(String lastname);
	public ArrayList<Person> getChild(String address);
	public ArrayList<Person> getPersons(String email);
	
	//Delete
	public boolean delete(Person person);
	
}
