package com.cjour.SafetyNetAlert.service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.repository.Database;

@Repository
public class FireStationDAOImpl implements FireStationDAO {
	
	public static ArrayList<FireStation> fireStations = new Database().fireStationList;

	@Override
	public boolean addAFireStation(FireStation fireStation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<FireStation> findAll() {
		// TODO Auto-generated method stub
		return fireStations;
	}

	@Override
	public boolean delete(FireStation fireStation) {
		// TODO Auto-generated method stub
		return false;
	}

}
