package com.sinum.user.mapper;

import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO;
import com.sinum.user.entity.UserEntity;

public interface UserMapper {
	
	UserEntity userDataDtoToUserEntity (UserDTO userDataDTO);
    
    UserResponseDTO userEntityToUserResponseDTO (UserEntity userEntity);

	UserEntity userDtoToUserEntity(UserDTO userDto, UserEntity entity);
}
