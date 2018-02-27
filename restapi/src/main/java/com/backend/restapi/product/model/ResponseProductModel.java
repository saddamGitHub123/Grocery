package com.backend.restapi.product.model;

import com.backend.restapi.common.SuccResponse;

public class ResponseProductModel extends SuccResponse {
	
	private String Request_Type;
	private String Shop_ID;

	Product_Model productData;

	/*@Override
	public String toString() {
		return "RequestProductModel [Request_Type=" + Request_Type + ", Shop_ID=" + Shop_ID + ", productData="
				+ productData + "]";
	}

	public ResponseProductModel(String request_Type, String shop_ID, Product_Model productData) {
		super();
		Request_Type = request_Type;
		Shop_ID = shop_ID;
		this.productData = productData;
	}*/

	public String getRequest_Type() {
		return Request_Type;
	}

	public void setRequest_Type(String request_Type) {
		Request_Type = request_Type;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public Product_Model getProductData() {
		return productData;
	}

	public void setProductData(Product_Model productData) {
		this.productData = productData;
	}
	
	

}
