/**
 * 
 */
package com.backend.restapi.user.model;

import javax.persistence.Column;

import com.backend.restapi.user.dto.Address;

/**
 * @author Sk Saddam Hosan
 *
 */
public class User_Data {
	
	private String Shop_ID;
	private String User_ID;
	private String Name;
	private String User_Name;
	private String User_Password;
	private String Contact;
	private String Email;

	
	Address userAddress;

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
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the user_Name
	 */
	public String getUser_Name() {
		return User_Name;
	}

	/**
	 * @param user_Name the user_Name to set
	 */
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	/**
	 * @return the user_Password
	 */
	public String getUser_Password() {
		return User_Password;
	}

	/**
	 * @param user_Password the user_Password to set
	 */
	public void setUser_Password(String user_Password) {
		User_Password = user_Password;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return Contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		Contact = contact;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}


	/**
	 * @return the userAddress
	 */
	public Address getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	
}
