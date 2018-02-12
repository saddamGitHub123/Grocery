package com.backend.restapi.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="address")
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String Shop_ID;
	private  String User_ID;
	private String House_No;
	private String Locality;
	private String Landmark;
	private String PinCode;
	private String Area;
	private String City;
	private boolean Address_Active;
	
	@Override
	public String toString() {
		return "Address [ID=" + ID + ", Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", House_No=" + House_No
				+ ", Locality=" + Locality + ", Landmark=" + Landmark + ", PinCode=" + PinCode + ", Area=" + Area
				+ ", City=" + City + ", Is_Active=" + Address_Active + "]";
	}
	
	public Address() {
		
	}
	
	
	/**
	 * @param iD
	 * @param shop_ID
	 * @param user_ID
	 * @param house_No
	 * @param locality
	 * @param landmark
	 * @param pinCode
	 * @param area
	 * @param city
	 * @param is_Active
	 */
	public Address(int iD, String shop_ID, String user_ID, String house_No, String locality, String landmark,
			String pinCode, String area, String city, boolean address_Active) {
		super();
		ID = iD;
		Shop_ID = shop_ID;
		User_ID = user_ID;
		House_No = house_No;
		Locality = locality;
		Landmark = landmark;
		PinCode = pinCode;
		Area = area;
		City = city;
		Address_Active = address_Active;
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

	public String getHouse_No() {
		return House_No;
	}

	public void setHouse_No(String house_No) {
		House_No = house_No;
	}

	public String getLocality() {
		return Locality;
	}

	public void setLocality(String locality) {
		Locality = locality;
	}

	public String getLandmark() {
		return Landmark;
	}

	public void setLandmark(String landmark) {
		Landmark = landmark;
	}

	public String getPinCode() {
		return PinCode;
	}

	public void setPinCode(String pinCode) {
		PinCode = pinCode;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public boolean isAddress_Active() {
		return Address_Active;
	}

	public void setIs_Active(boolean address_Active) {
		Address_Active = address_Active;
	}
	
	

}
