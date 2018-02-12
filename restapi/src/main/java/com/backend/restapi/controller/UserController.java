package com.backend.restapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.dto.Address;
import com.backend.restapi.dto.User;
import com.backend.restapi.dto.UserAddress;
import com.backend.restapi.model.UserRequest;
import com.backend.restapi.model.UserResponse;

@Controller
@EnableWebMvc
@RequestMapping(value ="/user")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	/**
	 * Add user using shop_id and user details
	 * */
	
	@RequestMapping(value ="/add/details", method = RequestMethod.POST)
	public @ResponseBody UserRequest addUserDetails(@RequestBody UserRequest userRequest) {
		
		logger.debug("Entering addUserDetails() in user conroller ");
		try {	
	User user = userRequest.getUserDetails();
	Address address = userRequest.getUserAddress();
	
	//UserAddress userAddress= null;
	String Shop = "Shop_";
	
	
	/**
	 * This is for ShopKeeper add the new Shopkeeper
	 * **/
	
	/**
	 * check  how many shopkeeper are exist in our database
	 * if not empty shopkeeper then add as 1st shopkeeper
	 * if exist some shopkeeper then add and create new shopkeeper 
	 * **/
	
	    if(userRequest.getShop_ID() == null || userRequest.getShop_ID().isEmpty()) {
	    	
	    	//check how many uique shopid or get list of shopID from database
	    	List<UserAddress> shopList = userDAO.listOfShop(userRequest);
	    	
	    	System.out.println(shopList.size());
	    	
	    	
	    	userRequest.setShop_ID(Shop + (shopList.size()) );
	    	
	    	//Add new Shopkeeper Details
	    	if(userDAO.addShop(userRequest)) {
	    		
	    		userRequest.setShop_ID(Shop + (shopList.size()) );
	    		return userRequest;
	    	}
	    	else {
	    		return null;
	    	}
	    	
	    }
	
	   
	
	
	
	
	//List<UserAddress> userList = userDAO.listOfUser(userRequest);
	
	
	
	
	//System.out.println(user +" "+ address);
		
		return null;
		}catch (RuntimeException re)
		{
			logger.error("get failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		
	    }
	}
	

}
