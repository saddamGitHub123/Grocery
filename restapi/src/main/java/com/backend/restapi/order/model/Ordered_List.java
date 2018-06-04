package com.backend.restapi.order.model;

import java.sql.Timestamp;
import java.util.List;

import com.backend.restapi.order.dto.Order;
import com.backend.restapi.user.dto.Address;


public class Ordered_List {
	
	private String User_ID;
	Address address;
	List<Order> order ;
	
	java.sql.Timestamp TimeStamp;
	
	private String Order_ID;
	
	private double Total_Amount;
	

	/*public Ordered_List(String user_ID, Address address, List<Order> order, Timestamp timeStamp, String order_ID) {
		super();
		User_ID = user_ID;
		this.address = address;
		this.order = order;
		TimeStamp = timeStamp;
		Order_ID = order_ID;
	}*/
	
	
	public Ordered_List(String user_ID, Address address, List<Order> order, Timestamp timeStamp, String order_ID,
			double total_Amount) {
		super();
		User_ID = user_ID;
		this.address = address;
		this.order = order;
		TimeStamp = timeStamp;
		Order_ID = order_ID;
		Total_Amount = total_Amount;
	}


	@Override
	public String toString() {
		return "Ordered_List [User_ID=" + User_ID + ", address=" + address + ", order=" + order + ", TimeStamp="
				+ TimeStamp + ", Order_ID=" + Order_ID + ", Total_Amount=" + Total_Amount + "]";
	}
	
	
	public double getTotal_Amount() {
		return Total_Amount;
	}
	public void setTotal_Amount(double total_Amount) {
		Total_Amount = total_Amount;
	}
	public String getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
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
