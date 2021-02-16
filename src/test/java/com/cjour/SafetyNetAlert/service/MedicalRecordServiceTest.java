package com.cjour.SafetyNetAlert.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjour.SafetyNetAlert.model.MedicalRecord;
import com.cjour.SafetyNetAlert.repository.Database;

@SpringBootTest
public class MedicalRecordServiceTest {

	@Autowired
	private MedicalRecordServiceImpl medicalRecordService;

	private static ArrayList<MedicalRecord> listOfMedicalRecords;
	private MedicalRecord medicalRecord1 = new MedicalRecord("Mouchette", "Le chat", "25/05/2019",
			new ArrayList<String>(), new ArrayList<String>());

	@MockBean
	private Database database;

	@BeforeEach
	public void setUp() {
		listOfMedicalRecords = new ArrayList<>();
		listOfMedicalRecords.add(medicalRecord1);
	}

	@AfterEach
	public void cleanDB() {
		listOfMedicalRecords= null;
	}

	@Test
	public void findAll() {
		// GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);

		// WHEN
		ArrayList<MedicalRecord> result = medicalRecordService.findAll();

		// THEN
		assertFalse(result.isEmpty());
		assertTrue(result.get(0).equals(medicalRecord1));

	}

	@Test
	public void getAMedicalRecordTest() {
		// GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);

		// WHEN
		MedicalRecord result = medicalRecordService.getAMedicalRecord("Mouchette", "Le chat");

		// THEN
		assertTrue(result.equals(medicalRecord1));
	}

	@Test
	public void deleteTest() {
		// GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);

		// WHEN
		boolean result = medicalRecordService.delete(medicalRecord1);

		// THEN
		assertTrue(result);
		assertTrue(listOfMedicalRecords.isEmpty());
	}

	@Test
	public void addAMedicalRecordTest() {
		// GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		MedicalRecord medicalRecord2 = new MedicalRecord("Clément", "Jourdain", "17/05/1992", null, null);

		// WHEN
		boolean result = medicalRecordService.addAMedicalRecord(medicalRecord2);

		// THEN
		assertTrue(result);
		assertTrue(listOfMedicalRecords.size() == 2);
		assertTrue(listOfMedicalRecords.get(1).equals(medicalRecord2));
	}
	
	@Test
	public void addAMedicalRecordAlreadyTakenNamesTest() {
		//GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		MedicalRecord invalidMedicalRecord = new MedicalRecord("Mouchette", "Le chat", "17/05/1992", null, null);

		
		//WHEN
		boolean result = medicalRecordService.addAMedicalRecord(invalidMedicalRecord);		
		//THEN
		assertFalse(result);

	}
	
	@Test
	public void updateBirthdateForMedicalRecordTest() {
		//GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		MedicalRecord medicalRecordToUpdate = medicalRecord1;
		MedicalRecord medicalRecordForUpdate = new MedicalRecord("Mouchette", "Le chat", "20/05/2019", null, null);
		//WHEN
		boolean result = medicalRecordService.updateMedicalRecord(medicalRecordToUpdate, medicalRecordForUpdate);
		
		//THEN
		assertTrue(result);
		assertTrue(listOfMedicalRecords.get(0).getBirthdate().equals("20/05/2019"));
		assertFalse(listOfMedicalRecords.get(0).getBirthdate().equals("25/05/2019"));
	}
	
	@Test
	public void updateAllergiesForMedicalRecordTest() {
		//GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		ArrayList<String> allergies = new ArrayList<>();
		allergies.add("Coriza");
		MedicalRecord medicalRecordToUpdate = medicalRecord1;
		MedicalRecord medicalRecordForUpdate = new MedicalRecord("Mouchette", "Le chat", null, null, allergies);
		//WHEN
		boolean result = medicalRecordService.updateMedicalRecord(medicalRecordToUpdate, medicalRecordForUpdate);
		
		//THEN
		assertTrue(result);
		assertTrue(listOfMedicalRecords.get(0).getAllergies().get(0).equals("Coriza"));

	}
	
	@Test
	public void updateMedicationsForMedicalRecordTest() {
		//GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		ArrayList<String> medications = new ArrayList<>();
		medications.add("Cuddles");
		MedicalRecord medicalRecordToUpdate = medicalRecord1;
		MedicalRecord medicalRecordForUpdate = new MedicalRecord("Mouchette", "Le chat", null, medications, null);
		//WHEN
		boolean result = medicalRecordService.updateMedicalRecord(medicalRecordToUpdate, medicalRecordForUpdate);
		
		//THEN
		assertTrue(result);
		assertTrue(listOfMedicalRecords.get(0).getMedications().get(0).equals("Cuddles"));

	}
	
	@Test
	public void updateMedicalRecordInvalidNamesTest() {
		//GIVEN
		when(database.getMedicalRecordList()).thenReturn(listOfMedicalRecords);
		
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("Je n'existe pas", "", null, null, null);
		MedicalRecord medicalRecordForUpdate = new MedicalRecord("Je n'existe pas", "", null, null, null);
		//WHEN
		boolean result = medicalRecordService.updateMedicalRecord(medicalRecordToUpdate, medicalRecordForUpdate);
		
		//THEN
		assertFalse(result);

	}
}
