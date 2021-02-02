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
	
	@Override
	public ArrayList<MedicalRecord> findAll() {
		return database.getMedicalRecordList();
	}

	@Override
	public MedicalRecord findDistinctByLastnameAndFirstname(String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MedicalRecord medicalRecord) {
		database.getMedicalRecordList().remove(medicalRecord);
	}
	
	@Override
	public Boolean addAMedicalRecord(MedicalRecord medicalRecord) {
		return database.getMedicalRecordList().add(medicalRecord);
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

}
