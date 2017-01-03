package com.ironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironyard.entities.User;
import com.ironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngularSpringApplicationTests {

	@Autowired
	WebApplicationContext wap;

	@Autowired
	UserRepository users;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void addUser() throws Exception {
		User user = new User();
		user.setUsername("Alice");
		user.setAddress("17 Princess St");
		user.setEmail("alice@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.content(json)
						.contentType("application/json")
		);

		Assert.assertTrue(users.count() == 1);
	}

	@Test
	public void deleteUser() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/user/1")
		);

		Assert.assertTrue(users.count() == 0);
	}
}


