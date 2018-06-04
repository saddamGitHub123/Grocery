package com.backend.restapi.product.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product_Model implements Serializable{

	 
		private static final long serialVersionUID = 1L;
		
	    private int ID;	
		
		//private String code;
		@JsonIgnore
		private String Product_Name;
		
		private String Product_ID;
		private String Shop_ID;
		@JsonIgnore
		// private String Product_Price;

		private List<String> Product_Price;

		private List<String> Unit;

		private List<String> Stock_Value;
		
		/*@JsonIgnore
		 private String Product_Image;*/
		
		private byte[] Product_Image;
		@JsonIgnore
		 private String Product_Category;
		@JsonIgnore
		 private String Product_Type;
		@JsonIgnore
		 private boolean Availability  ;
		
	/*	public String toString() {
			return "Product_Model [ID=" + ID + ", code=" + code + ", Product_Name=" + Product_Name + ", Product_ID="
					+ Product_ID + ", Shop_ID=" + Shop_ID + ", Product_Price=" + Product_Price + ", Unit=" + Unit
					+ ", Stock_Value=" + Stock_Value + ", Product_Image=" + Product_Image + ", Product_Category="
					+ Product_Category + ", Product_Type=" + Product_Type + ", Availability=" + Availability + "]";
		}
		public Product_Model(int iD, String code, String product_Name, String product_ID, String shop_ID,
				List<String> product_Price, List<String> unit, List<String> stock_Value, String product_Image,
				String product_Category, String product_Type, boolean availability) {
			super();
			ID = iD;
			this.code = code;
			Product_Name = product_Name;
			Product_ID = product_ID;
			Shop_ID = shop_ID;
			Product_Price = product_Price;
			Unit = unit;
			Stock_Value = stock_Value;
			Product_Image = product_Image;
			Product_Category = product_Category;
			Product_Type = product_Type;
			Availability = availability;
		}*/
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		/*public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}*/
		public String getProduct_Name() {
			return Product_Name;
		}
		public void setProduct_Name(String product_Name) {
			Product_Name = product_Name;
		}
		public String getProduct_ID() {
			return Product_ID;
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
		public List<String> getProduct_Price() {
			return Product_Price;
		}
		public void setProduct_Price(List<String> product_Price) {
			Product_Price = product_Price;
		}
		public List<String> getUnit() {
			return Unit;
		}
		public void setUnit(List<String> unit) {
			Unit = unit;
		}
		public List<String> getStock_Value() {
			return Stock_Value;
		}
		public void setStock_Value(List<String> stock_Value) {
			Stock_Value = stock_Value;
		}
		
		public byte[] getProduct_Image() {
			return Product_Image;
		}
		public void setProduct_Image(byte[] product_Image) {
			Product_Image = product_Image;
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
		 
	
		
		
	
}
