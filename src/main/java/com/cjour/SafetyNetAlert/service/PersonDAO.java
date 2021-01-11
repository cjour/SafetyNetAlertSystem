package com.cjour.SafetyNetAlert.service;

import java.util.List;

import com.cjour.SafetyNetAlert.model.Person;

public interface PersonDAO {
	//Create and Update
	public boolean addAPerson(Person person);
	
	//Read
	public List<Person> findAll();
	public Person findDistinctByLastnameAndFirstname(String lastname, String firstname);
	
	//Delete
	public boolean delete(Person person);

	
}
