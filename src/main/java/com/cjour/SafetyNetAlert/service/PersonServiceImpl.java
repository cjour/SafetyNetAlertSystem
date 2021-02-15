package com.cjour.SafetyNetAlert.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.*;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class PersonServiceImpl implements PersonService {

	@Autowired
	private Database database;

	@Override
	public ArrayList<Person> findAll() {
		return database.getPersonList();
	}

	@Override
	public Person getPerson(String firstName, String lastName) {
		Person person = null;

		ArrayList<Person> listOfPerson = database.getPersonList();

		for (Person elem : listOfPerson) {
			if (elem.getFirstName().equals(firstName) && elem.getLastName().equals(lastName)) {
				person = elem;
			}
		}

		return person;
	}

	@Override
	public ArrayList<PersonDTOChild> getChild(String address) {
		ArrayList<PersonDTOChild> myListOfChild = new ArrayList<>();
		ArrayList<Person> related = new ArrayList<>();

		for (Person person : database.getPersonList()) {

			if (person.getAddress().equals(address) && person.getAge() > 18) {
				related.add(person);
			}

			if (person.getAge() > 0 && person.getAge() <= 18 && (person.getAddress()).equals(address)) {

				PersonDTOChild personDTO = new PersonDTOChild(person.getFirstName(), person.getLastName(),
						person.getAge(), related);
				myListOfChild.add(personDTO);
			}
		}
		return myListOfChild;
	}

	@Override
	public ArrayList<PersonDTOEmail> getEmail(String city) {
		ArrayList<PersonDTOEmail> listOfPerson = new ArrayList<PersonDTOEmail>();
		for (Person person : database.getPersonList()) {
			if (person.getCity().equals(city)) {
				PersonDTOEmail personDTO = new PersonDTOEmail(person.getEmail());
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public ArrayList<Object> getPersonRelatedToFireStation(int stationNumber) {
		ArrayList<PersonDTOFireStation> listOfPerson = new ArrayList<>();
		HashMap<String, Integer> listOfMinorAndMajor = new HashMap<>();
		int major = 0;
		int minor = 0;

		for (Person person : database.getPersonList()) {
			if (person.getFireStation().getStation() == stationNumber) {

				PersonDTOFireStation personDTO = new PersonDTOFireStation(person.getFirstName(), person.getLastName(),
						person.getAddress(), person.getPhone());
				listOfPerson.add(personDTO);

				if (person.getAge() > 18) {
					major++;
				} else {
					minor++;
				}
			}
		}

		listOfMinorAndMajor.put("Number of minor", minor);
		listOfMinorAndMajor.put("Number of major", major);

		ArrayList<Object> list = new ArrayList<>();
		list.add(listOfPerson);
		list.add(listOfMinorAndMajor);
		return list;
	}

	@Override
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(int stationNumber) {
		ArrayList<PersonDTOPhone> listOfPerson = new ArrayList<>();
		for (Person person : database.getPersonList()) {
			if (person.getFireStation().getStation() == stationNumber) {
				PersonDTOPhone personDTO = new PersonDTOPhone(person.getPhone());
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public ArrayList<Object> getPersonRelatedToThisAddress(String address) {
		ArrayList<Object> list = new ArrayList<>();
		int fireStationRelated = 0;

		ArrayList<PersonDTOAddress> listOfPerson = new ArrayList<>();
		for (Person person : database.getPersonList()) {
			if (person.getAddress().equals(address)) {
				fireStationRelated = person.getFireStation().getStation();
				PersonDTOAddress personDTO = new PersonDTOAddress(person.getLastName(), person.getAge(),
						person.getPhone(), person.getMedicalRecord());
				listOfPerson.add(personDTO);
			}
		}
		list.add(fireStationRelated);
		list.add(listOfPerson);

		return list;
	}

	@Override
	public ArrayList<PersonDTOInfo> getPersonByTheirFirstNameAndLastName(String lastName, String firstName) {
		ArrayList<PersonDTOInfo> listOfPerson = new ArrayList<>();
		for (Person person : database.getPersonList()) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				PersonDTOInfo personDTO = new PersonDTOInfo(person.getLastName(), person.getAddress(), person.getAge(),
						person.getEmail(), person.getMedicalRecord());
				listOfPerson.add(personDTO);
			}
		}
		return listOfPerson;
	}

	@Override
	public HashMap<String, Object> getHomeRelatedToFireStation(int[] station_numbers) {
		HashMap<String, Object> list = new HashMap<>();

		ArrayList<FireStation> listOfFirestation = new ArrayList<>();
		for (int station_number : station_numbers) {
			for (FireStation fireStation : database.getFireStationList()) {
				if (fireStation.getStation() == station_number) {
					listOfFirestation.add(fireStation);
				}
			}
		}

		for (FireStation fireStation : listOfFirestation) {
			
			ArrayList<PersonDTOFireStations> listOfPerson = new ArrayList<>();

			
			for (Person person : database.getPersonList()) {

				if (person.getFireStation().getAddress() == fireStation.getAddress()) {

					PersonDTOFireStations personDTO = new PersonDTOFireStations(person.getMedicalRecord(),
							person.getLastName(), person.getAddress(), person.getPhone(), person.getAge());
					listOfPerson.add(personDTO);
				}
			}
			
			list.put(fireStation.getAddress(), listOfPerson);
		}
		return list;
	}

	public boolean delete(Person person) {
		return database.getPersonList().remove(person);
	}

	public Boolean addPerson(Person person) {
		if (database.getPersonList().add(person)) {
			return true;
		}
		return false;
	}

	public void updatePerson(Person getPerson, Person person) {
		if (person.getAddress() != null) {
			getPerson.setAddress(person.getAddress());
		}

		if (person.getEmail() != null) {
			getPerson.setEmail(person.getEmail());
		}

		if (person.getPhone() != null) {
			getPerson.setPhone(person.getPhone());
		}

		if (person.getCity() != null) {
			getPerson.setCity(person.getCity());
		}

		if (person.getZip() != null) {
			getPerson.setZip(person.getZip());
		}
	}
}
