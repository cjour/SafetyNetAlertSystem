package com.cjour.SafetyNetAlert.model;

import java.util.ArrayList;

public class FireStation {

	private ArrayList<Person> listOfPersonAffiliatedToThisStation;
	private String address;
	private int stationNumber;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	public ArrayList<Person> getListOfPersonAffiliatedToThisStation() {
		return listOfPersonAffiliatedToThisStation;
	}
	public void setListOfPersonAffiliatedToThisStation(ArrayList<Person> listOfPersonAffiliatedToThisStation) {
		this.listOfPersonAffiliatedToThisStation = listOfPersonAffiliatedToThisStation;
	}
}
