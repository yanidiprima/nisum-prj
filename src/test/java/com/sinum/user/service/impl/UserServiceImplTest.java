package com.sinum.user.service.impl;
 
import static org.mockito.ArgumentMatchers.any; 
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner; 
import org.springframework.test.util.ReflectionTestUtils;

import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO; 
import com.sinum.user.entity.UserEntity;
import com.sinum.user.mapper.UserMapper;
import com.sinum.user.repository.UserRepository;
import com.sinum.user.security.JwtTokenProvider; 

@RunWith(MockitoJUnitRunner.class) 
public class UserServiceImplTest {
	
	@Mock
    private UserRepository repository;
    @InjectMocks
	private UserServiceImpl service;
    @Mock 
	private  JwtTokenProvider jwtTokenProvider;
	
	 @Mock
	 private UserMapper mapper;
	 
	 private UserEntity userEntity; 
	 private UserResponseDTO response;
	 private UserDTO userDto;
	 private String USER = "user";
	 private String EMAIL = "email@email.com.cl";
	 private String REGEX_PATTERN = "regexPattern";
	 private String REGEX_PATTERN_VALUE = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}+.cl";
	 
    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(service, REGEX_PATTERN, REGEX_PATTERN_VALUE);
    	response =  UserResponseDTO.builder().id(1).username(USER)
    			.build();
    	userEntity =  UserEntity.builder().id(1).username(USER).email(EMAIL)
    			.build(); 
    	userDto = UserDTO.builder().username(USER).email(EMAIL).build();
      
    	 
    } 
	@Test
    public void whenFindByUserNameThenReturnDto() throws IOException { 
        
        when(repository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
        when(mapper.userEntityToUserResponseDTO(userEntity)).thenReturn(response);
        UserResponseDTO dtoFound = service.searchByUserName(USER);

        assertThat(response.toString(), equalTo(dtoFound.toString()));
    }
	
	@Test
    public void whenRegisterDtoThenReturnDto() throws IOException {
		
        when(mapper.userDataDtoToUserEntity(any())).thenReturn(userEntity);
        when(repository.save(any())).thenReturn(userEntity);
        when(mapper.userEntityToUserResponseDTO(userEntity)).thenReturn(response);
         
         
        UserResponseDTO response = service.register(userDto);

        assertThat(userDto.getUsername(), equalTo(response.getUsername()));
    }
	
	@Test
    public void whenUpdateDtoThenReturnDto() throws IOException { 

        when(repository.findByUsername(userEntity.getUsername())).thenReturn(userEntity);
        when(mapper.userDtoToUserEntity(userDto, userEntity)).thenReturn(userEntity);
        when(repository.save(any())).thenReturn(userEntity);
        when(mapper.userEntityToUserResponseDTO(userEntity)).thenReturn(response);

        
        UserResponseDTO dtoFound = service.update(userDto);

        assertThat(response.toString(), equalTo(dtoFound.toString()));
    }
}