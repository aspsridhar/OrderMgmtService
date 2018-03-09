package com.oms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.oms.App;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyFindOrderById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/order/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.orderId").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.category").exists())
		.andExpect(jsonPath("$.orderId").value(3))
		.andExpect(jsonPath("$.category").value("Bricks"))
		.andExpect(jsonPath("$.completed").value(true))
		.andDo(print());
	}
	
	@Test
	public void verifyCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/order/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"noOfBricks\" : \"20\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.orderId").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.category").exists())
		.andExpect(jsonPath("$.category").value("New Order Sample"))
		.andExpect(jsonPath("$.completed").value(true))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/order/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"orderReferanceID\": \"12453\", \"description\" : \"New Order Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, orderReferanceID must not be defined"))
		.andDo(print());
	}
	

}
