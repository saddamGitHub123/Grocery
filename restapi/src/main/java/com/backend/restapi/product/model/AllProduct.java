package com.backend.restapi.product.model;

import java.util.List;

import com.backend.restapi.common.SuccResponse;

public class AllProduct extends SuccResponse{


	//price are not working
	//List<Product> product;
	
	//price is working
	List<UniqueProduct> uniqueProduct;
	
	public AllProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AllProduct(List<UniqueProduct> uniqueProduct) {
		super();
		this.uniqueProduct = uniqueProduct;
	}


	public List<UniqueProduct> getUniqueProduct() {
		return uniqueProduct;
	}


	public void setUniqueProduct(List<UniqueProduct> uniqueProduct) {
		this.uniqueProduct = uniqueProduct;
	}





	
	
	/*public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}*/
	
	
	
	
	
}
