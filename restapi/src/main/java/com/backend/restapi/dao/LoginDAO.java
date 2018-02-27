/**
 * 
 */
package com.backend.restapi.dao;

import com.backend.restapi.user.dto.User;

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
