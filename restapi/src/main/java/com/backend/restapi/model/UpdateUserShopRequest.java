/**
 * 
 */
package com.backend.restapi.model;

import com.backend.restapi.common.SuccResponse;


/**
 * @author Sk Saddam Hosan
 *
 */
public class UpdateUserShopRequest {
	
	private String Shop_ID;
	private String User_ID;
	
	
	User_Data userData;

	/**
	 * @return the shop_ID
	 */
	public String getShop_ID() {
		return Shop_ID;
	}

	/**
	 * @param shop_ID the shop_ID to set
	 */
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	/**
	 * @return the user_ID
	 */
	public String getUser_ID() {
		return User_ID;
	}

	/**
	 * @param user_ID the user_ID to set
	 */
	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	/**
	 * @return the userData
	 */
	public User_Data getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public void setUserData(User_Data userData) {
		this.userData = userData;
	}

}
