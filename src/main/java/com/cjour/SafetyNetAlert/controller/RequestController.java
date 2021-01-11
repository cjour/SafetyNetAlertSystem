package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.service.PersonDAOImpl;

@RestController
public class RequestController {
	
	@Autowired
	PersonDAOImpl personDAO;

	@GetMapping(value="/Persons")
	public ArrayList<Person> getPersons () {
		return personDAO.findAll();
	}
	
	@GetMapping(value="/Persons/{lastname}/{firstname}")
	public Person getPerson (@PathVariable String lastname, @PathVariable String firstname) {
		return personDAO.findDistinctByLastnameAndFirstname(lastname, firstname);			
	}
	
	@GetMapping(value="/FireStations")
	public String getFireStations () {
		return "Here is the list of fire stations";
		
	}
	
	@GetMapping(value="/FireStation/{id}")
	public String getFireStation (@PathVariable int id) {
		return "You asked for this fire station : " + id;
		
	}
}
