package com.cjour.SafetyNetAlert.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
	
	@Override
	public ArrayList<Person> getChild(String address) {
		ArrayList<Person> myListOfChild = new ArrayList<Person>();
		for (Person person : persons) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate birthdate = LocalDate.parse(person.getMedicalRecord().getBirthdate(), formatter);
			
			LocalDate now = LocalDate.now();
			
			Period period = Period.between(birthdate, now);
			
			if(period.getYears() <= 18 && (person.getAddress()).equals(address)) {
				myListOfChild.add(person);
			}
		}
		return myListOfChild;
	}

	@Override
	public ArrayList<Person> getPersons(String city) {
		ArrayList<Person> listOfPerson = new ArrayList<Person>();
		for (Person person : persons) {
			if(person.getCity().equals(city)) {
				listOfPerson.add(person);
			}
		}
		return listOfPerson;
	}
}
