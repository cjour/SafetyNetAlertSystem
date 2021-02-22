package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.FireStation;

public interface FireStationService {
	
	//Create 
	public boolean addAFireStation(FireStation fireStation);
	
	//Read
	public ArrayList<FireStation> findAll();
	public FireStation getFireStation(String address);
	public ArrayList<FireStation> getFireStationWithNumber(int number);
	
	//Update
	public boolean updateFireStation(FireStation firestation, int station);
	
	//Delete
	public boolean delete(FireStation fireStation);
	
}

