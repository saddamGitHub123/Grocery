package com.backend.restapi.user.model;

import java.util.List;

import com.backend.restapi.common.SuccResponse;
import com.backend.restapi.user.dto.User_Data;

public class UserDetailsResponse extends SuccResponse {

	
	List <User_Data> userData;

	public List<User_Data> getUserData() {
		return userData;
	}

	public void setUserData(List<User_Data> userData) {
		this.userData = userData;
	}
	
	
}
