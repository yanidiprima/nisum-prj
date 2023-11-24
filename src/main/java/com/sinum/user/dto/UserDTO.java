package com.sinum.user.dto; 
 
import java.util.List;

import javax.validation.constraints.NotNull;
 

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {
	
	  @NotNull
	  @ApiModelProperty(position = 0)
	  private String name;
	  
	  @NotNull
	  @ApiModelProperty(position = 1)
	  private String username;

	  @NotNull
	  @ApiModelProperty(position = 2)
	  private String email; 
	  
	  @NotNull
	  @ApiModelProperty(position = 3)
	  private String password;
	  
	  @NotNull
	  @ApiModelProperty(position = 4)
	  private List<PhoneDTO> phones; 
	   
}
