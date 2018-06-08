package com.backend.restapi.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;

import com.backend.restapi.user.dto.Pushnotification;
import com.backend.restapi.user.dto.User;
import com.backend.restapi.user.dto.User1;
import com.backend.restapi.user.dto.UserAddress;
import com.backend.restapi.user.dto.User_Data;
import com.backend.restapi.user.model.UpdateRequest;
import com.backend.restapi.user.model.UpdateUserShopRequest;
import com.backend.restapi.user.model.UpdateUserShopResponse;
import com.backend.restapi.user.model.UserRequest;


/**
 * 
 * @author saddam
 *
 */

public interface UserDAO {
	
	
	/**
	 * crete method for geting list of shopid for add shopkeeper into database
	 * ***/
	
	 List<User> listOfShop(UserRequest userRequest);
	
	 
	 /**
	  * Add new Shop Keeper Details
	  * **/
	 boolean addShop(UserRequest userRequest);
	
	/**
	 * Update user and shopkeeper update 
	 * 
	 * **/
	
	 UpdateUserShopResponse updateUserShop(UpdateUserShopRequest updateRequest);
	// Address updateUserShop(UpdateUserShopRequest updateRequest);
	

	boolean register(User1 user);

	// List of product
	List<User1> list();

	
	
	// save image to database usign blob file
		//Test saveImage(Test test);
	
	//Show list of user
	
	List<UserAddress> listOfUser(UserRequest userRequest);
	
	
	
	/**
	 * For only one user details 
	 * **/
	
	User_Data userDetailByShopIdAndUserId(UpdateRequest updateRequest);
	
	
	/**
	 * Getting all user details using shopID
	 * ***/
	
	List<User_Data> userDetailsByShopID(UpdateRequest updateRequest);
	
	
	/**
	 * This is for push notification method 
	 * **/
	CompletableFuture<String> send (HttpEntity<String> entity);
	
	
	/*
	 *  This method for the push notification
	 *  saving device id using Shop_ID
	 */
	
	boolean saveDeviceID(Pushnotification pushNotification);
	
	
	/**
	 * Get Device ID from  Database
	 * 
	 * */
	
	boolean getDeviceID(String Shop_ID,String message);
	
}
