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
	public boolean delete(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void addAMedicalRecord(MedicalRecord medicalRecord) {
		database.getMedicalRecordList().add(medicalRecord);
	}

}
