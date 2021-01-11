package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public interface MedicalRecordDAO {
	
		//Create and Update
		public boolean addAMedicalRecord(MedicalRecord medicalRecord);
		
		//Read
		public ArrayList<MedicalRecord> findAll();
		public MedicalRecord findDistinctByLastnameAndFirstname(String lastname);
		
		//Delete
		public boolean delete(MedicalRecord medicalRecord);

		
	
}
