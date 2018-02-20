package com.backend.restapi.dao;

import java.util.List;

import com.backend.restapi.dto.User1;
import com.backend.restapi.dto.UserAddress;
import com.backend.restapi.model.UpdateUserShopRequest;
import com.backend.restapi.model.UpdateUserShopResponse;
import com.backend.restapi.model.UserRequest;


/**
 * 
 * @author saddam
 *
 */

public interface UserDAO {
	
	
	/**
	 * crete method for geting list of shopid for add shopkeeper into database
	 * ***/
	
	 List<UserAddress> listOfShop(UserRequest userRequest);
	
	 
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
	
}
