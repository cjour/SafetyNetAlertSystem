package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.model.Person;
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
	
	@GetMapping(value="/firestations")
	public ArrayList<FireStation> getFireStations(){
		return fireStationDAO.findAll();
	}
	
	@GetMapping(value="/persons")
	public ArrayList<Person> getPersons(){
		return personDAO.findAll();
	}
	
	@GetMapping(value="/medicalRecords")
	public ArrayList<MedicalRecord> getMedicalRecords(){
		return medicalRecordDAO.findAll();
	}
	
	@PostMapping(value="/person")
	public void addPerson(@RequestBody Person person) {
		personDAO.addPerson(person);
	}
	
	@PostMapping(value="/medicalRecord")
	public void addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		medicalRecordDAO.addAMedicalRecord(medicalRecord);
	}
	
	@PostMapping(value="/firestation")
	public void addfireStation(@RequestBody FireStation fireStation) {
		fireStationDAO.addAFireStation(fireStation);
	}
	
	@DeleteMapping(value="/person")
	public void deleteAPerson(@RequestParam String firstName, String lastName){
		Person person = personDAO.getPerson(firstName, lastName);
		personDAO.delete(person);
	}
	
	@DeleteMapping(value="/medicalRecord")
	public void deleteAMedicalRecord(@RequestParam String firstName, String lastName) {
		MedicalRecord medicalRecord = medicalRecordDAO.getAMedicalRecord(firstName, lastName);
		medicalRecordDAO.delete(medicalRecord);
	}
	
	@DeleteMapping(value="/firestation")
	public void deleteAFireStation(@RequestParam String address) {
		FireStation firestation = fireStationDAO.getFireStation(address);
		fireStationDAO.delete(firestation);
	}
}
