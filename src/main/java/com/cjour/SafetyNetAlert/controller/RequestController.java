package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;
import java.util.HashMap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjour.SafetyNetAlert.DTO.*;
import com.cjour.SafetyNetAlert.model.*;
import com.cjour.SafetyNetAlert.service.*;

@RestController
public class RequestController {

	@Autowired
	PersonServiceImpl personService;
	@Autowired
	FireStationServiceImpl firestationService;
	@Autowired
	MedicalRecordServiceImpl medicalRecordService;
	
	private static final Logger LOGGER = LogManager.getLogger("APPLog");

	// read
	@GetMapping(value = "/childAlert")
	public ArrayList<PersonDTOChild> getChild(@RequestParam String address) {
		LOGGER.info("request childAlert has been send for address : " + address);
		ArrayList<PersonDTOChild> result = personService.getChild(address);
		LOGGER.info("result for childAlert request is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/communityEmail")
	public ArrayList<PersonDTOEmail> getPersonEmailByCity(@RequestParam String city) {
		LOGGER.info("request for endpoint communityEmail has been send for city : " + city);
		ArrayList<PersonDTOEmail> result = personService.getEmail(city);
		LOGGER.info("result for communityEmail is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/firestation")
	public ArrayList<Object> getPersonRelatedToFirestation(@RequestParam int stationNumber) {
		LOGGER.info("request firestation has been send for firestation : " + stationNumber);
		ArrayList<Object> result = personService.getPersonRelatedToFireStation(stationNumber);
		LOGGER.info("result for firestation is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/phoneAlert")
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(@RequestParam int firestation) {
		LOGGER.info("request phoneAlert has been send for firestation : " + firestation);
		ArrayList<PersonDTOPhone> result = personService.getPhoneNumberForSpecificFirestation(firestation);
		LOGGER.info("result for phoneAlert is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/fire")
	public ArrayList<Object> getPersonsByAddress(@RequestParam String address) {
		LOGGER.info("request fire has been send for address : " + address);
		ArrayList<Object> result = personService.getPersonRelatedToThisAddress(address);
		LOGGER.info("result for fire is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/flood/stations")
	public HashMap<String, ArrayList<PersonDTOFireStations>> getHomeRelatedToFireStation(@RequestParam int[] stations) {
		LOGGER.info("request flood/stations has been send for stations : " + stations);
		HashMap<String, ArrayList<PersonDTOFireStations>> result = personService.getHomeRelatedToFireStation(stations);
		LOGGER.info("result for flood/stations is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/personInfo")
	public ArrayList<PersonDTOInfo> getPersonsByFirstAndLastName(@RequestParam String firstName, String lastName) {
		LOGGER.info("request personInfo has been send for person : " + firstName + " " + lastName);
		ArrayList<PersonDTOInfo> result = personService.getPersonByTheirFirstNameAndLastName(lastName, firstName);
		LOGGER.info("result for personInfo is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/firestations")
	public ArrayList<FireStation> getFireStations() {
		LOGGER.info("request firestations has been send");
		ArrayList<FireStation> result = firestationService.findAll();
		LOGGER.info("result for firestations is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/persons")
	public ArrayList<Person> getPersons() {
		LOGGER.info("request persons has been send");
		ArrayList<Person> result = personService.findAll();
		LOGGER.info("result for persons is : " + result.toString());
		return result;
	}

	@GetMapping(value = "/medicalRecords")
	public ArrayList<MedicalRecord> getMedicalRecords() {
		LOGGER.info("request medicalRecords has been send");
		ArrayList<MedicalRecord> result = medicalRecordService.findAll();
		LOGGER.info("result for medicalRecords is : " + result.toString());
		return medicalRecordService.findAll();
	}

	// create
	@PostMapping(value = "/person")
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		LOGGER.info("request person has been send");
		if (person.getFirstName() != null && person.getLastName() != null) {
			if (!personService.addAPerson(person)) {
				LOGGER.error("Person already exists !");
				return new ResponseEntity<String>("Person already exists !", HttpStatus.CONFLICT);
			}
			LOGGER.info("Person has been created");
			return new ResponseEntity<String>("Person has been created", HttpStatus.CREATED);
		}
		LOGGER.error("You need at least a firstName and lastName to create a person");
		return new ResponseEntity<String>("You need at least a firstName and lastName to create a person", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<String> addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null) {
			if (!medicalRecordService.addAMedicalRecord(medicalRecord)) {
				LOGGER.error("Medical Record already exists !");
				return new ResponseEntity<String>("Medical Record already exists !", HttpStatus.CONFLICT);
			}
			LOGGER.info("Medical record has been created");
			return new ResponseEntity<String>("Medical record has been created", HttpStatus.CREATED);
		}
		LOGGER.error("You need at least a firstName and lastName to create a medical record");
		return new ResponseEntity<String>("You need at least a firstName and lastName to create a medical record", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<String> addfireStation(@RequestBody FireStation fireStation) {
		if(fireStation.getAddress() != null && fireStation.getStation() !=0 && fireStation.getStation() > 0) {
			if (!firestationService.addAFireStation(fireStation)) {
				LOGGER.error("FireStation already exists !");
				return new ResponseEntity<String>("FireStation already exists !", HttpStatus.CONFLICT);
			}
			LOGGER.info("FireStation has been created");
			return new ResponseEntity<String>("Firestation located at " + fireStation.getAddress() + " has been created", HttpStatus.CREATED);
		}
		LOGGER.error("You need an address and a station number to create a firestation");
		return new ResponseEntity<String>("You need an address and a station number to create a firestation", HttpStatus.BAD_REQUEST);
	}
		

	// delete
	@DeleteMapping(value = "/person")
	public ResponseEntity<String> deleteAPerson(@RequestBody Person person) {
		if(person.getFirstName() != null && person.getLastName() != null) {
			Person persontoDelete = personService.getPerson(person.getFirstName(), person.getLastName());
			if(persontoDelete != null) {
				if(!personService.delete(persontoDelete)) {
					LOGGER.error("Cannot delete this person, an error occurred");

					return new ResponseEntity<String>(
							"Cannot delete this person, an error occurred", HttpStatus.CONFLICT);
				}
				LOGGER.info("Cannot delete this person, an error occurred");
				return new ResponseEntity<String>(
						"" + person.getFirstName() + " " + person.getLastName() + " has been deleted", HttpStatus.OK);

			}
			LOGGER.error("Cannot delete a non existing person");
			return new ResponseEntity<String>(
					"Cannot delete a non existing person", HttpStatus.NOT_FOUND);
		}
		LOGGER.error("You need to pass the lastName and firstName of the person you want to delete");
		return new ResponseEntity<String>(
				"You need to pass the lastName and firstName of the person you want to delete", HttpStatus.BAD_REQUEST);
		
	}

	@DeleteMapping(value = "/medicalRecord")
	public ResponseEntity<String> deleteAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if(medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null) {
			MedicalRecord medicalRecordToDelete = medicalRecordService.getAMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
			if (medicalRecordToDelete != null) {
				if(!medicalRecordService.delete(medicalRecordToDelete)) {
					LOGGER.error("Cannot delete this medical record, an error occurred");

					return new ResponseEntity<String>(
							"Cannot delete this medical record, an error occurred", HttpStatus.CONFLICT);
				}
				LOGGER.info("Cannot delete this person, an error occurred");
				return new ResponseEntity<String>(
						"" + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " has been deleted", HttpStatus.OK);

			}
			LOGGER.error("Cannot delete a non existing medical record");
			return new ResponseEntity<String>(
					"Cannot delete a non existing medical record", HttpStatus.NOT_FOUND);
		}
		LOGGER.error("You need to pass the lastName and firstName of the person you want to delete");
		return new ResponseEntity<String>(
				"You need to pass the lastName and firstName of the medicalRecord you want to delete", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/firestation")
	public void deleteAFireStation(@RequestParam String address) {
		FireStation firestation = firestationService.getFireStation(address);
		firestationService.delete(firestation);
	}

	// update
	@PutMapping(value = "/person")
	public ResponseEntity<String> updateAPerson(@RequestBody Person person) {
		if (person.getFirstName() != null && person.getLastName() != null) {
			Person getPerson = personService.getPerson(person.getFirstName(), person.getLastName());
			if (getPerson != null) {
				if (!personService.updatePerson(getPerson, person)) {
					LOGGER.error("Cannot update the person, an error occured");
					return new ResponseEntity<String>("Cannot update the person, an error occured",
							HttpStatus.CONFLICT);
				}
				LOGGER.info("Person " + person.getFirstName() + " " + person.getLastName() + " has been updated");
				return new ResponseEntity<String>(
						"Person " + person.getFirstName() + " " + person.getLastName() + " has been updated",
						HttpStatus.OK);
			}
			LOGGER.error("Cannot update a non existing person");
			return new ResponseEntity<String>("Cannot update a non existing person", HttpStatus.NOT_FOUND);
		}
		LOGGER.error("You need to pass the lastName and firstName of the person you want to update");
		return new ResponseEntity<String>(
				"You need to pass the lastName and firstName of the person you want to update", HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/medicalRecord")
	public ResponseEntity<String> updateAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null) {
			MedicalRecord getMedicalRecord = medicalRecordService.getAMedicalRecord(medicalRecord.getFirstName(),
					medicalRecord.getLastName());
			if (getMedicalRecord != null) {
				if (!medicalRecordService.updateMedicalRecord(getMedicalRecord, medicalRecord)) {
					LOGGER.error("Cannot update the medical record, an error occurred");
					return new ResponseEntity<String>("Cannot update the medical record, an error occurred",
							HttpStatus.CONFLICT);
				}
				LOGGER.info("Medical record of " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " has been updated");
				return new ResponseEntity<String>("Medical record of " + medicalRecord.getFirstName() + " "	+ medicalRecord.getLastName() + " has been updated", HttpStatus.OK);
			}
			LOGGER.error("Cannot update a non existing medical record");
			return new ResponseEntity<String>("Cannot update a non existing medical record", HttpStatus.NOT_FOUND);

		}
		LOGGER.error("You need to pass the lastName and firstName of the person related to the medical record you want to update");
		return new ResponseEntity<String>(
				"You need to pass the lastName and firstName of the person related to the medical record you want to update",
				HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/firestation")
	public ResponseEntity<String> updateAFireStation(@RequestBody FireStation firestation) {
		if (firestation.getAddress() != null) {
			FireStation firestationToUpdate = firestationService.getFireStation(firestation.getAddress());
			if (firestationToUpdate != null) {
				if (firestation.getStation() != 0 && firestation.getStation() > 0) {
					if (!firestationService.updateFireStation(firestationToUpdate, firestation.getStation())) {
						LOGGER.error("Cannot update the fire station, the number of station already exists");
						return new ResponseEntity<String>("Cannot update the fire station, the number of station already exists",
								HttpStatus.CONFLICT);

					}
					LOGGER.info("Firestation n° " + firestationToUpdate.getAddress() + " has been updated");
					return new ResponseEntity<String>(
							"Firestation n° " + firestationToUpdate.getAddress() + " has been updated", HttpStatus.OK);
				}
				LOGGER.error("station number must be positive");
				return new ResponseEntity<String>("station number must be positive", HttpStatus.CONFLICT);
			}
			LOGGER.error("Cannot update a non existing station");
			return new ResponseEntity<String>("Cannot update a non existing station", HttpStatus.NOT_FOUND);

		}
		LOGGER.error("You need to pass a valid address to update a station");
		return new ResponseEntity<String>("You need to pass a valid address to update a station", HttpStatus.NOT_FOUND);

	}

}
