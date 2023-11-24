package com.sinum.user.service.impl;

import java.util.regex.Pattern; 

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO;
import com.sinum.user.entity.UserEntity;
import com.sinum.user.exception.CustomException;
import com.sinum.user.mapper.UserMapper;
import com.sinum.user.repository.UserRepository;
import com.sinum.user.security.JwtTokenProvider;
import com.sinum.user.service.UserService;
import com.sinum.user.utils.Constants;

import lombok.RequiredArgsConstructor;
  

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository; 
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final UserMapper userMapper;
	
	@Value("${regex.pattern}")
	private String regexPattern;

	@Override
	public String login(String username, String password) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			return jwtTokenProvider.createToken(username);

		} catch (AuthenticationException e) {

			throw new CustomException(Constants.INVALID_CREDENCIALS, HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	@Override
	public UserResponseDTO register(UserDTO userDto) {
		
		
		validateUserData(userDto);
		
		UserEntity appUser = userMapper.userDataDtoToUserEntity(userDto);

		appUser = userRepository.save(appUser);

		UserResponseDTO userResponseDTO = userMapper.userEntityToUserResponseDTO(appUser);
		userResponseDTO.setToken(jwtTokenProvider.createToken(userDto.getUsername()));

		return userResponseDTO;

	}

	private  boolean patternMatches(String emailAddress) {
		  
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	@Override
	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	@Override
	public UserResponseDTO searchByUserName(String username) {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new CustomException(Constants.USER_NOT_EXISTS, HttpStatus.NOT_FOUND);
		}
		return userMapper.userEntityToUserResponseDTO(user); 
	}
 

	@Override
	public String refresh(String username) {
		return jwtTokenProvider.createToken(username);
	}
	

	@Override
	public UserResponseDTO update(UserDTO user) {
		
		UserEntity userEntity = userRepository.findByUsername(user.getUsername());
		if(userEntity == null) {
			throw new CustomException(Constants.USER_NOT_EXISTS, HttpStatus.NOT_FOUND);			
		}

		validateUpdateUser(user, userEntity);
		
		userMapper.userDtoToUserEntity(user, userEntity);

		userRepository.save(userEntity);
		
		UserResponseDTO userResponseDTO = userMapper.userEntityToUserResponseDTO(userEntity); 

		return userResponseDTO;
	}
	

	private void validateUserData(UserDTO userDto) {
		
		validateRequiredData(userDto);
		
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new CustomException(Constants.USERNAME_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new CustomException(Constants.EMAIL_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if (!patternMatches(userDto.getEmail())) {
			throw new CustomException(Constants.EMAIL_FORMAT_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private void validateUpdateUser(UserDTO userDto, UserEntity entity) {
		
		if(!userDto.getEmail().equalsIgnoreCase(entity.getEmail())) {
			throw new CustomException(Constants.EMAIL_NOT_ALLOWED, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(!userDto.getUsername().equalsIgnoreCase(entity.getUsername())) {
			throw new CustomException(Constants.USERNAME_NOT_ALLOWED, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private void validateRequiredData(UserDTO userDto) {
		
		if (StringUtils.isBlank(userDto.getUsername())) {
			throw new CustomException(Constants.USERNAME_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if (StringUtils.isBlank(userDto.getEmail())) {
			throw new CustomException(Constants.EMAIL_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
