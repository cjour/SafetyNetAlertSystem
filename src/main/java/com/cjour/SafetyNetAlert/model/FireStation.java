package com.cjour.SafetyNetAlert.model;

import java.util.ArrayList;
import java.util.List;

public class FireStation {

	//Créer des DTOs qui créer une couche supérieure à ces objets là.
	
	private List<Person> listOfPerson = new ArrayList<Person>();
	private String address;
	private int station;
	
	public FireStation () {
		super();
	}
	
	public FireStation(String address, int station) {
		super();
		this.address = address;
		this.station = station;
	}
	
	
	public List<Person> getListOfPerson() {
		return listOfPerson;
	}

	public void setListOfPerson(List<Person> listOfPerson) {
		this.listOfPerson = listOfPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}


}
