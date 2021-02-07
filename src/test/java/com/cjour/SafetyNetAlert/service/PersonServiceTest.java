package com.cjour.SafetyNetAlert.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.cjour.SafetyNetAlert.model.Person;
import com.cjour.SafetyNetAlert.repository.Database;

@SpringBootTest
public class PersonServiceTest {
	
	
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	
	private ArrayList<Person> listOfPerson;
	
	@MockBean
	//Pourquoi @Bean ne fonctionne pas mais @MockBean fonctionne ??????
	private Database database;

	
	@Test
	public void getPersonTest() {
		listOfPerson = new ArrayList<Person>();
		listOfPerson.add(new Person("James", "Bond", "", "", "", "", ""));
		when(database.getPersonList()).thenReturn(listOfPerson);
		Person result = personServiceImpl.getPerson("James", "Bond");
		
		
		assertTrue(result.getFirstName() == "James");
		assertTrue(result.getLastName() == "Bond");

		
	}
}
