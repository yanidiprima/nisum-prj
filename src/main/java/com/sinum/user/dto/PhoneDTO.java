package com.sinum.user.dto;
 
 

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PhoneDTO {

	  @ApiModelProperty(position = 0)
	  private String numberPhone;

	  @ApiModelProperty(position = 1)
	  private String cityCode;

	  @ApiModelProperty(position = 2)
	  private String countryCode;
}
