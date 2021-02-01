package com.cjour.SafetyNetAlert.repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.service.JSONDataHandler;

@Component("db")
public class Database {
	
	private final static JSONDataHandler jsonDataHandler = new JSONDataHandler();

	public ArrayList<Person> personList;
	public ArrayList<FireStation> fireStationList;
	public ArrayList<MedicalRecord> medicalRecordList;


	public Database() {
		this.personList = jsonDataHandler.fetchingDataFromJSONForPersons();
		this.fireStationList = jsonDataHandler.fetchingDataFromJSONForFireStation();
		this.medicalRecordList = jsonDataHandler.fetchingDataFromJSONForMedicalRecords();
		this.linkMedicalRecordsToPerson();
//		this.linkPersonToFireStation();
		this.linkFireStationToPerson();
		this.setAge();

	}

	public void linkMedicalRecordsToPerson() {
		for (Person person : personList) {
			for (MedicalRecord medicalrecord : medicalRecordList) {
 				if ((medicalrecord.getFirstName()).equals(person.getFirstName()) && (medicalrecord.getLastName().equals(medicalrecord.getLastName()))) {
					person.setMedicalRecord(medicalrecord);
				}
			}
		}
	}
	
	public void linkFireStationToPerson(){
		for (Person person : personList) {
			for (FireStation fireStation : fireStationList) {
				if(person.getAddress().equals(fireStation.getAddress())){
					person.setFireStation(fireStation);
				}
			}
		}
	}
	
	public void setAge() {
		for (Person person : personList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate birthdate = LocalDate.parse(person.getMedicalRecord().getBirthdate(), formatter);
			
			Period period = Period.between(birthdate, LocalDate.now());
			person.setAge(period.getYears());
		}
	}
	
//	public void linkPersonToFireStation() {
//		for (FireStation fireStation : fireStationList) {
//			for (Person person : personList) {
//				if(person.getAddress().equals(fireStation.getAddress())){
//					fireStation.getListOfPerson().add(person);
//					person.setFireStation(fireStation);
//				}
//			}
//		}
//	}
}
