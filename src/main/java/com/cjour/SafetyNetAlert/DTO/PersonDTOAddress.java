package com.cjour.SafetyNetAlert.DTO;

import com.cjour.SafetyNetAlert.model.MedicalRecord;

public class PersonDTOAddress {
	private String lastName;
	private int age;
	private String phone;
	private MedicalRecord medicalRecord;
	
	public PersonDTOAddress() {
		super();
	}

	public PersonDTOAddress(String lastName, int age, String phone, MedicalRecord medicalRecord) {
		super();
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.medicalRecord = medicalRecord;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	@Override
	public String toString() {
		return "PersonDTOAddress [lastName=" + lastName + ", age=" + age + ", phone=" + phone + ", medicalRecord="
				+ medicalRecord + "]";
	}	
}
