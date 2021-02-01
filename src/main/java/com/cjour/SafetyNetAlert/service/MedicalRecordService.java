package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public interface MedicalRecordService {
	
		//Create and Update
		public void addAMedicalRecord(MedicalRecord medicalRecord);
		
		//Read
		public ArrayList<MedicalRecord> findAll();
		public MedicalRecord findDistinctByLastnameAndFirstname(String lastname);
		
		//Delete
		public boolean delete(MedicalRecord medicalRecord);

		
	
}
