package com.cjour.SafetyNetAlert.DTO;

import java.util.ArrayList;

import com.cjour.SafetyNetAlert.model.Person;

public class PersonDTOChild {
	
	private String firstName;
	private String lastName;
	private int age;
	private ArrayList<Person> related;
	
	public PersonDTOChild() {
		super();
	}

	public PersonDTOChild(String firstName, String lastName, int age, ArrayList<Person> related) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.related = related;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Person> getRelated() {
		return related;
	}

	public void setRelated(ArrayList<Person> related) {
		this.related = related;
	}
	
	
}
