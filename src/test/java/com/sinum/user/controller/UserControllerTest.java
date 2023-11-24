package com.sinum.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO;
import com.sinum.user.service.UserService;
import com.sinum.user.utils.MockUtils; 

@RunWith(MockitoJUnitRunner.Silent.class)
@EnableWebMvc
public class UserControllerTest {
	

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;
	
	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private UserController userController;
	
	private static final String URL = "/api/user";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).addPlaceholderValue("/", URL).build();

	}

	private UserDTO userDTO;
	private UserResponseDTO response;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void registerTest() throws Exception {

		userDTO = MockUtils.getMock("dto/userDTO.json", UserDTO.class);

		when(userService.register(userDTO)).thenReturn(response);

		mockMvc.perform(post(URL + "/register").content(asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andExpect(status().isOk());

	}

	@Test
	public void loginTest() throws Exception {

		mockMvc.perform(post(URL + "/login?username=example&password=example123456")
				.contentType(MediaType.APPLICATION_JSON).content("").accept(MediaType.ALL))
				.andExpect(status().isOk());

	}
	
	@Test
	public void userTest() throws Exception {


		when(userService.searchByUserName("example")).thenReturn(response);
		mockMvc.perform(get(URL + "/example")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDA4NDM5MjksImV4cCI6MTcwMDg0NzUyOX0.B_loH0pnsvWaSJMmRrkgL00GQcpdYZ9BsFiwfcoVl6k")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.ALL))
				.andExpect(status().isOk()); 

	}
	
	@Test
	public void refreshTokenTest() throws Exception {

		mockMvc.perform(get(URL + "/refreshToken")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDA4NDM5MjksImV4cCI6MTcwMDg0NzUyOX0.B_loH0pnsvWaSJMmRrkgL00GQcpdYZ9BsFiwfcoVl6k")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.ALL))
				.andExpect(status().isOk());  

	}
	 
	
	@Test
	public void deleteUserTest() throws Exception {

		mockMvc.perform(delete(URL + "/example")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDA4NDM5MjksImV4cCI6MTcwMDg0NzUyOX0.B_loH0pnsvWaSJMmRrkgL00GQcpdYZ9BsFiwfcoVl6k")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.ALL))
				.andExpect(status().isOk()); 

	}
}
