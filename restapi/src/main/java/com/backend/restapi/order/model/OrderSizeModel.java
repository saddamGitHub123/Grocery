package com.backend.restapi.order.model;

import java.sql.Timestamp;

public class OrderSizeModel {
	
	private int listSize;
	
     private String Order_ID;
    
     private double Total_Amount;
    
     java.sql.Timestamp TimeStamp;
     
 

	public OrderSizeModel(int listSize, String order_ID, double total_Amount, Timestamp timeStamp) {
		super();
		this.listSize = listSize;
		Order_ID = order_ID;
		Total_Amount = total_Amount;
		TimeStamp = timeStamp;
	}
	
	

	@Override
	public String toString() {
		return "OrderSizeModel [listSize=" + listSize + ", Order_ID=" + Order_ID + ", Total_Amount=" + Total_Amount
				+ ", TimeStamp=" + TimeStamp + "]";
	}



	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public String getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}

	public double getTotal_Amount() {
		return Total_Amount;
	}

	public void setTotal_Amount(double total_Amount) {
		Total_Amount = total_Amount;
	}

	public java.sql.Timestamp getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		TimeStamp = timeStamp;
	}
     
     
	

}
