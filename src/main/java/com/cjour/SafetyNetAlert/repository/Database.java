package com.cjour.SafetyNetAlert.repository;

import java.util.ArrayList;
import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.service.JSONDataHandler;


public class Database {
	
	private ArrayList<Person> personList;
	private ArrayList<FireStation> fireStationList;
	private ArrayList<MedicalRecord> medicalRecordList;
	
	private final static JSONDataHandler jsonDataHandler = new JSONDataHandler();
	
	public Database() {
		this.personList = jsonDataHandler.fetchingDataFromJSONForPersons();
		this.fireStationList = jsonDataHandler.fetchingDataFromJSONForFireStation();
		this.medicalRecordList = jsonDataHandler.fetchingDataFromJSONForMedicalRecords();
	}

	public void showDatabaseContent() {
		for (Person person : personList) {
			System.out.println(person.getFirstName() + " " 
							 + person.getLastName() + " "
							 + person.getAddress() + " "
							 + person.getCity() + " "
							 + person.getZip() + " "
							 + person.getPhone() + " "
							 + person.getEmail()
							 );
		}
		
		for (FireStation fireStation : fireStationList) {
			System.out.println(fireStation.getStation());
		}
		
		for (MedicalRecord medicalRecord : medicalRecordList) {
			System.out.println(medicalRecord.getMedications());
		}	
	}
	
	
}
