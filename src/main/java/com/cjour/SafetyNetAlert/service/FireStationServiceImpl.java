package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class FireStationServiceImpl implements FireStationService {
	
	@Autowired
	private Database database;

	
	@Override
	public void addAFireStation(FireStation fireStation) {
		database.getFireStationList().add(fireStation);
	}

	@Override
	public ArrayList<FireStation> findAll() {
		return database.getFireStationList();
	}
	
	@Override
	public ArrayList<Person> findPersonRelatedByFireStation(int fireStationNumber) {
		
		return null;
	}

	@Override
	public void delete(FireStation fireStation) {
		database.getFireStationList().remove(fireStation);
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

	

}
