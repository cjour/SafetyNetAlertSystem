package com.cjour.SafetyNetAlert.controller;

import java.util.ArrayList;
import java.util.HashMap;

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

	// read
	@GetMapping(value = "/childAlert")
	public ArrayList<PersonDTOChild> getChild(@RequestParam String address) {
		return personService.getChild(address);
	}

	@GetMapping(value = "/communityEmail")
	public ArrayList<PersonDTOEmail> getPersonEmailByCity(@RequestParam String city) {
		return personService.getEmail(city);
	}

	@GetMapping(value = "/firestation")
	public ArrayList<Object> getPersonRelatedToFirestation(@RequestParam int station_number) {
		return personService.getPersonRelatedToFireStation(station_number);
	}

	@GetMapping(value = "/phoneAlert")
	public ArrayList<PersonDTOPhone> getPhoneNumberForSpecificFirestation(@RequestParam int firestation) {
		return personService.getPhoneNumberForSpecificFirestation(firestation);
	}

	@GetMapping(value = "/fire")
	public ArrayList<Object> getPersonsByAddress(@RequestParam String address) {
		return personService.getPersonRelatedToThisAddress(address);
	}

	@GetMapping(value = "/flood/stations")
	public HashMap<String, ArrayList<PersonDTOFireStations>> getHomeRelatedToFireStation(
			@RequestParam int[] station_numbers) {
		return personService.getHomeRelatedToFireStation(station_numbers);
	}

	@GetMapping(value = "/personInfo")
	public ArrayList<PersonDTOInfo> getPersonsByFirstAndLastName(@RequestParam String firstName, String lastName) {
		return personService.getPersonByTheirFirstNameAndLastName(lastName, firstName);
	}

	@GetMapping(value = "/firestations")
	public ArrayList<FireStation> getFireStations() {
		return firestationService.findAll();
	}

	@GetMapping(value = "/persons")
	public ArrayList<Person> getPersons() {
		return personService.findAll();
	}

	@GetMapping(value = "/medicalRecords")
	public ArrayList<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.findAll();
	}

	// create
	@PostMapping(value = "/person")
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		if (person.getFirstName() != null && person.getLastName() != null) {
			if (!personService.addAPerson(person)) {
				return new ResponseEntity<String>("Person already exists !", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Person has been created", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("You need at least a firstName and lastName to create a person", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<String> addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null) {
			if (!medicalRecordService.addAMedicalRecord(medicalRecord)) {
				return new ResponseEntity<String>("Medical Record already exists !", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Medical record has been created", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("You need at least a firstName and lastName to create a medical record", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<String> addfireStation(@RequestBody FireStation fireStation) {
		if(fireStation.getAddress() != null && fireStation.getStation() !=0 && fireStation.getStation() > 0) {
			if (!firestationService.addAFireStation(fireStation)) {
				return new ResponseEntity<String>("FireStation already exists !", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Firestation located at " + fireStation.getAddress() + " has been created", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("You need an address and a station number to create a firestation", HttpStatus.BAD_REQUEST);
	}
		

	// delete
	@DeleteMapping(value = "/person")
	public ResponseEntity<String> deleteAPerson(@RequestBody Person person) {
		if(person.getFirstName() != null && person.getLastName() != null) {
			Person persontoDelete = personService.getPerson(person.getFirstName(), person.getLastName());
			if(persontoDelete != null) {
				if(!personService.delete(persontoDelete)) {
					return new ResponseEntity<String>(
							"Cannot delete this person, an error occurred", HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<String>(
						"" + person.getFirstName() + " " + person.getLastName() + " has been deleted", HttpStatus.OK);

			}
			return new ResponseEntity<String>(
					"Cannot delete a non existing person", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(
				"You need to pass the lastName and firstName of the person you want to delete", HttpStatus.BAD_REQUEST);
		
	}

	@DeleteMapping(value = "/medicalRecord")
	public void deleteAMedicalRecord(@RequestParam String firstName, String lastName) {
		MedicalRecord medicalRecord = medicalRecordService.getAMedicalRecord(firstName, lastName);
		medicalRecordService.delete(medicalRecord);
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
					return new ResponseEntity<String>("Cannot update the person, an error occured",
							HttpStatus.CONFLICT);
				}
				return new ResponseEntity<String>(
						"Person " + person.getFirstName() + " " + person.getLastName() + " has been updated",
						HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cannot update a non existing person", HttpStatus.NOT_FOUND);
		}
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
					return new ResponseEntity<String>("Cannot update the medical record, an error occurred",
							HttpStatus.CONFLICT);
				}
				return new ResponseEntity<String>("Medical record of " + medicalRecord.getFirstName() + " "
						+ medicalRecord.getLastName() + " has been updated", HttpStatus.OK);
			}

			return new ResponseEntity<String>("Cannot update a non existing medical record", HttpStatus.NOT_FOUND);

		}
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
						return new ResponseEntity<String>("Cannot update the fire station, the number of station already exists",
								HttpStatus.CONFLICT);

					}
					return new ResponseEntity<String>(
							"Firestation n° " + firestationToUpdate.getAddress() + " has been updated", HttpStatus.OK);
				}
				return new ResponseEntity<String>("station number must be positive", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Cannot update a non existing station", HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<String>("You need to pass a valid address to update a station", HttpStatus.NOT_FOUND);

	}

}
