package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjour.SafetyNetAlert.model.*;
import com.cjour.SafetyNetAlert.service.*;

@RestController
public class RequestController {
	
	@Autowired
	PersonDAOImpl personDAO;
	@Autowired
	FireStationDAOImpl fireStationDAO;
	@Autowired
	MedicalRecordDAOImpl medicalRecordDAO;
	

	@GetMapping(value="/childAlert")
	public ArrayList<PersonDTOChild> getChild(@RequestParam String address){
		return personDAO.getChild(address);	
	}	
	
	@GetMapping(value="/communityEmail")
	public ArrayList<PersonDTOEmail> getPersonEmailByCity(@RequestParam String city) {
		return personDAO.getEmail(city);
	}
	
	@GetMapping(value="/firestation")
	public ArrayList<PersonDTOFireStation> getPersonRelatedToFirestation(@RequestParam int station_number){
		return personDAO.getPersonRelatedToFireStation(station_number);
	}
	
	@GetMapping(value="/phoneAlert")
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(@RequestParam int firestation){
		return personDAO.getPhoneNumberForSpecificFirestation(firestation);
	}
	
	@GetMapping(value="/fire")
	public ArrayList<PersonDTOAddress> getPersonsByAddress(@RequestParam String address){
		return personDAO.getPersonRelatedToThisAddress(address);
	}
	
//	@GetMapping(value="/flood/stations")
//
//	@GetMapping(value="/personInfo")
	
}
