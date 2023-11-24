package com.sinum.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinum.user.dto.UserDTO;
import com.sinum.user.dto.UserResponseDTO;
import com.sinum.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@Api(tags = "user")
@RequiredArgsConstructor
public class UserController {
	
	  private final UserService userService;
	  private final ModelMapper modelMapper;
	  
	  @PostMapping("/login")
	  @ApiOperation(value = "${UserController.login}")
	  @Operation(summary = "User login with credentials")
	  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
	  public String login(
	      @ApiParam("Username") @RequestParam String username, //
	      @ApiParam("Password") @RequestParam String password) {
	    return userService.login(username, password);
	  }

	  @PostMapping("/register")
	  @Operation(summary = "Create a new user")
	  @ApiOperation(value = "${UserController.register}")
	  @ApiResponses(value = {
	      @ApiResponse(code = 400, message = "Something went wrong"), 
	      @ApiResponse(code = 403, message = "Access denied"), 
	      @ApiResponse(code = 422, message = "Username is already in use")})
	  public com.sinum.user.dto.UserResponseDTO signup(@ApiParam("Register User") @RequestBody UserDTO user) {
	    return userService.register(user);
	  }

	  @DeleteMapping(value = "/{username}") 
	  @Operation(summary = "Delete a user only with username")
	  @ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value="apiKey") })
	  @ApiResponses(value = {
	      @ApiResponse(code = 400, message = "Something went wrong"), 
	      @ApiResponse(code = 403, message = "Access denied"), 
	      @ApiResponse(code = 404, message = "The user doesn't exist"), 
	      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	  public String delete(@ApiParam("Username") @PathVariable String username) {
	    userService.delete(username);
	    return username;
	  }

	  @GetMapping(value = "/{username}") 
	  @Operation(summary = "Get username details")
	  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"), //
	      @ApiResponse(code = 403, message = "Access denied"), //
	      @ApiResponse(code = 404, message = "The user doesn't exist"),
	      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	  public UserResponseDTO search(@ApiParam("username") @PathVariable String username) {
	    return modelMapper.map(userService.searchByUserName(username), UserResponseDTO.class);
	  }

	  @PutMapping(value = "/update")
	  @Operation(summary = "Update user details") 
	  @ApiOperation(value = "${UserController.update}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"),
	      @ApiResponse(code = 403, message = "Access denied"), 
	      @ApiResponse(code = 404, message = "The user doesn't exist"),
	      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	  public UserResponseDTO update(@RequestBody UserDTO req) {
	    return userService.update(req);
	  }

	  @GetMapping("/refreshToken")
	  @Operation(summary = "Refresh Token ") 
	  public String refresh(HttpServletRequest req) {
	    return userService.refresh(req.getRemoteUser());
	  }
}
