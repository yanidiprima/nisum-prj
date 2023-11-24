package com.sinum.user.entity;
 

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size; 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder 
@Data
@AllArgsConstructor
@NoArgsConstructor  
@Entity
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  
	  @Column(unique = true, nullable = false, length = 36)
	  private String uuid;

	  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	  @Column(unique = true, nullable = false)
	  private String username;
	  
	  @Column(nullable = false)
	  private String name;
	  
	  @Column(unique = true, nullable = false)
	  private String email;

	  @Size(min = 8, message = "Minimum password length: 8 characters")
	  private String password;
	  
	  @Column(nullable = false)
	  private LocalDate createdDate;

	  @Column(nullable = true)
	  private LocalDate modifyDate;

	  @Column(nullable = true)
	  private LocalDate lastLogin;

	  @Column(nullable = false)
	  private Boolean isActive;
	  
	  @OneToMany(cascade = CascadeType.ALL)
	  private List<PhoneEntity> phones;
	   
}
