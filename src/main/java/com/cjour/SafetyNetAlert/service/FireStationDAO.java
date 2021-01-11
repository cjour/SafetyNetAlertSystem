package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.FireStation;

public interface FireStationDAO {
	//Create and Update
	public boolean addAFireStation(FireStation fireStation);
	
	//Read
	public ArrayList<FireStation> findAll();
	
	//Delete
	public boolean delete(FireStation fireStation);

	
}

