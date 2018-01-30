/**
 * 
 */
package com.backend.restapi.model;

import com.backend.restapi.dto.Address;
import com.backend.restapi.dto.User;

/**
 * @author Sk Saddam Hosan
 *
 */
public class UserRequest{
	
	private String Shop_ID;
	
	User userDetails;
	Address userAddress;

	public UserRequest() {}
	
	/**
	 * @param shop_ID
	 * @param userDetails
	 * @param userAddress
	 */
	public UserRequest(String shop_ID, User userDetails, Address userAddress) {
		super();
		Shop_ID = shop_ID;
		this.userDetails = userDetails;
		this.userAddress = userAddress;
	}
	
	public String getShop_ID() {
		return Shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}
	public User getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	public Address getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}
	
	
	

}
