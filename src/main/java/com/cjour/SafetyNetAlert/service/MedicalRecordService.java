package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class MedicalRecordService implements MedicalRecordDAO {

	public static ArrayList<MedicalRecord> medicalRecords = new Database().medicalRecordList;

	
	@Override
	public boolean addAMedicalRecord(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MedicalRecord> findAll() {
		// TODO Auto-generated method stub
		return medicalRecords;
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

}
