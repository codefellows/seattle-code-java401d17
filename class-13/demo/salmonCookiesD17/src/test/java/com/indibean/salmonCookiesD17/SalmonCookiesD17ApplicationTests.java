package com.indibean.salmonCookiesD17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SalmonCookiesD17ApplicationTests {
  @Autowired
  MockMvc mockMvc;

  // Tests the @GetMethod("/") is working correctly
  // it should direct us to the requested URL and return some bit of HTML
  // .print() will print all of the HTML that is returned to the console (NOT REQUIRED FOR THE TEST TO PASS)
  // the .andExpects() are saying that our .perform() is expecting some content in the form of string to be return
  // that string content returned in our HTML for our webpage
  // somewhere in that string of HTML we should expect some substring that we chose form our .html file
  // ultimately, we are checking if the HTML returned from perform() is the same as the HTML we grabbed from our file
  // the @GetMethod("/") is defined in SalmonCookiesController.java
  @Test
  void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
      .andDo(print())
      .andExpect(content().string(containsString("<h1>Salmon Cookies!</h1>")))
      .andExpect(content().string(containsString("<form action=\"/employee/add\" method=\"POST\">")))
      .andExpect(status().isOk());
  }

  // Tests that the @PostMethod("/cookie-store/") is working correctly
  // it should create a new store with the given name and averageCookiesPerDay
  // then it should redirect me back to
  // The first input in the param() is the parameter that the @PostMethod is looking for
  // the second input in the param() is the value that I want to set for that field of my Store
  // The post method's definition can be found in SalmonCookiesController.java
  @Test
  void testPostCookieStore() throws Exception {
    mockMvc.perform(
      post("/cookie-store/")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("name", "Indi's")
        .param("averageCookiesPerDay", String.valueOf(500))
    )
      .andExpect(redirectedUrl("/"))
      .andExpect(status().isFound());
  }

	@Test
	void contextLoads() {
	}

}
