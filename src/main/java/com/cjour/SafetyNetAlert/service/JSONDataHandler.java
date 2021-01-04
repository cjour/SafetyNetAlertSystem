package com.cjour.SafetyNetAlert.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.cjour.SafetyNetAlert.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataHandler {

	public void ConvertJSONToObjectPerson() throws MalformedURLException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.readValue(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"), Person.class);
	}
	
}

