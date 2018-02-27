/**
 * 
 */
package com.backend.restapi.user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

/**
 * @author Sk Saddam Hosan
 *
 */


@Entity
@Table(name = "usershop_details")
@SecondaryTables({
    @SecondaryTable(name="address", pkJoinColumns={@PrimaryKeyJoinColumn(name = "ID")})
    

})
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	//private String Shop_ID;
	
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
	
	
	@Column(name="Shop_ID", table="address")
    private String ShopID;
	
	@Column(name="User_ID", table="address")
	private String UserID;
	@Column(table="address")
	private String House_No;
	@Column(table="address")
	private String Locality;
	@Column(table="address")
	private String Landmark;
	@Column(table="address")
	private String PinCode;
	@Column(table="address")
	private String Area;
	@Column(table="address")
	private String City;
	
	@Column(table="address")
	private boolean Address_Active;
	
	
	public UserAddress() {}
	
	
	

	//Adding Shopkeeper

	public UserAddress(String shop_ID, String user_ID, String name, String user_Name, String user_Password,
			String contact, String email, boolean is_Active, boolean shop_Count, String shopID,String  userID,
			String house_No, String locality, String landmark, String pinCode, String area, String city,
			boolean address_Active) {
		super();
		Shop_ID = shop_ID;
		User_ID = user_ID;
		Name = name;
		User_Name = user_Name;
		User_Password = user_Password;
		Contact = contact;
		Email = email;
		Is_Active = is_Active;
		Shop_Count = shop_Count;
		ShopID = shopID;
		UserID = userID;
		House_No = house_No;
		Locality = locality;
		Landmark = landmark;
		PinCode = pinCode;
		Area = area;
		City = city;
		Address_Active = address_Active;
	}





	/**
	 * 
	 */
	public UserAddress(String shop_ID, String user_ID, String name, String user_Name, String user_Password,
			String contact, String email, boolean is_Active, boolean shop_Count, String shopID, String userID,
			boolean address_Active) {
		super();
		Shop_ID = shop_ID;
		User_ID = user_ID;
		Name = name;
		User_Name = user_Name;
		User_Password = user_Password;
		Contact = contact;
		Email = email;
		Is_Active = is_Active;
		Shop_Count = shop_Count;
		ShopID = shopID;
		UserID = userID;
		Address_Active = address_Active;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAddress [ID=" + ID + ", Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", Name=" + Name
				+ ", User_Name=" + User_Name + ", User_Password=" + User_Password + ", Contact=" + Contact + ", Email="
				+ Email + ", Is_Active=" + Is_Active + ", Shop_Count=" + Shop_Count + ", ShopID=" + ShopID + ", UserID="
				+ UserID + ", House_No=" + House_No + ", Locality=" + Locality + ", Landmark=" + Landmark + ", PinCode="
				+ PinCode + ", Area=" + Area + ", City=" + City + ", Address_Active=" + Address_Active + "]";
	}






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
	 * @return the house_No
	 */
	public String getHouse_No() {
		return House_No;
	}
	/**
	 * @param house_No the house_No to set
	 */
	public void setHouse_No(String house_No) {
		House_No = house_No;
	}
	/**
	 * @return the locality
	 */
	public String getLocality() {
		return Locality;
	}
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		Locality = locality;
	}
	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return Landmark;
	}
	/**
	 * @param landmark the landmark to set
	 */
	public void setLandmark(String landmark) {
		Landmark = landmark;
	}
	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return PinCode;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		PinCode = pinCode;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return Area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		Area = area;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}




	/**
	 * @return the address_Active
	 */
	public boolean isAddress_Active() {
		return Address_Active;
	}




	/**
	 * @param address_Active the address_Active to set
	 */
	public void setAddress_Active(boolean address_Active) {
		Address_Active = address_Active;
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
	 * @return the shopID
	 */
	public String getShopID() {
		return ShopID;
	}




	/**
	 * @param shopID the shopID to set
	 */
	public void setShopID(String shopID) {
		ShopID = shopID;
	}




	/**
	 * @return the userID
	 */
	public String getUserID() {
		return UserID;
	}




	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		UserID = userID;
	}
	
	
	

}
