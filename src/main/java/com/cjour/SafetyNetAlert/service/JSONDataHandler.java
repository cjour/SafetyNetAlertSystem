package com.cjour.SafetyNetAlert.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataHandler {

	// ObjectMapper is the entity used to write/read JSON data.
	private static final ObjectMapper mapper = new ObjectMapper();

	
	public ArrayList<Person> fetchingDataFromJSONForPersons() {

		ArrayList<Person> list = new ArrayList<Person>();

		try {
			JsonNode jsonNodeT = mapper.readTree(new URL(
					"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"))
					.get("persons");
			String jsonNodeAsString = mapper.writeValueAsString(jsonNodeT);
			list = mapper.readValue(jsonNodeAsString, new TypeReference<ArrayList<Person>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<FireStation> fetchingDataFromJSONForFireStation() {

		ArrayList<FireStation> list = new ArrayList<FireStation>();

		try {
			JsonNode jsonNodeT = mapper.readTree(new URL(
					"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"))
					.get("firestations");
			String jsonNodeAsString = mapper.writeValueAsString(jsonNodeT);
			list = mapper.readValue(jsonNodeAsString, new TypeReference<ArrayList<FireStation>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MedicalRecord> fetchingDataFromJSONForMedicalRecords() {

		ArrayList<MedicalRecord> list = new ArrayList<MedicalRecord>();

		try {
			JsonNode jsonNodeT = mapper.readTree(new URL(
					"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"))
					.get("medicalrecords");
			String jsonNodeAsString = mapper.writeValueAsString(jsonNodeT);
			list = mapper.readValue(jsonNodeAsString, new TypeReference<ArrayList<MedicalRecord>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

// I should try to develop a generic function but cannot manage to find how to pass the class to POJO as parameter.
//	
//	public <T> List<T> fetchingDataFromJSON(String nodeValue, Class<T> POJO) {
//
//		List<T> list = new ArrayList<T>();
//
//		try {
//			JsonNode jsonNodeT = mapper.readTree(new URL(
//					"https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"))
//					.get(nodeValue);
//			String jsonNodeAsString = mapper.writeValueAsString(jsonNodeT);
//			list = mapper.readValue(jsonNodeAsString, new TypeReference<List<T>>() {
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
}
