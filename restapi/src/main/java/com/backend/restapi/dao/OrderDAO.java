package com.backend.restapi.dao;

import java.util.List;

import com.backend.restapi.order.dto.Order;
import com.backend.restapi.order.model.DispatchRequest;
import com.backend.restapi.order.model.OrderRequest;
import com.backend.restapi.order.model.OrderRequestAdd;
import com.backend.restapi.order.model.OrderSizeModel;
import com.backend.restapi.order.model.OrderSizeRequest;
import com.backend.restapi.order.model.Ordered_List;
import com.backend.restapi.user.dto.User;



/**
 * 
 * @author saddam
 *
 */
public interface OrderDAO {

	// Add the order list using shopID and userID

	boolean addOrder(OrderRequest orderRequest);
	
	
	// Add the order list using shopID and userID

		boolean addOrderAndOrderID(OrderRequestAdd orderRequest);

	// order list and user details by shopID
	List<Ordered_List> userOrderListByShopId(String Shop_ID,String User_ID,boolean Dispatch);
	
	
  // Order Dispatch method
	
	boolean orderDispatchOrderID(DispatchRequest dispatchRequest);
	
	
	
	//getting order size and orderid list 
	
	List<OrderSizeModel> orderSizeList(OrderSizeRequest orderSizeRequest);
	
	
	//orderList for mobile app using orderid
	
	List<Order> orderList(OrderSizeRequest orderSizeReques);
	
	
	//getting phone number for dispatch controller sms intrgation 
	
	List<User> userPhoneNumber(DispatchRequest dispatchRequest);
	


}
