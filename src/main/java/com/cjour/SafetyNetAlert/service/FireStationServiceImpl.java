package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class FireStationServiceImpl implements FireStationService {
	
	@Autowired
	private Database database;

	
	//read
	@Override
	public ArrayList<FireStation> findAll() {
		return database.getFireStationList();
	}
	
	@Override
	public FireStation getFireStation(String address) {
		FireStation firestation = null;
		for (FireStation elem : database.getFireStationList()) {
			if(elem.getAddress().equals(address)) {
				firestation = elem;
			}
		}
		return firestation;
	}
	
	public ArrayList<FireStation> getFireStationWithNumber(int number) {
		ArrayList<FireStation> fireStationList = new ArrayList<>();
		for (FireStation firestation : database.getFireStationList()) {
			if (firestation.getStation() == number) {
				fireStationList.add(firestation);
			}
		}
		
		return fireStationList ;	
	}
	
	//create
	@Override
	public boolean addAFireStation(FireStation fireStation) {
		if(getFireStationWithNumber(fireStation.getStation()).isEmpty()){
			if(fireStation.getStation() != 0 && fireStation.getAddress() != null) {
				return database.getFireStationList().add(fireStation);
			}
		}
		return false;
	}

	
	//delete
	@Override
	public boolean delete(FireStation fireStation) {
		return database.getFireStationList().remove(fireStation);
	}

	
	//update
	public boolean updateFireStation(FireStation firestation, int station) {
		for (FireStation elem : database.getFireStationList()) {
			if(elem.getStation() == station) {
				return false;
			}
		}	
		firestation.setStation(station);			
		return true;
	}

	

}
