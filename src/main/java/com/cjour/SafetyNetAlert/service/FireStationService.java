package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.Person;

public interface FireStationService {
	//Create and Update
	public boolean addAFireStation(FireStation fireStation);
	
	//Read
	public ArrayList<FireStation> findAll();
	public FireStation getFireStation(String address);
	
	//Delete
	public void delete(FireStation fireStation);
	
}

