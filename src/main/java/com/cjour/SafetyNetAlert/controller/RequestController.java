package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjour.SafetyNetAlert.DTO.PersonDTOChild;
import com.cjour.SafetyNetAlert.DTO.PersonDTOEmail;
import com.cjour.SafetyNetAlert.DTO.PersonDTOInfo;
import com.cjour.SafetyNetAlert.DTO.PersonDTOPhone;
import com.cjour.SafetyNetAlert.model.*;
import com.cjour.SafetyNetAlert.service.*;

@RestController
public class RequestController {
	
	@Autowired
	PersonServiceImpl personDAO;
	@Autowired
	FireStationServiceImpl fireStationDAO;
	@Autowired
	MedicalRecordServiceImpl medicalRecordDAO;
	

	@GetMapping(value="/childAlert")
	public ArrayList<PersonDTOChild> getChild(@RequestParam String address){
		return personDAO.getChild(address);
	}	
	
	@GetMapping(value="/communityEmail")
	public ArrayList<PersonDTOEmail> getPersonEmailByCity(@RequestParam String city) {
		return personDAO.getEmail(city);
	}
	
	@GetMapping(value="/firestation")
	public ArrayList<Object> getPersonRelatedToFirestation(@RequestParam int station_number){
		return personDAO.getPersonRelatedToFireStation(station_number);
	}
	
	@GetMapping(value="/phoneAlert")
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(@RequestParam int firestation){
		return personDAO.getPhoneNumberForSpecificFirestation(firestation);
	}
	
	@GetMapping(value="/fire")
	public HashMap<String, Object> getPersonsByAddress(@RequestParam String address){
		return personDAO.getPersonRelatedToThisAddress(address);
	}
	
	@GetMapping(value="/flood/stations")
	public HashMap<String, Object> getHomeRelatedToFireStation (@RequestParam int[] station_numbers) {
		return personDAO.getHomeRelatedToFireStation(station_numbers);
	}
	
	@GetMapping(value="/personInfo")
	public ArrayList<PersonDTOInfo> getPersonsByFirstAndLastName (@RequestParam String firstName, String lastName){
		return personDAO.getPersonByTheirFirstNameAndLastName(lastName, firstName);
	}
	
	@PutMapping(value="/person")
	public ArrayList<Person> deletePerson(@RequestParam String firstName, String lastName, @RequestBody String Email) {
		return personDAO.updatePerson(firstName, lastName, Email);
	}

}
