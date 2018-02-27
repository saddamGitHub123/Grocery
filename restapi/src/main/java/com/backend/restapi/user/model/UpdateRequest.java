package com.backend.restapi.user.model;


public class UpdateRequest {
	
	private String Shop_ID;
	private String User_ID;
	
    User_Data user_Data;

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public User_Data getUser_Data() {
		return user_Data;
	}

	public void setUser_Data(User_Data user_Data) {
		this.user_Data = user_Data;
	}
    
    

}
