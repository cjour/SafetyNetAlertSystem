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
	

	@GetMapping(value="/person")
	public ArrayList<Person> getPersons () {
		return personDAO.findAll();
	}
	
	@GetMapping(value="/childAlert")
	public ArrayList<Person> getChild(@RequestParam String address){
		return personDAO.getChild(address);	
	}	
	
	@GetMapping(value="/firestation")
	public ArrayList<FireStation> getFireStations () {
		return fireStationDAO.findAll();
	}
	
	@GetMapping(value="/medicalRecord")
	public ArrayList<MedicalRecord> getMedicalRecords () {
		return medicalRecordDAO.findAll();
	}
	
	@GetMapping(value="/communityEmail")
	public ArrayList<Person> getPersonEmailByCity(@RequestParam String city) {
		return personDAO.getPersons(city);
	}
}
