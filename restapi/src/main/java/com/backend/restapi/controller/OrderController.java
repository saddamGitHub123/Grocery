package com.backend.restapi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.restapi.common.ApiErrors;
import com.backend.restapi.common.ApiUrl;
import com.backend.restapi.common.JsonResponse;
import com.backend.restapi.common.SuccResponse;
import com.backend.restapi.dao.OrderDAO;
import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.order.dto.Order;
import com.backend.restapi.order.model.DispatchRequest;
import com.backend.restapi.order.model.OrderListResponse;
import com.backend.restapi.order.model.OrderRequest;
import com.backend.restapi.order.model.OrderRequestAdd;
import com.backend.restapi.order.model.OrderSizeModel;
import com.backend.restapi.order.model.OrderSizeRequest;
import com.backend.restapi.order.model.OrderSizeResponse;
import com.backend.restapi.order.model.Ordered_List;
import com.backend.restapi.order.model.ShopkeeperOrderResponse;

/**
 * 
 * @author sk saddam hosan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	   public OrderDAO orderDAO;
	@Autowired
	public UserDAO userDAO;

	/**
	 * Add orderList using shopID and userID used by User
	 * **/
	
	@RequestMapping(value = "/addOrderList", method = RequestMethod.POST)
	public @ResponseBody SuccResponse addOrderListByShopID(@RequestBody OrderRequestAdd orderRequest) {

		logger.info("User & Shopkeeper Entered addOrderListByShopID() in OrderController  - Post all order list");
		SuccResponse response = new SuccResponse();
		
		try {

			// For getting list of the Order
			
			List<Order> orderList = orderDAO.listOfOrder();
			
			System.out.println(orderList.size());
			
			//call the add order method
			//if(orderDAO.addOrder(orderRequest)) {
			String Order_ID = ApiUrl.ORDER__STRING + orderList.size();
			
			 orderRequest.setOrder_ID(Order_ID);			
			String orderID = orderDAO.addOrderAndOrderID(orderRequest);
			if(!orderID.isEmpty()) {
			
			/*
			 * Add push notification using Shop_ID
			 * **/
				
				//String me
			
				userDAO.getDeviceID(orderRequest.getShop_ID(),ApiErrors.FCM_SUCCESS__ADD_ORDER + orderID);
				
			
			//response.setStatus_code(JsonResponse.CODE__OK);
			//response.setStatus_message(JsonResponse.CODE__SUCCESS);
			response.setStatus_code(JsonResponse.CODE__OK);
			response.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
			return response;
			
			//System.out.println(shopid+" "+userid+" "+order);
			}
			else {
				response.setStatus_code(JsonResponse.CODE__EMPTY);
				//response.setStatus_message("Something wrong!! addOrderAndOrderID() in orderDAO");
				response.setStatus_message(JsonResponse.CODE__ERROR);
				return response;
			}

		} catch (Exception e) {
			
			logger.error("addOrderListByShopID(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__EMPTY);
			response.setStatus_message(JsonResponse.CODE__EXCEPTION);
			return response;
		}
	}


	/**
	 * returning all the order details using shopID list of user and details 
	 * **/
	
	@RequestMapping(value = "/shopkeeper/user/details", method = RequestMethod.POST)
	public @ResponseBody ShopkeeperOrderResponse userOrderListByShopID(@RequestBody OrderRequest orderRequest) {

		logger.info("User & Shopkeeper Entered userOrderListByShopID() in OrderController  - Post only shopID");
		
		
		
		
		ShopkeeperOrderResponse response = null ;
		
		try {
			
			 String Shop_ID = orderRequest.getShop_ID();
		     String User_ID = orderRequest.getUser_ID();
		   ///  int Page_Value = orderRequest.getPage_Value();
		     boolean Dispatch = orderRequest.isDispatch();
			
			    
		     
		     List<Ordered_List>  orderList =  orderDAO.userOrderListByShopId(Shop_ID,User_ID,Dispatch);
		     
		     System.out.println("orderlist size"+orderList.size());
		     
		     if(orderList.size() == 0 ) {
		    	 response = new ShopkeeperOrderResponse(orderList);
		    	// response.setStatus_code(JsonResponse.CODE__EMPTY);
					//response.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
		    	 
		    	 response.setStatus_code(JsonResponse.CODE__EMPTY);
		    	 response.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
					return response;
		     }
		     //For paging
		     
		     int pageValue = orderRequest.getPage_Value();
		     System.out.println("page value :"+pageValue);
		     int low_index = 20*pageValue;
		     int higest_index = low_index+20;
		     
		     
		     if(higest_index < orderList.size()) {
		     List<Ordered_List>  orderListValue = orderList.subList(low_index, higest_index);
		     response = new ShopkeeperOrderResponse(orderListValue);
		     }
		     else
		     {   
		    	 
		    	 if(orderList.size() <= low_index) {
		    		 
		    		 //when list is empty 
		                   	 List<Ordered_List> myEmptyList = Collections.<Ordered_List>emptyList();
		    	             response = new ShopkeeperOrderResponse(myEmptyList);
		    	             response.setStatus_code(JsonResponse.CODE__EMPTY);
			                 response.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
			                 return response;
		                    }
		    	 //for last list of orderList from list 
		    	 List<Ordered_List>  orderListValue = orderList.subList(low_index, orderList.size());
			     response = new ShopkeeperOrderResponse(orderListValue); 
		     }
		     
		    
		     
		    // response = new ShopkeeperOrderResponse(orderList);
		     
		   
		     
		     response.setStatus_code(JsonResponse.CODE__OK);
		     response.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
		     response.setOrderSize(orderList.size());
		     return response;

		} catch (Exception e) {
			
			logger.error("userOrderListByShopID(): Error - " + e);
			//response.setStatus_code(JsonResponse.CODE__EMPTY);
			response.setStatus_code(JsonResponse.CODE__EMPTY);
			response.setStatus_message(JsonResponse.CODE__EXCEPTION);
			return response;
		}
		
	}

	
	/**
	 * 
	 * Stock value update using shopID using orderList and price table
	 * For dispatch order API
	 * */
	
	

	@RequestMapping(value = "/dispatch", method = RequestMethod.POST)
	public @ResponseBody SuccResponse orderDispatch(@RequestBody DispatchRequest dispatchRequest) {

		logger.info("Order Dispatch  Entered orderDispatch() in OrderController  - shopID,orderID");
		
		//orderDAO.
		SuccResponse response = new SuccResponse();
		
		/*try {
			logger.info("Entered forgottenPassword()  - Send password in your mobile");
			
			
			
             //getting phone number using userID and shopID
			
			List<User> userDeatails = orderDAO.userPhoneNumber(dispatchRequest);
			
			String  phoneNumber = userDeatails.get(0).getContact();
			
			String name = userDeatails.get(0).getName();
			
			//String phoneNumber = "+91"+phone;
			System.out.println(phoneNumber);
			
			
			// Find your Account Sid and Token at twilio.com/user/account
		   // public static final String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		   // public static final String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    //public static final String TWILIO_NUMBER = "+19295002280";
			
			
			 //this is kiora company credential 
			 String ACCOUNT_SID = "AC82908b6852b609b75dae53cfecf5d92c";
		    String AUTH_TOKEN = "9beaa9551c8669c6e977a3a4bfffd1c2";
		    String TWILIO_NUMBER = "+14844986253";
		    
		    String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		    String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    String TWILIO_NUMBER = "+19295002280";
			
		        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", "Your Order Successfully Dispatch "));
		        params.add(new BasicNameValuePair("To", phoneNumber)); //Add real number here
		       // params.add(new BasicNameValuePair("To", "+919740092365"));
		        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		        
		       // return 
		    
		}   catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	    }*/
		
		try {

      if(orderDAO.orderDispatchOrderID(dispatchRequest)) {
			
			
			
			response.setStatus_code(JsonResponse.CODE__OK);
			response.setStatus_message(JsonResponse.DISPATCH__SUCCESS);
			//response.setStatus_message(JsonResponse.DISPATCH__SUCCESS);
			return response;
			
			//System.out.println(shopid+" "+userid+" "+order);
          }
      
      return response;
			
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
			//	response.setStatus_code(JsonResponse.CODE__EMPTY);
				response.setStatus_code(JsonResponse.CODE__OK);
				response.setStatus_message(JsonResponse.CODE__EXCEPTION);
				return response;
			}	
		
	}
	
	
	
	
	
	/**
	 * 
	 * returing timestamp,orderid and total amount and orderlist size using
	 * shopid and userid 
	 * for mobile application
	 * */
	
	

	@RequestMapping(value = "/orderSize", method = RequestMethod.POST)
	public @ResponseBody OrderSizeResponse orderSizeStructure(@RequestBody OrderSizeRequest orderSizeRequest) {

		logger.info("Order Size orderSizeStructere() in OrderController  - shopID,userID");
		
		//orderDAO.
		OrderSizeResponse orderSizeResponse = null;
		
		try {
			
			
			List<OrderSizeModel> orderSize = orderDAO.orderSizeList(orderSizeRequest);
			System.out.println(orderSize);
			
			if(orderSize.size() == 0) {
				orderSizeResponse = new OrderSizeResponse(orderSize);
				//orderSizeResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				//orderSizeResponse.setStatus_message(JsonResponse.LIST__ERRORE);
				
				orderSizeResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderSizeResponse.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
				return orderSizeResponse;
				
			}
			
			orderSizeResponse = new OrderSizeResponse(orderSize);
			orderSizeResponse.setStatus_code(JsonResponse.CODE__OK);
			orderSizeResponse.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
			return orderSizeResponse;
	
		
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
				//orderSizeResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderSizeResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderSizeResponse.setStatus_message(JsonResponse.CODE__EXCEPTION);
				return orderSizeResponse;
			}
		
		
		
		
		
	}
	
	
	/**
	 * 
	 * returing orderlist using orderid and userid and shopid 
	 * for mobile app
	 * */
	
	

	@RequestMapping(value = "/orderId/orderList", method = RequestMethod.POST)
	public @ResponseBody OrderListResponse listOfOrder(@RequestBody OrderSizeRequest orderSizeRequest) {

		logger.info("Order List listOfOrder() in OrderController  - shopID,userID");
		
		//orderDAO.
		OrderListResponse orderListResponse = null;
		
		try {
			
			
			List<Order> orderSize = orderDAO. orderList(orderSizeRequest);
			System.out.println(orderSize);
			
			if(orderSize.size() == 0) {
				//orderSizeResponse = new OrderSizeResponse(orderSize);
				orderListResponse = new OrderListResponse(orderSize);
			//	orderListResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				//orderListResponse.setStatus_message(JsonResponse.LIST__ERRORE);
				
				orderListResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderListResponse.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
				return orderListResponse;
				
			}
			
			orderListResponse = new OrderListResponse(orderSize);
			orderListResponse.setStatus_code(JsonResponse.CODE__OK);
			orderListResponse.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
			return orderListResponse;
	
		
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
			//	orderListResponse.setStatus_code(JsonResponse.CODE__EXCEPTION);
				orderListResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderListResponse.setStatus_message(JsonResponse.CODE__EXCEPTION);
				return orderListResponse;
			}
		
		
		
		
		
	}
	
	
	

	 //push notification for the web page 
	@RequestMapping(value = "/addNotificationtype", method = RequestMethod.GET)
	public String addNotification() {

		logger.info("User & Shopkeeper Entered addOrderListByShopID() in OrderController  - Post all order list");
		//Response response = new Response();
		
		 String AUTH_KEY_FCM = "AAAAwNSbEj0:APA91bG-iq7DieKB5VF2nTk2Du6bo779LsoBIPFmIyJgxx3Ej_YD6bEiIWlGMNwTeknUv4M64RpS3FtUcfD44fF3YS_gGYe6i35LF2rzLckR2rJj8PlNL0AFsghNBUhBas2tchBQC5bt";
		 String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
		// String deviceToken = "XYWARTYGFRT4T6";
		 String result = "";
		
		try {

			 
			 
			    URL url = new URL(API_URL_FCM);
			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			    conn.setUseCaches(false);
			    conn.setDoInput(true);
			    conn.setDoOutput(true);

			    conn.setRequestMethod("POST");
			    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
			    conn.setRequestProperty("Content-Type", "application/json");

			   // JsonObject json = new JSONPObject();

			    /*json.put("to", deviceToken.trim());
			    JSONObject info = new JSONObject();
			    info.put("title", "notification title"); // Notification title
			    info.put("body", "message body"); // Notification
			                                                            // body
			    json.put("notification", info);*/
			    
			    
			    OutputStreamWriter wr = new OutputStreamWriter(
		                conn.getOutputStream());
		    //    wr.write(json.toString());
		        wr.flush();

		        BufferedReader br = new BufferedReader(new InputStreamReader(
		                (conn.getInputStream())));

		        String output;
		        System.out.println("Output from Server .... \n");
		        while ((output = br.readLine()) != null) {
		            System.out.println(output);
		        }
		        result = "Success";
			
			return result;

		} catch (Exception e) {
	        e.printStackTrace();
	        result = "Faliures";
	    }
	    System.out.println("GCM Notification is sent successfully");

	    return result;
	}

	
}
