/**
 * 
 */
package com.backend.restapi.controller;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

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
import com.backend.restapi.common.ApiUrl;
import com.backend.restapi.common.JsonResponse;
import com.backend.restapi.dao.ProductDAO;
import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.product.dto.Product;
import com.backend.restapi.product.model.AllProduct;
import com.backend.restapi.product.model.Product_Model;
import com.backend.restapi.product.model.RequestProduct;
import com.backend.restapi.product.model.ResponseProductModel;
import com.backend.restapi.product.model.UniqueProduct;

/**
 * @author Sk Saddam Hosan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public UserDAO userDAO;

	@Autowired
	public ProductDAO productDAO;

	/**
	 * Add product using shopid and product list
	 **/

	@RequestMapping(value = "/add/productsList", method = RequestMethod.POST)
	public @ResponseBody ResponseProductModel addProductsList(@RequestBody RequestProduct allProductData) {
		logger.info("Entered addProductList()  - Save all the product ");

		Product_Model productData = allProductData.getProductDataValue();
		// AllProduct_Data allProduct = new AllProduct_Data();
		ResponseProductModel allProduct = new ResponseProductModel();

		if (allProductData.getShop_ID() == null || allProductData.getShop_ID().isEmpty()) {
			// ** no products exist, error message *//
			allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
			allProduct.setStatus_message(ApiErrors.ERROR__SHOP_ID);
			logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
			// allProduct.setProductData(null);
			return allProduct;
		}

	//	String productName = "Product_";

		try {

			if (productData.getProduct_Name() == null) {
				// ** no products exist, error message *//
				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message(ApiErrors.ERROR__ADD_PRODUCT_NAME);
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				// allProduct.setProductData(null);
				return allProduct;
			}

			List<Product> listOfProduct = productDAO.listProductByShopId(allProductData.getShop_ID());

			// set the size of list
			int size = listOfProduct.size();

			// when shop Keeper 1st time add the product
			if (listOfProduct.size() == 0) {
				// ** no products exist, Add new product *//
				productData.setShop_ID(allProductData.getShop_ID());

				productData.setProduct_ID(ApiUrl.PRODUCT__STRING + size);
				productData.setAvailability(true);
				// allProduct.setProductData(productData);
				// productDAO.addProduct(productData);
				if (productDAO.addProduct(productData)) {
					
					//Sending Push Notification  using FCM
				//	userDAO.getDeviceID(allProductData.getShop_ID(), ApiErrors.FCM_SUCCESS__ADD_PRODUCT);
					
					allProduct.setProductData(productData);
					allProduct.setStatus_code(JsonResponse.CODE__OK);
					allProduct.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
					allProduct.setRequest_Type(ApiErrors.ERROR__ADD_PRODUCT_TYPE);
					logger.info("Returning listOfProductByShopId()");
					return allProduct;
				} else {

					productData = null;
					allProduct.setProductData(productData);
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(ApiErrors.ERROR__DATA__NOT_SAVE);
					return allProduct;
				}
			} else {
				// Add new product of existing product

				productData.setShop_ID(allProductData.getShop_ID());

				productData.setProduct_ID(ApiUrl.PRODUCT__STRING + size);
				productData.setAvailability(true);
				// productList.add(productD);
				if (productDAO.addProduct(productData)) {

					// Here we have to write push notification code
					
					userDAO.getDeviceID(allProductData.getShop_ID(),ApiErrors.FCM_SUCCESS__ADD_PRODUCT);
					
					allProduct.setProductData(productData);
					allProduct.setStatus_code(JsonResponse.CODE__OK);
					allProduct.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
					allProduct.setRequest_Type(ApiErrors.ERROR__ADD_NEW_PRODUCT);
					logger.info("Returning listOfProductByShopId()");
					return allProduct;
				} else {

					productData = null;
					allProduct.setProductData(productData);
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(ApiErrors.ERROR__DATA__NOT_SAVE);
					return allProduct;
				}
			}

		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
			allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return allProduct;
		}

	}
	
	
	
	
	
	
	/**
	 * Update the product list using ShopId and productId
	 **/

	@RequestMapping(value = "/update/productsList", method = RequestMethod.POST)
	public @ResponseBody ResponseProductModel updateProductsList(@RequestBody RequestProduct allProductData) {
		logger.info("Entered updateProductList()  - Update all the product ");

		//AllProduct_Data allProduct = new AllProduct_Data();
		ResponseProductModel allProduct = new ResponseProductModel();
		
		
		Product_Model checkProduct = allProductData.getProductDataValue();
		//Product_Data checkProduct = allProductData.getProductData();

		try {
			
			if(allProductData.getShop_ID() == null || checkProduct.getProduct_ID() == null ) {
				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message(ApiErrors.ERROR__PRODUCT_ID_EMPTY);
				allProduct.setRequest_Type(ApiErrors.ERROR__SHOP_ID_NOT_EXIST);
				//allProduct.setProductData(updateData);
				return allProduct;
				
			}
			

		/*	Product_Data updateData = productDAO.updateProduct(allProductData.getShop_ID(),
					allProductData.getProductData());*/
			Product_Model updateData = productDAO.updateProduct(allProductData.getShop_ID(),
					allProductData.getProductDataValue());
			

			if (updateData == null) {

				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message(ApiErrors.ERROR__PRODUCT_ID_NOT_EXIST);
				allProduct.setRequest_Type(ApiErrors.ERROR__PRODUCT_IS_NOT_EXIST);
				allProduct.setProductData(updateData);
				return allProduct;
			}

			allProduct.setStatus_code(JsonResponse.CODE__OK);
			allProduct.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);
			allProduct.setRequest_Type(ApiErrors.SUCCESS__UPDATE_PRODUCTLIST);
			allProduct.setProductData(updateData);

			logger.info("Returning updateProductsList()");
			return allProduct;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			allProduct.setStatus_code(JsonResponse.CODE__EXCEPTION);
			allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return allProduct;
		}

	}
	
	
	/**
	 * Using shopID to get all product list
	 * 
	 * **
	 */

	@RequestMapping(value = "/list/byShopId", method = RequestMethod.POST)
	public @ResponseBody AllProduct listOfProductByShopId(@RequestBody RequestProduct productResponse) {

		{
			logger.info("Entered listOfProductByShopId()  - Get a Product list from shopId");
			AllProduct allProduct = null;

			try {

				if (productResponse.getShop_ID() == null) {
					// ** no products exist, error message *//
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(JsonResponse.CODE__ERROR);
					logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
					return allProduct;
				}
                
				//not used price and qty_Price
				//List<Product> listOfSimpleEntities = productDAO.productsByShopId(productResponse.getShop_ID());
				
				//for price and qty_price used 
				
				List<UniqueProduct> listOfSimpleEntities = productDAO.productsByShopId(productResponse.getShop_ID());
				//allProduct.setProduct(listOfSimpleEntities);
				allProduct = new AllProduct(listOfSimpleEntities);
           
				if (listOfSimpleEntities == null || listOfSimpleEntities.size() == 0) {
					// ** no products exist, error message *//
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
					logger.error(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
					return allProduct;
				}

				// ** set status OK *//*
				allProduct.setStatus_code(JsonResponse.CODE__OK);
				allProduct.setStatus_message(ApiErrors.SUCCESS__AUTHENTICATED);

			} catch (Exception e) {
				logger.error("listOfProductByShopId(): Error - " + e);
				allProduct.setStatus_code(JsonResponse.CODE__EXCEPTION);
				allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return allProduct;
			}
			logger.info("Returning listOfProductByShopId()");

			return allProduct;
		}
	}
	
	
	/**
	 * This API for GET all the unique product list using name
	 **/

	@RequestMapping(value = "/unique/product/list", method = RequestMethod.GET)
	public @ResponseBody List<UniqueProduct> uniqueProductListByName(HttpServletRequest request)

	{
		logger.info("Entered uniqueProductListByName()  - Get all Unique Product list");
		// Product product = new Product();

		try {

			List<UniqueProduct> productList = productDAO.uniqueProductList();

			// System.out.println(productList);
			return productList;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			logger.error(ApiErrors.ERROR__PRODUCT_DOES_NOT_EXIST);
			return null;
		}

	}
	
	/***
	 * for tesing that push notification working or not 
	 * 
	 * ***/
	
	@RequestMapping(value = "/fcmNotification", method = RequestMethod.GET)
	public String sendTest() {
		logger.info("Entered getUserDetails()  - one user details ");
		
		System.out.println(addNewProductNotificatiopn());
		
		logger.info(" value is comming ");
		
		return "value is cmming";
	}
	
	
	
	/**
	 * This is the add new product push notification 
	 * 
	 * */
	public ResponseEntity<String> addNewProductNotificatiopn() {
		
		final String TOPIC = "JavaSampleApproach";
		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "JSA Notification");
		notification.put("body", "Happy Message!");
		
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
	 * Here we write the push notification method and code 
	 * 
	 * **/
	public String serverToOneDevice() throws Exception {

		//String userDeviceIdKey = "egtbfsd8MCo:APA91bGEDXehUv8iDoJE9Mj802ESCLlAtZXhdqMgHUiLU9hbUn3B-lYdvqISCc-t3FLnxGfS0IrxLkr_yhMtSuu98r-jwWn7opDjuNLjVsJ7dPacVbbunHK9rX_AezwXnROLjMe3w7HL";
		
		String userDeviceIdKey = "fk7oBlniDtA:APA91bEEvqqvpLhvGZuHLHgkZl8eaI6FcIGMa37ljIRltsUrgmDv86eTsPp7MzQ5EAlhI2Fz566E3jePiDx33iPP2UPW2Gc8mZRQQ8irj7BVXiJ2HhxPbNIEK8xwjkRhz1BG9rotmXlx";
		
		
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

}
