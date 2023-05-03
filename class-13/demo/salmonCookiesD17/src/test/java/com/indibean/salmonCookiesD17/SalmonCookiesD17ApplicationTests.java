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

  @Test
  void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
      .andDo(print())
      .andExpect(content().string(containsString("<h1>Salmon Cookies!</h1>")))
      .andExpect(content().string(containsString("<form action=\"/employee/add\" method=\"POST\">")))
      .andExpect(status().isOk());
  }

  @Test
  void testPostCookieStore() throws Exception {
    mockMvc.perform(
      post("/cookie-store/")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("name", "Indi's")
        .param("averageCookiesPerDay", String.valueOf(5000))
    )
      .andExpect(redirectedUrl("/"))
      .andExpect(status().isFound());
  }

	@Test
	void contextLoads() {
	}

}
