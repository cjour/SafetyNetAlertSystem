package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.repository.Database;


@Repository
public class PersonDAOImpl implements PersonDAO {
	
	public static ArrayList<Person> persons = new Database().personList;
	

	
	@Override
	public boolean addAPerson(Person person) {
		persons.add(person);
		return true;
	}

	@Override
	public ArrayList<Person> findAll() {
		return persons;
	}

	@Override
	public Person findDistinctByLastnameAndFirstname(String lastname) {
		for (Person person : persons) {
			if(person.getLastName() == lastname) {
				return person;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Person person) {
		persons.remove(person);
		return true;
	}

}
