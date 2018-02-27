package com.backend.restapi.product.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UniqueProduct {
	
	    private int ID;	
		
		private String Product_ID;
		private String Shop_ID;
		private String Product_Name;
		 //private int Product_Price;
		 //private String Product_Image;
		 
		 private byte[] Product_Image;
		 private String Product_Category;
		 private String Product_Type;
		 private boolean Availability ;
		 private List<String> Product_Price;
		 private List<String> Unit;
		 private List<String> Stock;
		 
		 
		 
		 
		 
		 public UniqueProduct() {
		 }
		 

		

		 
		public UniqueProduct(String product_ID, String shop_ID, String product_Name, byte[] product_Image,
				String product_Category, String product_Type, boolean availability, List<String> product_Price,
				List<String> unit, List<String> stock) {
			super();
			Product_ID = product_ID;
			Shop_ID = shop_ID;
			Product_Name = product_Name;
			Product_Image = product_Image;
			Product_Category = product_Category;
			Product_Type = product_Type;
			Availability = availability;
			Product_Price = product_Price;
			Unit = unit;
			Stock = stock;
		}





		public UniqueProduct(String product_ID, String shop_ID, String product_Name, byte[] product_Image,
				String product_Category, String product_Type,List<String> product_Price,
				List<String> unit, List<String> stock) {
			super();
			Product_ID = product_ID;
			Shop_ID = shop_ID;
			Product_Name = product_Name;
			Product_Image = product_Image;
			Product_Category = product_Category;
			Product_Type = product_Type;
			//Availability = availability;
			Product_Price = product_Price;
			Unit = unit;
			Stock = stock;
		}




		public List<String> getStock() {
			return Stock;
		}




		public void setStock(List<String> stock) {
			Stock = stock;
		}




		@Override
		public String toString() {
			return "UniqueProduct [ID=" + ID + ", Product_ID=" + Product_ID + ", Shop_ID=" + Shop_ID + ", Product_Name="
					+ Product_Name + ", Product_Image=" + Product_Image + ", Product_Category=" + Product_Category
					+ ", Product_Type=" + Product_Type + ", Availability=" + Availability + ", Product_Price="
					+ Product_Price + ", Unit=" + Unit + ", Stock=" + Stock + "]";
		}
		
		
		public List<String> getUnit() {
			return Unit;
		}


		public void setUnit(List<String> unit) {
			Unit = unit;
		}

        @JsonIgnore
		public int getID() {
			return ID;
		}
        @JsonIgnore
		public void setID(int iD) {
			ID = iD;
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
		public String getProduct_Name() {
			return Product_Name;
		}
		public void setProduct_Name(String product_Name) {
			Product_Name = product_Name;
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
		public List<String> getProduct_Price() {
			return Product_Price;
		}
		public void setProduct_Price(List<String> product_Price) {
			Product_Price = product_Price;
		}
		 
		 

}
