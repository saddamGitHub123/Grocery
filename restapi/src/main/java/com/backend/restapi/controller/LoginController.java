/**
 * 
 */
package com.backend.restapi.controller;

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

import com.backend.restapi.common.ApiErrors;
import com.backend.restapi.common.JsonResponse;
import com.backend.restapi.dao.LoginDAO;
import com.backend.restapi.user.dto.User;
import com.backend.restapi.user.model.LoginResponse;

/**
 * @author Sk Saddam Hosan
 *
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public LoginDAO loginDAO;
	
	@RequestMapping(value = "/user/shop", method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginUser(@RequestBody User loginuser) {

		logger.info("User & Shopkeeper Entered loginUser() in LoginController  - Post username and password for USER");
		LoginResponse loginResponse = new LoginResponse() ;
		
		try {
			// Create main class Object			
			User userDetails = loginDAO.checkLogin(loginuser);
			if (userDetails == null) {
				loginResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				loginResponse.setStatus_message(ApiErrors.ERROR__LOGIN);
				logger.error("User name and password not found");
				
				return loginResponse;
			}
			else {

			    loginResponse.setStatus_code(JsonResponse.CODE__OK);
				loginResponse.setStatus_message(ApiErrors.ERROR__LOGIN);
				logger.debug("User name and password  found");
				//logger.D("User name and password  found");
				loginResponse.setData(userDetails);
				return loginResponse;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
