package com.backend.restapi.order.model;

public class OrderSizeRequest {
	
	private String Shop_ID;
    private String User_ID;
    private String Order_ID;
    
    
    
    
    
	@Override
	public String toString() {
		return "OrderSizeRequest [Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", Order_ID=" + Order_ID + "]";
	}
	
	
	
	public String getOrder_ID() {
		return Order_ID;
	}



	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}



	public String getShop_ID() {
		return Shop_ID;
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
    
    

}
