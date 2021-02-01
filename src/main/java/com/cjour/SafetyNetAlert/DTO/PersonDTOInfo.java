package com.cjour.SafetyNetAlert.DTO;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public class PersonDTOInfo {
	
	private String lastName;
	private String address;
	private int age;
	private String email;
	private MedicalRecord medicalRecord;;
	
	public PersonDTOInfo () {
		super();
	}
	
	public PersonDTOInfo(String lastName, String address, int age, String email, MedicalRecord medicalRecord) {
		super();
		this.medicalRecord = medicalRecord;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
