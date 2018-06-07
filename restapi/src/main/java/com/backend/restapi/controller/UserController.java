package com.backend.restapi.controller;

import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.restapi.common.ApiErrors;
import com.backend.restapi.common.JsonResponse;
import com.backend.restapi.common.SuccResponse;
import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.user.dto.NotificationData;
import com.backend.restapi.user.dto.NotificationRequestModel;
import com.backend.restapi.user.dto.Pushnotification;
import com.backend.restapi.user.dto.User;
import com.backend.restapi.user.dto.User_Data;
import com.backend.restapi.user.model.UpdateRequest;
import com.backend.restapi.user.model.UpdateResponse;
import com.backend.restapi.user.model.UpdateUserShopRequest;
import com.backend.restapi.user.model.UpdateUserShopResponse;
import com.backend.restapi.user.model.UserDetailsResponse;
import com.backend.restapi.user.model.UserRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Add user using shop_id and user details
	 */

	@RequestMapping(value = "/add/details", method = RequestMethod.POST)
	public @ResponseBody UserRequest addUserDetails(@RequestBody UserRequest userRequest) {

		logger.debug("Entering addUserDetails() in user conroller ");
		try {
			User user = userRequest.getUserDetails();
			// Address address = userRequest.getUserAddress();

			// UserAddress userAddress= null;
			String Shop = "Shop_";
			String User = "User_";

			/**
			 * This is for ShopKeeper add the new Shopkeeper
			 **/

			/**
			 * check how many shopkeeper are exist in our database if not empty shopkeeper
			 * then add as 1st shopkeeper if exist some shopkeeper then add and create new
			 * shopkeeper
			 **/

			if (userRequest.getShop_ID() == null || userRequest.getShop_ID().isEmpty()) {

				logger.debug(" Adding Shopkeeper into database ");

				// check how many uique shopid or get list of shopID from database
				List<User> shopList = userDAO.listOfShop(userRequest);

				// check user name ,phoner number and email already save into database or not
				for (int j = 0; j < shopList.size(); j++) {
					if (shopList.get(j).getUser_Name() == user.getUser_Name()
							|| shopList.get(j).getContact() == user.getContact()
							|| shopList.get(j).getEmail() == user.getEmail()) {
						userRequest.setStatus_code(JsonResponse.CODE__EMPTY);
						userRequest.setStatus_message(ApiErrors.ERROR__ALREADY_EXIST);
						userRequest.setUserDetails(null);
						userRequest.setUserAddress(null);
						logger.error("already username or phone number or email exisit");
						// allProduct.setProductData(null);
						return userRequest;

					}
				}

				System.out.println(shopList.size());

				userRequest.setShop_ID(Shop + (shopList.size()));

				// Add new Shopkeeper Details
				if (userDAO.addShop(userRequest)) {

					user.setShop_ID(Shop + (shopList.size()));
					userRequest.setStatus_code(JsonResponse.CODE__OK);
					userRequest.setStatus_message(ApiErrors.SUCCESS__SHOPKEEPER_ADD);
					return userRequest;
				} else {
					userRequest.setStatus_code(JsonResponse.CODE__EMPTY);
					userRequest.setStatus_message(ApiErrors.ERROR__EMPTY_SHOPKEEPER);
					return null;
				}

			} else {
				logger.debug(" Adding User into database ");

				/**
				 * Add user
				 **/

				// check how many uique userID or get list of userID from database
				List<User> shopList = userDAO.listOfShop(userRequest);
				int size = shopList.size();
				System.out.println(shopList.size());

				if (shopList.get(size - 1).getUser_ID() == null || shopList.size() == 0) {
					System.out.println("1st user add");

					user.setUser_ID(User + (size - 1));

					if (userDAO.addShop(userRequest)) {
						user.setUser_ID(User + (size - 1));
						userRequest.setStatus_code(JsonResponse.CODE__OK);
						userRequest.setStatus_message(ApiErrors.SUCCESS__USER_ADD);
						return userRequest;

					} else {
						userRequest.setStatus_code(JsonResponse.CODE__EMPTY);
						userRequest.setStatus_message(ApiErrors.ERROR__EMPTY_USER);
						userRequest.setUserDetails(null);
						userRequest.setUserAddress(null);
						return null;
					}
				} else {
					System.out.println("2nd or 3rd user add");

					user.setUser_ID(User + (size - 1));
					if (userDAO.addShop(userRequest)) {
						user.setUser_ID(User + (size - 1));
						userRequest.setStatus_code(JsonResponse.CODE__OK);
						userRequest.setStatus_message(ApiErrors.SUCCESS__USER_ADD);
						return userRequest;

					} else {
						userRequest.setStatus_code(JsonResponse.CODE__EMPTY);
						userRequest.setStatus_message(ApiErrors.ERROR__EMPTY_USER);
						userRequest.setUserDetails(null);
						userRequest.setUserAddress(null);
						return null;
					}
				}

			}

		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */

		}
	}

	/**
	 * update user and shopkeeper
	 **/

	@RequestMapping(value = "/update/details", method = RequestMethod.POST)
	public @ResponseBody UpdateUserShopResponse updateUserDetails(@RequestBody UpdateUserShopRequest updateRequest) {

		logger.debug("Entering updateUserAndShopkeeperDetails() in user conroller ");
		try {
			// UpdateUserShopResponse updateUser = null;
			// User user = updateRequest.getUserDetails();
			// Address address = updateRequest.getUserAddress();

			UpdateUserShopResponse updateUser = userDAO.updateUserShop(updateRequest);

			System.out.println(updateUser);

			if (updateUser != null) {

				updateUser.setStatus_code(JsonResponse.CODE__OK);
				updateUser.setStatus_message(ApiErrors.SUCCESS__USER_UPDATE);
				return updateUser;
			}
			return updateUser;

		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */

		}
	}

	/**
	 * API Get a user details usig userid and shopid
	 */

	@RequestMapping(value = "/details/shopid/userid", method = RequestMethod.POST)
	public @ResponseBody UpdateResponse getUserDetails(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {

		logger.info("Entered getUserDetails()  - one user details ");
		UpdateResponse updateResponse = new UpdateResponse();
		try {

			User_Data user = userDAO.userDetailByShopIdAndUserId(updateRequest);

			if (user.getShop_ID() == null || user.getUser_ID() == null) {

				updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				updateResponse.setStatus_message(ApiErrors.ERROR__EMPTY_USER__SHOP);
				// updateResponse.setRequest_Type("Product Is Not Exist ");
				// allProduct.setProductData(updateData);
				return updateResponse;
			} else {
				updateResponse.setStatus_code(JsonResponse.CODE__OK);
				updateResponse.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
				updateResponse.setUserData(user);
				logger.info("Returning updateUserList and Address");
				return updateResponse;
			}
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			// updateResponse.setStatus_message("Something wrong!!
			// userDetailByShopIdAndUserId() in userDAOImpl");
			updateResponse.setStatus_message(JsonResponse.CODE__ERROR);
			return updateResponse;
		}

	}

	/**
	 * Get All user list using shopID
	 */

	@RequestMapping(value = "/details/shopid", method = RequestMethod.POST)
	public @ResponseBody UserDetailsResponse getAllUserDetailsByShopId(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {

		logger.info("Entered getUserDetails()  - one user details ");

		// String shopid = updateRequest.getShop_ID();
		UserDetailsResponse updateResponse = new UserDetailsResponse();

		try {

			List<User_Data> user = userDAO.userDetailsByShopID(updateRequest);

			if (user.isEmpty()) {

				updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				updateResponse.setStatus_message(ApiErrors.ERROR__NO_USER_EXIST);
				// updateResponse.setRequest_Type("Product Is Not Exist ");
				// allProduct.setProductData(updateData);
				return updateResponse;
			}

			updateResponse.setStatus_code(JsonResponse.CODE__OK);
			updateResponse.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
			updateResponse.setUserData(user);
			logger.info("Returning updateUserList and Address");
			return updateResponse;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			updateResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			updateResponse.setStatus_message(JsonResponse.CODE__ERROR);
			return updateResponse;
		}
	}

	/**
	 * This controller for the push notification
	 * 
	 * Save the Device_ID , and Shop_ID into database for push notification using
	 * FCM
	 * 
	 **/

	@RequestMapping(value = "/save/deviceid/pushnotification", method = RequestMethod.POST)
	public @ResponseBody SuccResponse saveDeviceIdForPushNotification(@RequestBody Pushnotification pushNotification,
			HttpServletRequest request) {

		logger.info(" Enterring the saveDeviceIdForPushNotification() -- in UserController ");

		SuccResponse succResponse = new SuccResponse();

		try {

			logger.debug(" Enterring the saveDeviceIdForPushNotification() -- in UserController ");

			// getting device token from web application using Push notification model class
			if (userDAO.saveDeviceID(pushNotification)) {
				// if device token is save into server
				succResponse.setStatus_code(JsonResponse.CODE__OK);
				succResponse.setStatus_message(ApiErrors.SUCCESS__SAVE_DEVICE_TOKEN);
				return succResponse;

			} else {

				succResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				succResponse.setStatus_message(ApiErrors.ERROR__DEVICE_TOKEN);
				return succResponse;
			}

		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			succResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			succResponse.setStatus_message(JsonResponse.CODE__ERROR);
			return succResponse;
		}

	}

	/**
	 * 
	 * this is for one to many device its send notification from server to every
	 * device
	 * for this we created one java class HeaderRequestInterceptor.java 
	 * and one DAO class and DAOImpl class  used in send() method 
	 */

	@RequestMapping(value = "/fcmNotification", method = RequestMethod.GET)
	public ResponseEntity<String> addNewProductNotificatiopn() {

		System.out.println("Entering push notification ");
		final String TOPIC = "Add Notification";
		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "JSA Notification");
		notification.put("body", "New Product Add");

		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<String> pushNotification = userDAO.send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			String firebaseResponse = pushNotification.get();

			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}

	/***
	 * its send notification from server to one device or particular device
	 * 
	 * Finally It is working Using Andriod
	 */
	@RequestMapping(value = "/fcmNotification/oneToOne/device", method = RequestMethod.GET)
	public String serverToOneDevice() throws Exception {

		//String userDeviceIdKey = "egtbfsd8MCo:APA91bGEDXehUv8iDoJE9Mj802ESCLlAtZXhdqMgHUiLU9hbUn3B-lYdvqISCc-t3FLnxGfS0IrxLkr_yhMtSuu98r-jwWn7opDjuNLjVsJ7dPacVbbunHK9rX_AezwXnROLjMe3w7HL";
		
		String userDeviceIdKey ="fTiMENgaIAI:APA91bHdqOZEZHYlq34Aeve6_30WskJX1oBy56-kSTBh18usYArkr06tXXOIUjDDWBLo9ICLk5q5b0-4U4lo38AlJklTOm7yrMZIlu9LtaiGNqF1frefTyThDaFqyIiMaFnqgSUj-o3q";
		// String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		// String FMCurl = "https://fcm.googleapis.com/fcm/send";
		String FIREBASE_SERVER_KEY = "AIzaSyAuS9vJADBUEWM_pAQcgPDGR_GcNWP2knw"; // You FCM AUTH key
		String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

		URL url = new URL(FIREBASE_API_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + FIREBASE_SERVER_KEY);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("to", userDeviceIdKey.trim());
		JSONObject info = new JSONObject();
		info.put("title", "Notificatoin"); // Notification title
		info.put("body", "Add new Product"); // Notification body
		json.put("notification", info);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();
		System.out.println(conn);
		System.out.println(json.toString());
		return null;
	}

	/**
	 * Using model class from server to only one device sending push notification
	 * 
	 * for this controller we are using two maven dependency that are
	 * 
	 * one is -- gson for google.code.gson and another is httpclient for apache
	 * 
	 * for this we created two model class - NotificationData.java and NotificationRequestModel.java
	 * 
	 ***/

	@RequestMapping(value = "/fcmNotification/oneToOne/device/using/model", method = RequestMethod.GET)
	public String serverToOneDeviceUsingModel() throws Exception {

		System.out.println("Welcome to Developine");

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");

		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
		NotificationData notificationData = new NotificationData();

		notificationData.setDetail("this is firebase push notification from java client (server)");
		notificationData.setTitle("Hello Firebase Push Notification");
		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setTo(
				"d-uXK4cYxcs:APA91bGBmtBExzQQmbBf1tRu1g58QU0SVcYhY8pJRaBgEjwzimeXeO4GYCcryjR_Diq2B8bZIesqJ9lNtlRv5Oh8fR8Yu8rZ_JBh8BUnoXh7p9jhV9-GNhCfkDsr4HNROTtCqNkNWaX0");

		Gson gson = new Gson();
		Type type = new TypeToken<NotificationRequestModel>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		postRequest.addHeader("Authorization", "key=AIzaSyBSahQgns1tqT1RcXMOxlOb2MjxT3y00tc");
		postRequest.setEntity(input);

		System.out.println("reques:" + json);

		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		} else if (response.getStatusLine().getStatusCode() == 200) {

			System.out.println("response:" + EntityUtils.toString(response.getEntity()));

		}

		return null;

	}

}
