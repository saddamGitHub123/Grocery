/**
 * 
 */
package com.backend.restapi.user.model;

import com.backend.restapi.common.JsonViewModel;
import com.backend.restapi.common.SuccResponse;
import com.backend.restapi.user.dto.User;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * @author Sk Saddam Hosan
 *
 */
public class LoginResponse extends SuccResponse{
	
	//@JsonView(JsonViewModel.LoginView.class) 
	public User Data;

	/**
	 * @return the data
	 */
	/*public User getData() {
		return Data;
	}*/

	/**
	 * @param data the data to set
	 */
	public void setData(User data) {
		Data = data;
	}


	
	
	

}
