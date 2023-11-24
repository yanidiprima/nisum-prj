package com.sinum.user.service;
 

import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO; 
 

public interface UserService {
	
	  public String login(String username, String password);

	  public UserResponseDTO register(UserDTO user);

	  public void delete(String username);
	  
	  public UserResponseDTO searchByUserName(String username);

	  public UserResponseDTO update(UserDTO user);

	  public String refresh(String username);
}
