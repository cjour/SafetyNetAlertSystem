package com.cjour.SafetyNetAlert.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class RequestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RequestController requestController;
	
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
		mockMvc.perform(get("/firestation?station_number=1")).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetcallphoneAlert() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetcallfire() throws Exception {
		mockMvc.perform(get("/fire?address=")).andExpect(status().isOk());
		
	}
	
}
