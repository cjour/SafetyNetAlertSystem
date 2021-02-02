package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public interface MedicalRecordService {
	
		//Create and Update
		public Boolean addAMedicalRecord(MedicalRecord medicalRecord);
		
		//Read
		public MedicalRecord getAMedicalRecord(String firstName, String lastName);
		public ArrayList<MedicalRecord> findAll();
		public MedicalRecord findDistinctByLastnameAndFirstname(String lastname);
		
		//Delete
		public void delete(MedicalRecord medicalRecord);

		
	
}
