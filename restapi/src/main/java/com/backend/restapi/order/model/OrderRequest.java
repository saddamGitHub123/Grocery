package com.backend.restapi.order.model;

import java.util.List;

import com.backend.restapi.order.dto.Order;



public class OrderRequest {
	
	
	private String Shop_ID;
    private String User_ID;
    private int Page_Value;
    private boolean Dispatch;
    java.sql.Timestamp TimeStamp;
    
    List<Order> orderList;

    
    
    
	public boolean isDispatch() {
		return Dispatch;
	}

	public void setDispatch(boolean dispatch) {
		Dispatch = dispatch;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}
	
	public int getPage_Value() {
		return Page_Value;
	}

	public void setPage_Value(int page_Value) {
		Page_Value = page_Value;
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
    
    
    

}
