package com.backend.restapi.order.model;

public class DispatchRequest {
	
	
	 private String Shop_ID;
	 private String Order_ID;
	 
	 private  boolean Dispatch;

	 private String User_ID;
	 
	@Override
	public String toString() {
		return "DispatchRequest [Shop_ID=" + Shop_ID + ", Order_ID=" + Order_ID + ", Dispatch=" + Dispatch
				+ ", User_ID=" + User_ID + "]";
	}
	
	
	
	
	public String getUser_ID() {
		return User_ID;
	}




	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}




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
	public String getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
	 
	 

}
