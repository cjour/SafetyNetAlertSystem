package com.cjour.SafetyNetAlert.DTO;

public class PersonDTOEmail {

	private String email;

	public PersonDTOEmail() {
		super();
	}
	
	public PersonDTOEmail(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonDTOEmail [email=" + email + "]";
	}
}
