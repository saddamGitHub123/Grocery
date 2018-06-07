package com.backend.restapi.product.dto;



import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Productlist")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;	
	
	//private String code;
	private String Product_Name;
	private String Product_ID;
	private String Shop_ID;
	// private int Product_Price;
	 //private String Product_Image;
	
	private byte[] Product_Image;
	 private String Product_Category;
	 private String Product_Type;
	 private boolean Availability ;
	 private boolean Product_Active;
	 
	 
	 public Product() {
		 //this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
	 }

	/**
	 * @param iD
	 * @param product_Name
	 * @param product_ID
	 * @param shop_ID
	 * @param product_Image
	 * @param product_Category
	 * @param product_Type
	 * @param availability
	 * @param product_Active
	 */
	public Product(int iD, String product_Name, String product_ID, String shop_ID, byte[] product_Image,
			String product_Category, String product_Type, boolean availability, boolean product_Active) {
		super();
		ID = iD;
		Product_Name = product_Name;
		Product_ID = product_ID;
		Shop_ID = shop_ID;
		Product_Image = product_Image;
		Product_Category = product_Category;
		Product_Type = product_Type;
		Availability = availability;
		Product_Active = product_Active;
	}







	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [ID=" + ID + ", Product_Name=" + Product_Name + ", Product_ID=" + Product_ID + ", Shop_ID="
				+ Shop_ID + ", Product_Image=" + Arrays.toString(Product_Image) + ", Product_Category="
				+ Product_Category + ", Product_Type=" + Product_Type + ", Availability=" + Availability
				+ ", Product_Active=" + Product_Active + "]";
	}






	/*	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}*/
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	/*public int getProduct_Price() {
		return Product_Price;
	}*/
	/*public void setProduct_Price(int product_Price) {
		Product_Price = product_Price;
	}*/

	
	
	
	
	public String getProduct_ID() {
		return Product_ID;
	}
	public byte[] getProduct_Image() {
		return Product_Image;
	}






	public void setProduct_Image(byte[] product_Image) {
		Product_Image = product_Image;
	}






	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}
	public String getShop_ID() {
		return Shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getProduct_Category() {
		return Product_Category;
	}
	public void setProduct_Category(String product_Category) {
		Product_Category = product_Category;
	}
	public String getProduct_Type() {
		return Product_Type;
	}
	public void setProduct_Type(String product_Type) {
		Product_Type = product_Type;
	}
	public boolean isAvailability() {
		return Availability;
	}
	public void setAvailability(boolean availability) {
		Availability = availability;
	}






	/**
	 * @return the product_Active
	 */
	public boolean isProduct_Active() {
		return Product_Active;
	}






	/**
	 * @param product_Active the product_Active to set
	 */
	public void setProduct_Active(boolean product_Active) {
		Product_Active = product_Active;
	}
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * we used for getting unique value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Product_Name == null) ? 0 : Product_Name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Product_Name == null) {
			if (other.Product_Name != null)
				return false;
		} else if (!Product_Name.equals(other.Product_Name))
			return false;
		return true;
	}
	 
	 
	 
	 
	
	

}
