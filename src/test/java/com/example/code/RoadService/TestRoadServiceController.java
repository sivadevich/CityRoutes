package com.example.code.RoadService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.code.roadsService.RoadServiceApplication;

@SpringBootTest(classes = RoadServiceApplication.class)
@AutoConfigureMockMvc 
public class TestRoadServiceController extends RoadServiceApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	
	 @Autowired
     private MockMvc mockMvc;
	 
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public  void testfindCityRoutes() throws Exception {
		System.out.println("testfindCityRoutes");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/connected").queryParam("origin","Boston").queryParam("destination", "Newark"))
	        .andExpect(MockMvcResultMatchers.status().is(200))
	        .andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertEquals(actualResponseBody, "Yes");
	}
	
	@Test
	public  void testfindCityRoutesWithRandomData() throws Exception {
		System.out.println("testfindCityRoutes");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/connected").queryParam("origin","LA").queryParam("destination", "Newark"))
	        .andExpect(MockMvcResultMatchers.status().is(200))
	        .andReturn();
		
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertEquals(actualResponseBody, "No");
	}
	
	@Test
	public  void testfindCityRoutesWithNoParamValues() throws Exception {
		System.out.println("testfindCityRoutes");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/connected").queryParam("origin","").queryParam("destination", ""))
	        .andExpect(MockMvcResultMatchers.status().is(200))
	        .andReturn();
		
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertEquals(actualResponseBody, "No");
	        

	}
	
	@Test
	public  void testfindCityRoutesWithNoRequestParams() throws Exception {
		System.out.println("testfindCityRoutes");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/connected").queryParam("","").queryParam("", ""))
	        .andExpect(MockMvcResultMatchers.status().is(400))
	        .andReturn();
	        

	}

}
