package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public interface MedicalRecordService {
	
		//Create
		public boolean addAMedicalRecord(MedicalRecord medicalRecord);
		
		//Read
		public MedicalRecord getAMedicalRecord(String firstName, String lastName);
		public ArrayList<MedicalRecord> findAll();
		
		//Update
		public boolean updateMedicalRecord(MedicalRecord getMedicalRecord, MedicalRecord medicalRecord);
		
		//Delete
		public boolean delete(MedicalRecord medicalRecord);

		
	
}
