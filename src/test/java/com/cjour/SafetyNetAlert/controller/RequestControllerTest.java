package com.cjour.SafetyNetAlert.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.cjour.SafetyNetAlert.model.FireStation;
import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.service.FireStationServiceImpl;
import com.cjour.SafetyNetAlert.service.MedicalRecordServiceImpl;
import com.cjour.SafetyNetAlert.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
	private PersonServiceImpl personService;
	
	@MockBean
	private FireStationServiceImpl fireStationService;
	
	@MockBean
	private MedicalRecordServiceImpl medicalRecordService;

	@Test
	public void testGetcall() throws Exception {
		
		mockMvc.perform(get("/childAlert?address=")).andExpect(status().isOk());
	}

	@Test
	public void testGetcallCommunityEmail() throws Exception {
		mockMvc.perform(get("/communityEmail?city=")).andExpect(status().isOk());
	}

	@Test
	public void testGetcallfirestation() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
	}

	@Test
	public void testGetcallphoneAlert() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());
	}

	@Test
	public void testGetcallfire() throws Exception {
		mockMvc.perform(get("/fire?address=")).andExpect(status().isOk());
	}

	@Test
	public void testGetpersonInfo() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=&lastName=")).andExpect(status().isOk());
	}
	
	@Test 
	public void testGetFireStations() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());
	}
	
	@Test 
	public void testGetPersons() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}
	
	@Test 
	public void testGetMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalRecords")).andExpect(status().isOk());
	}
	
	@Test 
	public void testPostPerson() throws Exception {		
		when(personService.addAPerson(Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Someone", "Someone", null, null, null, null, null))))
		.andExpect(status().isCreated());
	}
	
	@Test 
	public void testPostPersonInvalidName() throws Exception {		
		when(personService.addAPerson(Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Someone", null, null, null, null, null, null))))
		.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void testPostPersonAlreadyTakenName() throws Exception {	
		when(personService.addAPerson(Mockito.any(Person.class))).thenReturn(false);
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Someone", "Someone", null, null, null, null, null))))
		.andExpect(status().isConflict());
	}
	
	@Test 
	public void testPostFireStation() throws Exception {		
		when(fireStationService.addAFireStation(Mockito.any(FireStation.class))).thenReturn(true);
		
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("some address", 123))))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testPostFireStationInvalidInfo() throws Exception {		
		when(fireStationService.addAFireStation(Mockito.any(FireStation.class))).thenReturn(true);
		
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation(null, 666))))
		.andExpect(status().isBadRequest());
	}
	

	@Test
	public void testPostFireStationAlreadyExist() throws Exception {		
		when(fireStationService.addAFireStation(Mockito.any(FireStation.class))).thenReturn(false);
		
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("some other address", 666))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testPostMedicalRecord() throws Exception {	
		when(medicalRecordService.addAMedicalRecord(Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Me", "Me again", null, null, null))))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testPostMedicalRecordInvalidNames() throws Exception {		
		when(medicalRecordService.addAMedicalRecord(Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord(null, "Me again", null, null, null))))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testPostMedicalRecordAlreadyTakenNames() throws Exception {		
		when(medicalRecordService.addAMedicalRecord(Mockito.any(MedicalRecord.class))).thenReturn(false);
		
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Me", "Me again", null, null, null))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testPutPerson() throws Exception{
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());
		when(personService.updatePerson(Mockito.any(Person.class), Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("", "", null, null, null, null, null))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutPersonInvalidNames() throws Exception{
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());
		when(personService.updatePerson(Mockito.any(Person.class), Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person(null, "", null, null, null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutPersonNoMatch() throws Exception{
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		when(personService.updatePerson(Mockito.any(Person.class), Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("", "", null, null, null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutPersonErrorOccured() throws Exception{
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());
		when(personService.updatePerson(Mockito.any(Person.class), Mockito.any(Person.class))).thenReturn(false);
		
		mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("", "", null, null, null, null, null))))
		.andExpect(status().isConflict());
	}
	
	
	@Test
	public void testPutFireStation() throws Exception{
		when(fireStationService.getFireStation(Mockito.anyString())).thenReturn(new FireStation());
		when(fireStationService.updateFireStation(Mockito.any(FireStation.class), Mockito.anyInt())).thenReturn(true);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("", 55))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutFireStationInvalidAddress() throws Exception{
		when(fireStationService.getFireStation(Mockito.anyString())).thenReturn(new FireStation());
		when(fireStationService.updateFireStation(Mockito.any(FireStation.class), Mockito.anyInt())).thenReturn(true);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation(null, 55))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutFireStationNonExistingStation() throws Exception{
		when(fireStationService.getFireStation(Mockito.anyString())).thenReturn(null);
		when(fireStationService.updateFireStation(Mockito.any(FireStation.class), Mockito.anyInt())).thenReturn(true);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("", 55))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutFireStationInvalidNumber() throws Exception{
		when(fireStationService.getFireStation(Mockito.anyString())).thenReturn(new FireStation());
		when(fireStationService.updateFireStation(Mockito.any(FireStation.class), Mockito.anyInt())).thenReturn(true);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("", 0))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testPutFireStationAlreadyExisting() throws Exception{
		when(fireStationService.getFireStation(Mockito.anyString())).thenReturn(new FireStation());
		when(fireStationService.updateFireStation(Mockito.any(FireStation.class), Mockito.anyInt())).thenReturn(false);
		
		mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new FireStation("", 55))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testPutMedicalRecord() throws Exception{
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.updateMedicalRecord(Mockito.any(MedicalRecord.class), Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Me", "Me again", null, null, null))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutMedicalRecordInvalidName() throws Exception{
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.updateMedicalRecord(Mockito.any(MedicalRecord.class), Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord(null, "Me again", null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutMedicalRecordNoMatch() throws Exception{
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		when(medicalRecordService.updateMedicalRecord(Mockito.any(MedicalRecord.class), Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Unknown", "Unknown", null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPutMedicalRecordErrorOccured() throws Exception{
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.updateMedicalRecord(Mockito.any(MedicalRecord.class), Mockito.any(MedicalRecord.class))).thenReturn(false);
		
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Unknown", "Unknown", null, null, null))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testDeletePerson() throws Exception {
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());

		when(personService.delete(Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(delete("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Clément", "Jourdain", null, null, null, null, null))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeletePersonInvalidNames() throws Exception {
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());

		when(personService.delete(Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(delete("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person(null, "Jourdain", null, null, null, null, null))))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeletePersonNoMatch() throws Exception {
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

		when(personService.delete(Mockito.any(Person.class))).thenReturn(true);
		
		mockMvc.perform(delete("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Clément", "Jourdain", null, null, null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeletePersonErrorOccured() throws Exception {
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());

		when(personService.delete(Mockito.any(Person.class))).thenReturn(false);
		
		mockMvc.perform(delete("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Clément", "Jourdain", null, null, null, null, null))))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void testDeleteMedicalRecord() throws Exception {
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());
		
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());
		when(medicalRecordService.delete(Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new MedicalRecord("Clément", "Jourdain", null, null, null))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMedicalRecordInvalidNames() throws Exception {
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());

		when(medicalRecordService.delete(Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person(null, "Jourdain", null, null, null, null, null))))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeleteMedicalRecordNoMatch() throws Exception {
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

		when(medicalRecordService.delete(Mockito.any(MedicalRecord.class))).thenReturn(true);
		
		mockMvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Clément", "Jourdain", null, null, null, null, null))))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteMedicalRecordErrorOccured() throws Exception {
		when(medicalRecordService.getAMedicalRecord(Mockito.anyString(), Mockito.anyString())).thenReturn(new MedicalRecord());
		when(personService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new Person());

		when(medicalRecordService.delete(Mockito.any(MedicalRecord.class))).thenReturn(false);
		
		mockMvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Person("Clément", "Jourdain", null, null, null, null, null))))
		.andExpect(status().isConflict());
	}
}
