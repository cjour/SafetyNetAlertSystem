package com.cjour.SafetyNetAlert.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.*;
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
	public ArrayList<PersonDTOChild> getChild(String address) {
		ArrayList<PersonDTOChild> myListOfChild = new ArrayList<>();
		ArrayList<Person> related = new ArrayList<>();

		for (Person person : persons) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate birthdate = LocalDate.parse(person.getMedicalRecord().getBirthdate(), formatter);
			
			Period period = Period.between(birthdate, LocalDate.now());
			person.setAge(period.getYears());
			
			if(person.getAddress().equals(address) && person.getAge() > 18) {
				related.add(person);
			}
			
			if(person.getAge() <= 18 && (person.getAddress()).equals(address)) {
							
				PersonDTOChild personDTO = new PersonDTOChild(
																person.getFirstName(),
																person.getLastName(),
																person.getAge(),
																related 
															);
				myListOfChild.add(personDTO);
			}
		}
		return myListOfChild;
	}

	@Override
	public ArrayList<PersonDTOEmail> getEmail(String city) {
		ArrayList<PersonDTOEmail> listOfPerson = new ArrayList<PersonDTOEmail>();
		for (Person person : persons) {
			if(person.getCity().equals(city)) {
				PersonDTOEmail personDTO = new PersonDTOEmail(person.getEmail());
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public ArrayList<PersonDTOFireStation> getPersonRelatedToFireStation(int stationNumber) {
		ArrayList<PersonDTOFireStation> listOfPerson = new ArrayList<>();
		for (Person person : persons) {
			if(person.getFireStation().getStation() == stationNumber) {
				PersonDTOFireStation personDTO = new PersonDTOFireStation(
																			person.getFirstName(),
																			person.getLastName(),
																			person.getAddress(),
																			person.getPhone()
																		);
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int stationNumber) {
		ArrayList<PersonDTOPhone> listOfPerson = new ArrayList<>();
		for (Person person : persons) {
			if(person.getFireStation().getStation() == stationNumber) {
				PersonDTOPhone personDTO = new PersonDTOPhone(person.getPhone());
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public ArrayList<PersonDTOAddress> getPersonRelatedToThisAddress(String address) {
		ArrayList<PersonDTOAddress> listOfPerson = new ArrayList<>();
		for (Person person : persons) {
			if(person.getAddress().equals(address)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate birthdate = LocalDate.parse(person.getMedicalRecord().getBirthdate(), formatter);
				
				Period period = Period.between(birthdate, LocalDate.now());
				person.setAge(period.getYears());
				
				PersonDTOAddress personDTO = new PersonDTOAddress(
																	person.getLastName(),
																	person.getAge(),
																	person.getPhone(),
																	person.getMedicalRecord()
																);
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}
}
