package com.cjour.SafetyNetAlert.model;

public class Person {
	
	
	

	private MedicalRecord medicalRecord;
	private FireStation fireStation;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phone;
	private String city;
	private String zip;
	private int age = -1;
	
	public Person () {
		super();
	}
	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.zip = zip;
	}
	
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	public FireStation getFireStation() {
		return fireStation;
	}
	public void setFireStation(FireStation fireStation) {
		this.fireStation = fireStation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public String toString() {
		return "Person [medicalRecord=" + medicalRecord + ", fireStation=" + fireStation + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", email=" + email + ", phone=" + phone
				+ ", city=" + city + ", zip=" + zip + ", age=" + age + "]";
	}
}
