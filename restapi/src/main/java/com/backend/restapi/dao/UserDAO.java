package com.backend.restapi.dao;

import java.util.List;

import com.backend.restapi.dto.User1;


/**
 * 
 * @author saddam
 *
 */

public interface UserDAO {

	boolean register(User1 user);

	// List of product
	List<User1> list();

	
	
	// save image to database usign blob file
		//Test saveImage(Test test);
	
}
