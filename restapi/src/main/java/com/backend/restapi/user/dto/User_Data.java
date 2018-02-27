package com.backend.restapi.user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usershop_details")
public class User_Data {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	private String Shop_ID;
	private String User_ID;
	private String Name;
	private String User_Name;
	private String User_Password;
	@Column(name="phn_number")
	private String Contact;
	private String Email;
	private boolean Is_Active;
	private boolean Shop_Count;
	   
	  
	   @Transient
	   Address userAddress;


	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}


	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}


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
	 * @return the is_Active
	 */
	public boolean isIs_Active() {
		return Is_Active;
	}


	/**
	 * @param is_Active the is_Active to set
	 */
	public void setIs_Active(boolean is_Active) {
		Is_Active = is_Active;
	}


	/**
	 * @return the shop_Count
	 */
	public boolean isShop_Count() {
		return Shop_Count;
	}


	/**
	 * @param shop_Count the shop_Count to set
	 */
	public void setShop_Count(boolean shop_Count) {
		Shop_Count = shop_Count;
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
