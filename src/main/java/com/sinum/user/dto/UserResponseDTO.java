package com.sinum.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;  

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
	
	  @ApiModelProperty(position = 0)
	  private Integer id;
	  
	  @ApiModelProperty(position = 1)
	  private String uuid;
	  
	  @ApiModelProperty(position = 2)
	  private String name;
	  
	  @ApiModelProperty(position = 3)
	  private String username;
	  
	  @ApiModelProperty(position = 4)
	  private String email;
	  
	  @ApiModelProperty(position = 5)
	  private Boolean isActive;
	  
	  @ApiModelProperty(position = 6)
	  @JsonFormat(shape = JsonFormat.Shape.STRING)
	  @JsonSerialize(using = LocalDateSerializer.class)
	  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	  private LocalDate createdDate;

	  @ApiModelProperty(position = 7)
	  @JsonFormat(shape = JsonFormat.Shape.STRING)
	  @JsonSerialize(using = LocalDateSerializer.class)
	  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	  private LocalDate modifyDate;

	  @ApiModelProperty(position = 8)
	  @JsonFormat(shape = JsonFormat.Shape.STRING)
	  @JsonSerialize(using = LocalDateSerializer.class)
	  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	  private LocalDate lastLogin;
	  
	  @ApiModelProperty(position = 9)
	  private List<PhoneResponseDTO> phones;  
	  
	  @ApiModelProperty(position = 10)
	  private String token;

}
