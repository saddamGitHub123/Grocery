package com.backend.restapi.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grocery")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@Override
	public String toString() {
		return "User [ID=" + ID + ", Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", Name=" + Name + ", User_Name="
				+ User_Name + ", User_Password=" + User_Password + ", Contact=" + Contact + ", Email=" + Email
				+ ", Is_Active=" + Is_Active + "]";
	}

	
	
	public User() {}
	
	/**
	 * @param iD
	 * @param shop_ID
	 * @param user_ID
	 * @param name
	 * @param user_Name
	 * @param user_Password
	 * @param contact
	 * @param email
	 * @param is_Active
	 */
	public User(int iD, String shop_ID, String user_ID, String name, String user_Name, String user_Password,
			String contact, String email, boolean is_Active) {
		super();
		ID = iD;
		Shop_ID = shop_ID;
		User_ID = user_ID;
		Name = name;
		User_Name = user_Name;
		User_Password = user_Password;
		Contact = contact;
		Email = email;
		Is_Active = is_Active;
	}





	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Password() {
		return User_Password;
	}

	public void setUser_Password(String user_Password) {
		User_Password = user_Password;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isIs_Active() {
		return Is_Active;
	}

	public void setIs_Active(boolean is_Active) {
		Is_Active = is_Active;
	}
	
	
	

}
