package com.backend.restapi.order.model;

import java.util.List;
import java.util.UUID;

import com.backend.restapi.order.dto.Order;


public class OrderRequestAdd {
	

	private String Shop_ID;
    private String User_ID;
    
    java.sql.Timestamp TimeStamp;
    
    private String Order_ID;
    private double Total_Amount;
    
    public OrderRequestAdd() {
 		// this.Order_ID ="OID "+ UUID.randomUUID().toString().substring(26).toUpperCase();
    	//this.Order_ID ="OID"+ String.valueOf(sendOTP(8));
 	 }
    
    List<Order> orderList;

	//Generate automatic orderID
	/*static char[] sendOTP(int length){
		
		String  number= "0123456789";
		Random r = new Random();
		char[] otp = new char[length];

		 for(int i=0 ; i<length ;i++){

		    otp[i] = number.charAt(r.nextInt((number.length())));
		 } 
		 return otp;
		 }*/
    
    

	public String getShop_ID() {
		return Shop_ID;
	}

	public double getTotal_Amount() {
		return Total_Amount;
	}

	public void setTotal_Amount(double total_Amount) {
		Total_Amount = total_Amount;
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

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public java.sql.Timestamp getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
    
    
    

}
