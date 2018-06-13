/**
 * 
 */
package com.backend.restapi.common;

/**
 * @author Sk Saddam Hosan

 */

/**
 * This is the JSON Response class  using JSON view 
 * we can used interface inheritance also like - 
 * */
public class JsonViewModel {
	
	public interface SuccResponseModel{}
	
	// This is for the login  
	public  interface LoginView extends SuccResponseModel{}
	
	// This is for the OrderController
	
	public interface OrderView extends SuccResponseModel{}
	
	
	// This is for the ProductController
	
	public interface ProductView extends SuccResponseModel{}
	
	// This is for UserController
	
	public interface UserView extends SuccResponseModel{}
	
	// This is for UserAddView in UserContrller
	
	public interface UserAddView extends SuccResponseModel{}
	
	// UpdateView in UserController
	
	public interface UpdateUserView extends SuccResponseModel{}
	
	// This is for ShpKeeper Add view in UserController
	
	public interface ShopKeeperAddView extends SuccResponseModel{}
	
	// This is for update shopkeeper view in UserController
	
	public interface updateShopkeereView extends SuccResponseModel{}
	

	
	
	
	

}
