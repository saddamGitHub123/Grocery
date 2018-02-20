package com.backend.restapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.dto.Address;
import com.backend.restapi.dto.User;
import com.backend.restapi.dto.UserAddress;
import com.backend.restapi.model.UpdateUserShopRequest;
import com.backend.restapi.model.UpdateUserShopResponse;
import com.backend.restapi.model.UserRequest;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
	String User = "User_";
	
	
	/**
	 * This is for ShopKeeper add the new Shopkeeper
	 * **/
	
	/**
	 * check  how many shopkeeper are exist in our database
	 * if not empty shopkeeper then add as 1st shopkeeper
	 * if exist some shopkeeper then add and create new shopkeeper 
	 * **/
	
	    if(userRequest.getShop_ID() == null || userRequest.getShop_ID().isEmpty()) {
	    	
	    	logger.debug(" Adding Shopkeeper into database ");
	    	
	    	//check how many uique shopid or get list of shopID from database
	    	List<UserAddress> shopList = userDAO.listOfShop(userRequest);
	    	
	    	// check user name ,phoner number and email already save into database or not
	    				for (int j = 0; j < shopList.size(); j++) {
	    					if (shopList.get(j).getUser_Name() == user.getUser_Name() ||
	    							shopList.get(j).getContact() == user.getContact() || 
	    							shopList.get(j).getEmail() == user.getEmail()) {
	    						userRequest.setStatus_code("400");
	    						userRequest.setStatus_message("already username or phone number or email exisit");
	    						userRequest.setUserDetails(null);
	    			    		userRequest.setUserAddress(null);
	    						logger.error("already username or phone number or email exisit");
	    						// allProduct.setProductData(null);
	    						return userRequest;

	    					}
	    				}
	    	
	    	
	    	System.out.println(shopList.size());
	    	
	    	
	    	userRequest.setShop_ID(Shop + (shopList.size()) );
	    	
	    	//Add new Shopkeeper Details
	    	if(userDAO.addShop(userRequest)) {
	    		
	    		user.setShop_ID(Shop + (shopList.size()));
	    		userRequest.setStatus_code("200");
	    		userRequest.setStatus_message("Successfully Add ShopKeeper");
	    		return userRequest;
	    	}
	    	else {
	    		userRequest.setStatus_code("400");
	    		userRequest.setStatus_message("Shopkeeper Empty");
	    		return null;
	    	}
	    	
	    }
	    else {
	    	logger.debug(" Adding User into database ");
	    	
	    	/**
	    	 * Add user 
	    	 * **/
	    	
	    	//check how many uique userID or get list of userID from database
	    	List<UserAddress> shopList = userDAO.listOfShop(userRequest);
	    	int size =  shopList.size();
	        System.out.println(shopList.size());
	        
	      
	        
	        
	        
	        if (shopList.get(size-1).getUser_ID() == null || shopList.size() == 0) {
	        	System.out.println("1st user add");
	        	
	        	user.setUser_ID(User + (size-1));
	        	
	        	if(userDAO.addShop(userRequest)) {
	        		user.setUser_ID(User + (size-1));
    	    		userRequest.setStatus_code("200");
    	    		userRequest.setStatus_message("Successfully Add ShopKeeper");
    	    		return userRequest;
	        		
	        	}else {
		    		userRequest.setStatus_code("400");
		    		userRequest.setStatus_message("Shopkeeper Empty");
		    		userRequest.setUserDetails(null);
		    		userRequest.setUserAddress(null);
		    		return null;
		    	}
	        }
	        else {
	        	System.out.println("2nd or 3rd user add");
	        	
	        	user.setUser_ID(User + (size-1));
                if(userDAO.addShop(userRequest)) {
                	user.setUser_ID(User + (size-1));
    	    		userRequest.setStatus_code("200");
    	    		userRequest.setStatus_message("Successfully Add ShopKeeper");
    	    		return userRequest;
	        		
	        	}
                else {
    	    		userRequest.setStatus_code("400");
    	    		userRequest.setStatus_message("Shopkeeper Empty");
    	    		userRequest.setUserDetails(null);
		    		userRequest.setUserAddress(null);
    	    		return null;
    	    	}
	        }
	    	
	    }

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
	
	
	/**
	 * update user and shopkeeper  
	 * **/

	@RequestMapping(value ="/update/details", method = RequestMethod.POST)
	public @ResponseBody UpdateUserShopResponse updateUserDetails(@RequestBody UpdateUserShopRequest updateRequest) {
		
		logger.debug("Entering updateUserAndShopkeeperDetails() in user conroller ");
		try {
			//UpdateUserShopResponse updateUser = null;
			//User user = updateRequest.getUserDetails();
		//	Address address = updateRequest.getUserAddress();
			
			UpdateUserShopResponse updateUser = userDAO.updateUserShop(updateRequest);
			
			System.out.println(updateUser);
			
			if (updateUser != null) {
			
			updateUser.setStatus_code("200");
			updateUser.setStatus_message("successfully update user");
			return updateUser;
			}else {
				updateUser.setStatus_code("400");
				updateUser.setStatus_message("shopkeeper or User are not found");
				return updateUser;
			}
			
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
