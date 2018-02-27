package com.backend.restapi.product.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_prices")
public class Price implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private int ID;	
	
	private String Product_ID;
	private String Shop_ID;
	
	private String Price;
	private String Qty_Price;
    
	private String Stock;

	
	public Price() {}

	

	
	
	
	
	

	public Price(String product_ID, String shop_ID, String price, String stock) {
		super();
		Product_ID = product_ID;
		Shop_ID = shop_ID;
		Price = price;
		Stock = stock;
	}









	public Price(String product_ID, String shop_ID, String price, String qty_Price, String stock) {
		super();
		Product_ID = product_ID;
		Shop_ID = shop_ID;
		Price = price;
		Qty_Price = qty_Price;
		Stock = stock;
	}









	@Override
	public String toString() {
		return "Price [ID=" + ID + ", Product_ID=" + Product_ID + ", Shop_ID=" + Shop_ID + ", Price=" + Price
				+ ", Qty_Price=" + Qty_Price + ", Stock=" + Stock + "]";
	}









	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}

	public int getID() {
		return ID;
	}
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
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getQty_Price() {
		return Qty_Price;
	}
	public void setQty_Price(String qty_Price) {
		Qty_Price = qty_Price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
