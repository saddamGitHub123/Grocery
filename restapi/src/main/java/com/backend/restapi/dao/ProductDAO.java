/**
 * 
 */
package com.backend.restapi.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;

import com.backend.restapi.product.dto.Product;
import com.backend.restapi.product.model.Product_Model;
import com.backend.restapi.product.model.UniqueProduct;

/**
 * @author Sk Saddam Hosan
 *
 */
public interface ProductDAO {
	
	
	/**
	 * List all product using shopId
	 * **/
	
	List<Product> listProductByShopId(String Shop_ID);
	
	
	/*
	 * Add Product in the database
	 * **/
	boolean addProduct(Product_Model product);
	
	
	
	  /**
     * Update product using shopid
     * **/
    Product_Model updateProduct(String shop_ID,Product_Model productData);
    
    
    
    /**
     *  Get all product list using shopId
     * **/
    	
    	List<UniqueProduct> productsByShopId(String Shop_ID);
    	
    	
    	/**
         * Get all unique productList
         * ****/
        
       List< UniqueProduct> uniqueProductList();
       
       
       
       /**
   	 * This is for push notification method 
   	 * **/
   	CompletableFuture<String> send (HttpEntity<String> entity);

}
