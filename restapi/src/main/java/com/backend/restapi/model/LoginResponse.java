/**
 * 
 */
package com.backend.restapi.model;

import com.backend.restapi.common.SuccResponse;
import com.backend.restapi.dto.User;

/**
 * @author Sk Saddam Hosan
 *
 */
public class LoginResponse extends SuccResponse{
	
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
