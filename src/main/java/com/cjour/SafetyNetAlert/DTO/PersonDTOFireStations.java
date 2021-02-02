package com.cjour.SafetyNetAlert.DTO;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public class PersonDTOFireStations {
	
	
	private String lastName;
	private String phone;
	private int age;
	private String address;
	private MedicalRecord medicalRecord;
	
	public PersonDTOFireStations () {
		super();
	}

	public PersonDTOFireStations(MedicalRecord medicalRecord, String lastName, String address, String phone, int age) {
		super();
		this.medicalRecord = medicalRecord;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}