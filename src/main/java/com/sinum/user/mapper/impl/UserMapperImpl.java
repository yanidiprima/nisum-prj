package com.sinum.user.mapper.impl;

import java.time.LocalDate; 
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sinum.user.dto.PhoneDTO;
import com.sinum.user.dto.PhoneResponseDTO;
import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO;
import com.sinum.user.entity.PhoneEntity;
import com.sinum.user.entity.UserEntity;
import com.sinum.user.mapper.UserMapper;

import lombok.AllArgsConstructor;
 
@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper  {
	
	private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity userDataDtoToUserEntity (UserDTO userDto) {
    	
    	return UserEntity.builder()
    	        .password(passwordEncoder.encode(userDto.getPassword()))
    	        .uuid(java.util.UUID.randomUUID().toString())
				.email(userDto.getEmail())
				.username(userDto.getUsername())
				.name(userDto.getName())
				.isActive(Boolean.TRUE)
				.createdDate(LocalDate.now())
				.lastLogin(LocalDate.now())
				.phones(userDto.getPhones().stream()
						.map((PhoneDTO p) -> PhoneEntity.builder()
								.numberPhone(p.getNumberPhone())
								.cityCode(p.getCityCode())
								.countryCode(p.getCountryCode()).build())
						.collect(Collectors.toList())) 
	  			.build();
         
    }

    @Override
    public UserResponseDTO userEntityToUserResponseDTO (UserEntity userEntity) {
    	
    	return UserResponseDTO.builder()
    	    	.id(userEntity.getId())
    	    	.uuid(userEntity.getUuid())	
				.email(userEntity.getEmail())
				.username(userEntity.getUsername())
				.name(userEntity.getName())
				.isActive(userEntity.getIsActive())
				.createdDate(userEntity.getCreatedDate())
				.lastLogin(userEntity.getLastLogin()) 
				.phones(userEntity.getPhones().stream()
						.map((PhoneEntity p) -> PhoneResponseDTO.builder()
								.numberPhone(p.getNumberPhone())
								.cityCode(p.getCityCode())
								.countryCode(p.getCountryCode()).build())
						.collect(Collectors.toList())) 
	  			.build();   
         
    }
    
    @Override
    public UserEntity userDtoToUserEntity (UserDTO userDto, UserEntity entity) {
    	entity.setName(userDto.getName());
    	entity.setPhones(userDto.getPhones().stream()
						.map((PhoneDTO p) -> PhoneEntity.builder()
								.numberPhone(p.getNumberPhone())
								.cityCode(p.getCityCode())
								.countryCode(p.getCountryCode()).build())
						.collect(Collectors.toList()));
    	entity.setModifyDate(LocalDate.now()); 
    	return entity; 
         
    }
    

}
