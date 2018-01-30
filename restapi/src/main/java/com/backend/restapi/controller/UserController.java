package com.backend.restapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.restapi.dto.Address;
import com.backend.restapi.dto.User;
import com.backend.restapi.model.UserRequest;
import com.backend.restapi.model.UserResponse;

@Controller
@EnableWebMvc
@RequestMapping(value ="/user")
public class UserController {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	/**
	 * Add user using shop_id and user details
	 * */
	
	@RequestMapping(value ="/add/details", method = RequestMethod.POST)
	public @ResponseBody UserResponse addUserDetails(@RequestBody UserRequest userRequest) {
		
		logger.debug("Entering addUserDetails() in user conroller ");
		
	User user = userRequest.getUserDetails();
	Address address = userRequest.getUserAddress();
	
	System.out.println(user +" "+ address);
		
		return null;
	}
	

}
