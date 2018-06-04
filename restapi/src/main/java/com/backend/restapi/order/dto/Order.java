 package com.backend.restapi.order.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orderlist")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID; 
	//private String code;
    private String Shop_ID;
    private String User_ID;
    private String Product_ID;
    private String Product_Name;
    private String Units;
    private String Qty; 
    private String Order_ID;
    
    private double Total_Amount;
    
    private String Price;
    
    private boolean Dispatch;
    
    private boolean Order_Active;
    
   /* @Column(name="TimeStamp")
    java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());*/
    
    @Column(name="TimeStamp")
    java.sql.Timestamp currentTimestamp;
    
    public Order() {
		// this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
	 }

	@Override
	public String toString() {
		return "Order [ID=" + ID + ", Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", Product_ID=" + Product_ID
				+ ", Product_Name=" + Product_Name + ", Units=" + Units + ", Qty=" + Qty + ", Order_ID=" + Order_ID
				+ ", Total_Amount=" + Total_Amount + ", Price=" + Price + ", Dispatch=" + Dispatch + ", Order_Active="
				+ Order_Active + ", currentTimestamp=" + currentTimestamp + "]";
	}

	
	
    @JsonIgnore
	public boolean isDispatch() {
		return Dispatch;
	}

	public void setDispatch(boolean dispatch) {
		Dispatch = dispatch;
	}

	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
		Price = price;
	}


	int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	/*@JsonIgnore
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}*/

	@JsonIgnore
	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	@JsonIgnore
	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}

	public String getProduct_Name() {
		return Product_Name;
	}

	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public String getQty() {
		return Qty;
	}

	public void setQty(String qty) {
		Qty = qty;
	}

	@JsonIgnore
	public java.sql.Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(java.sql.Timestamp currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

	@JsonIgnore
	public String getOrder_ID() {
		return Order_ID;
	}


	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
    
	@JsonIgnore
	public double getTotal_Amount() {
		return Total_Amount;
	}
	
	public void setTotal_Amount(double total_Amount) {
		Total_Amount = total_Amount;
	}

	public boolean isOrder_Active() {
		return Order_Active;
	}

	public void setOrder_Active(boolean order_Active) {
		Order_Active = order_Active;
	}

    
    
 
}
