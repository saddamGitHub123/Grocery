/**
 * 
 */
package com.backend.restapi.dao;

import com.backend.restapi.dto.User;

/**
 * @author Sk Saddam Hosan
 *
 */
public interface LoginDAO {
	
	/**
	 * login validation 
	 * **/
	
	User checkLogin(User loginuser);

}
