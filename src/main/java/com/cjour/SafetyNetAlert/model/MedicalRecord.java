package com.cjour.SafetyNetAlert.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MedicalRecord {

	private Date birthDate;
	private Map<String, Integer> drugs;
	private ArrayList<String> allergies;
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Map<String, Integer> getDrugs() {
		return drugs;
	}
	public void setDrugs(Map<String, Integer> drugs) {
		this.drugs = drugs;
	}
	public ArrayList<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}
}
