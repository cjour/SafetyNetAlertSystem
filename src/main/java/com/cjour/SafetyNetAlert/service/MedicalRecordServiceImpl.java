package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private Database database;
	
	//read
	@Override
	public ArrayList<MedicalRecord> findAll() {
		return database.getMedicalRecordList();
	}
	
	@Override
	public MedicalRecord getAMedicalRecord(String firstName, String lastName) {
		MedicalRecord medicalRecord = null;
		
		for (MedicalRecord elem : database.getMedicalRecordList()) {
			if(elem.getFirstName().equals(firstName) && elem.getLastName().equals(lastName)) {
				medicalRecord = elem;
			}
		}
		return medicalRecord;
	}
	
	//delete
	@Override
	public void delete(MedicalRecord medicalRecord) {
		database.getMedicalRecordList().remove(medicalRecord);
	}
	
	//create
	@Override
	public Boolean addAMedicalRecord(MedicalRecord medicalRecord) {
		if (getAMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) == null) {
			database.getMedicalRecordList().add(medicalRecord);
		}
		return false;
	}

	//update
	public void updateMedicalRecord(MedicalRecord getMedicalRecord, MedicalRecord medicalRecord) {
		if(this.getAMedicalRecord(getMedicalRecord.getFirstName(), getMedicalRecord.getLastName()) != null) {
			if(medicalRecord.getBirthdate() != null) {
				getMedicalRecord.setBirthdate(medicalRecord.getBirthdate());			
			}
			
			if(medicalRecord.getAllergies() != null) {
				getMedicalRecord.setAllergies(medicalRecord.getAllergies());			
			}
			
			if(medicalRecord.getMedications() != null) {
				getMedicalRecord.setMedications(medicalRecord.getMedications());
			}
		}
	}

}
