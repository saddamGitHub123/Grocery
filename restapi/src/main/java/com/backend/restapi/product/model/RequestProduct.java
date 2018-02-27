package com.backend.restapi.product.model;

import com.backend.restapi.common.SuccResponse;



public class RequestProduct extends SuccResponse{


	private String Request_Type;
	private String Shop_ID;
	
	
	
	Product_Model productDataValue;

	Product_Data productData;
	

	/*public Product_Model getProductData() {
		return productData;
	}


	public void setProductData(Product_Model productData) {
		this.productData = productData;
	}*/


	
	
	
	public String getShop_ID() {
		return Shop_ID;
	}





	public Product_Model getProductDataValue() {
		return productDataValue;
	}





	public void setProductDataValue(Product_Model productDataValue) {
		this.productDataValue = productDataValue;
	}





	public Product_Data getProductData() {
		return productData;
	}





	public void setProductData(Product_Data productData) {
		this.productData = productData;
	}





	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}


	/*public Product_Data getProductData() {
		return productData;
	}

	public void setProductData(Product_Data productData) {
		this.productData = productData;
	}*/

	public String getRequest_Type() {
		return Request_Type;
	}

	public void setRequest_Type(String request_Type) {
		Request_Type = request_Type;
	}


	public RequestProduct() {
		super();
		// TODO Auto-generated constructor stub
	}





	
	
	

}
