package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public interface MedicalRecordService {
	
		//Create and Update
		public boolean addAMedicalRecord(MedicalRecord medicalRecord);
		
		//Read
		public MedicalRecord getAMedicalRecord(String firstName, String lastName);
		public ArrayList<MedicalRecord> findAll();
		
		//Delete
		public boolean delete(MedicalRecord medicalRecord);

		
	
}
