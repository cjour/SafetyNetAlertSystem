package com.cjour.SafetyNetAlert.DTO;

public class PersonDTOPhone {
	private String phone;

	public PersonDTOPhone() {
		super();
	}
	
	public PersonDTOPhone(String phone) {
		super();
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PersonDTOPhone [phone=" + phone + "]";
	}
}
