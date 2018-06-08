/**
 * 
 */
package com.backend.restapi.common;

/**
 * @author Sk Saddam Hosan

 */
public interface ApiUrl {
	
	// This is FCM - firebase project server key
	// In FCM fireabase project u have to enable the cloud messaging
	public static final String FCM__SERVER_KEY = "AIzaSyAuS9vJADBUEWM_pAQcgPDGR_GcNWP2knw";
	
	//this is Firebase API URL 
	public static final String FCM__API_URL = "https://fcm.googleapis.com/fcm/send";
	
	//Order
	public static final String ORDER__STRING = "ORDER ";
	
	//User 
	
	public static final String SHOP__STRING = "Shop_";	
	public static final String USER__STRING = "User_";
	
	// Product
	
	public static final String PRODUCT__STRING = "Product_";	
}
