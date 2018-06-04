package com.backend.restapi.daoimpl;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.user.dto.Address;
import com.backend.restapi.user.dto.HeaderRequestInterceptor;
import com.backend.restapi.user.dto.Pushnotification;
import com.backend.restapi.user.dto.User;
import com.backend.restapi.user.dto.User1;
import com.backend.restapi.user.dto.UserAddress;
import com.backend.restapi.user.dto.User_Data;
import com.backend.restapi.user.model.UpdateRequest;
import com.backend.restapi.user.model.UpdateUserShopRequest;
import com.backend.restapi.user.model.UpdateUserShopResponse;
import com.backend.restapi.user.model.UserRequest;



/**
 * 
 * @author saddam
 *
 */
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
		private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.backend.restapi.dao.UserDAO#listOfShop(com.backend.restapi.model.
	 * UserRequest)
	 * 
	 * create method for getting list of shopID 
	 */
	@Override
	public List<User> listOfShop(UserRequest userRequest) {
		
	
		log.debug("Inserting UserDAOImpl class of listOfShop() method");
		
		try {

			if (userRequest.getShop_ID() == null || userRequest.getShop_ID().isEmpty()) {
				String uniqueShopID = "from User where Shop_Count = :Shop_Count GROUP BY Shop_ID ";
				List<User> list = sessionFactory.getCurrentSession().createQuery(uniqueShopID, User.class)
						// .setParameter("Shop_ID", Shop_ID)
						.setParameter("Shop_Count", true).getResultList();
				System.out.println(list.size());
				
				if ((list != null) && (list.size() > 0)) {
					//userFound= true;
					log.debug("get successful,Product list is found");
					return list;
				}
				else {
					log.debug("get successful,Product List is not found ");
					return list;
				 }

			} else {
				
				   log.debug("List all the user using shopId");	
				   
				   String Shop_ID = userRequest.getShop_ID();
		            String selectProductsByShopId = "FROM User WHERE Shop_ID = :Shop_ID ";
		            List<User> list= sessionFactory
							.getCurrentSession()
							.createQuery(selectProductsByShopId, User.class)
								.setParameter("Shop_ID", Shop_ID)
								//.setParameter("active", true)
									.getResultList();

				if ((list != null) && (list.size() > 0)) {
					//userFound= true;
					log.debug("get successful,Product list is found");
					return list;
				}
				else {
					log.debug("get successful,Product List is not found ");
					return list;
				 }

			}
		}catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		}
	    
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.backend.restapi.dao.UserDAO#addShop(com.backend.restapi.model.UserRequest)
	 * 
	 * Add new shop keeper details
	 */
	@Override
	public boolean addShop(UserRequest userRequest) {

		log.debug("Inserting UserDAOImpl class of addShop() method");

		try {
			User user = userRequest.getUserDetails();
			Address address = userRequest.getUserAddress();
			UserAddress userAddress= null;
			
			String Shop_ID = userRequest.getShop_ID();
			String User_ID = user.getUser_ID();
			boolean is_Active = true;
			boolean shop_Count = true;
			boolean address_Active = true;
			
			
			if(address  == null ) {
				
				userAddress = new UserAddress(Shop_ID,User_ID, user.getName(), user.getUser_Name(), user.getUser_Password(),
						user.getContact(),user.getEmail(), is_Active, shop_Count,userRequest.getShop_ID(),user.getUser_ID(),
						address_Active);
				
				System.out.println(userAddress);
				sessionFactory.getCurrentSession().persist(userAddress);
				
				return true;
			}
			else {
			userAddress = new UserAddress(Shop_ID,User_ID, user.getName(), user.getUser_Name(), user.getUser_Password(),
					user.getContact(),user.getEmail(), is_Active, shop_Count,userRequest.getShop_ID(),user.getUser_ID(),
					address.getHouse_No(), address.getLocality(),address.getLandmark(),address.getLocality(),
					address.getArea(), address.getCity(),address_Active);
			
			sessionFactory.getCurrentSession().persist(userAddress);
			

			return true;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */
		}
	}

	
	
	
	
	
	
	
	
	

	@Override
	public List<User1> list() {
		
			return sessionFactory
					.getCurrentSession()
						.createQuery("FROM User" , User1.class)
							.getResultList();
		
	}
	
	

	@Override
	public boolean register(User1 user) {
		return false;
	}

// List of user using shop_ID 

	@Override
	public List<UserAddress> listOfUser(UserRequest userRequest) {
		
		log.debug("Inserting UserDAOImpl class of listOfUser() method");
		try {
		
			
		String Shop_ID = userRequest.getShop_ID();


		
		String selectProductsByShopId = "FROM UserAddress WHERE Shop_ID = :Shop_ID AND Is_Active =:Is_Active";
        List<UserAddress> list = sessionFactory
				.getCurrentSession()
				.createQuery(selectProductsByShopId, UserAddress.class)
					.setParameter("Shop_ID", Shop_ID)
					.setParameter("Is_Active", true)
						.getResultList();
        System.out.println(list);

		return list;
		}catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		
	    }
		
	}



	/* (non-Javadoc)
	 * @see com.backend.restapi.dao.UserDAO#updateUserShop(com.backend.restapi.model.UpdateUserShopRequest)
	 * 
	 * update User as well shopkeeper
	 */
	@Override
	public UpdateUserShopResponse updateUserShop(UpdateUserShopRequest updateRequest) {
		log.debug("Inserting UserDAOImpl class of addShop() method");

		try {
			
			String User_ID = updateRequest.getUser_ID();
			String Shop_ID = updateRequest.getShop_ID();
			UserAddress userAddress = null ;
			UpdateUserShopResponse userResponse = null;
			//User_Data userData = updateRequest.getUserData();
			User_Data userData = updateRequest.getUserData();
			Address address = userData.getUserAddress();
			
			if(User_ID == null || User_ID.isEmpty() ) {
				
				/**
				 * update for shopKeeper
				 * ***/
				log.debug("Enterring Shoopkeeper Update ");
				
				String selectProductsByShopId = "FROM UserAddress WHERE Shop_ID = :Shop_ID";
				List<UserAddress> list = sessionFactory.getCurrentSession()
						.createQuery(selectProductsByShopId, UserAddress.class).setParameter("Shop_ID", Shop_ID)
						.getResultList();
				
				if ((list != null) && (list.size() > 0)) {
					//userFound= true;
					log.debug("get successful,ShopKeeper list is found");
					
					for(int i = 0 ; i< list.size() ; i++) {
						   if(list.get(i).getUser_ID() == null || list.get(i).getUser_ID().isEmpty() )
							   userAddress = list.get(i);
					   }
					
					userAddress.setName(userData.getName());
					userAddress.setUser_Name(userData.getUser_Name());
					userAddress.setUser_Password(userData.getUser_Password());
					userAddress.setContact(userData.getContact());
					userAddress.setEmail(userData.getEmail());
					userAddress.setHouse_No(address.getHouse_No());
					userAddress.setLocality(address.getLocality());
					userAddress.setLandmark(address.getLandmark());
					userAddress.setPinCode(address.getPinCode());
					userAddress.setArea(address.getArea());
					userAddress.setCity(address.getCity());
					
			   
				  // userAddress = new userAddress();
				 /*  userAddress = new UserAddress(Shop_ID, User_ID,userData.getName(), userData.getUser_Name(),
						   userData.getUser_Password(), userData.getContact(), userData.getEmail(), is_Active, shop_Count, 
						   updateRequest.getShop_ID(),updateRequest.getUser_ID(),address.getHouse_No(),address.getLocality(),
						   address.getLandmark(), address.getPinCode(), address.getArea(), address.getCity(),address_Active);
				   */
				   
				 sessionFactory.getCurrentSession().update(userAddress);
					  
				 sessionFactory.openSession().beginTransaction().commit();
				 
				//System.out.println(list);
				// userResponse = new UpdateUserShopResponse(userData);
				 
				return userResponse;
				}
				else {
					log.debug("ShopKeeper is not found ");
					//updateRequest = null;
					return userResponse;
				 }
				   
			}
			else {
				/**
				 * Update for User
				 * **/
				
				log.debug("Enterring User Update ");
				
				String selectProductsByShopId = "FROM UserAddress WHERE Shop_ID = :Shop_ID AND User_ID = :User_ID";
				List<UserAddress> list = sessionFactory.getCurrentSession()
						.createQuery(selectProductsByShopId, UserAddress.class).setParameter("Shop_ID", Shop_ID)
						.setParameter("User_ID", User_ID)
						.getResultList();
				
				
				if ((list != null) && (list.size() > 0)) {
					//userFound= true;
					log.debug("get successful,User list is found");
					userAddress = list.get(0);
					
					
					userAddress.setName(userData.getName());
					userAddress.setUser_Name(userData.getUser_Name());
					userAddress.setUser_Password(userData.getUser_Password());
					userAddress.setContact(userData.getContact());
					userAddress.setEmail(userData.getEmail());
					userAddress.setHouse_No(address.getHouse_No());
					userAddress.setLocality(address.getLocality());
					userAddress.setLandmark(address.getLandmark());
					userAddress.setPinCode(address.getPinCode());
					userAddress.setArea(address.getArea());
					userAddress.setCity(address.getCity());
					
					 userResponse = new UpdateUserShopResponse(userData);
					 
						return userResponse;
				}
				else {
					log.debug("User List is not found ");
					
					;
					return userResponse;
				 }
				
				
				
				
			}
			
			

		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */
		}
	}





	/**
	 * getting a users details using Shopid and UserID
	 * **/


@Override
public User_Data userDetailByShopIdAndUserId(UpdateRequest updateRequest) {

	
	
	
	
	try{
				
	    log.debug("showing one user details usign user id and shopid");
        
	    String Shop_ID = updateRequest.getShop_ID();
	    String User_ID = updateRequest.getUser_ID();		
		User_Data userData = new User_Data();

		
		// select the list of a user data usign shopid and user id in user table
					String selectProductsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID AND User_ID = :User_ID";
					List<User_Data> list = sessionFactory.getCurrentSession()
							.createQuery(selectProductsByShopId, User_Data.class).setParameter("Shop_ID", Shop_ID)
							.setParameter("User_ID", User_ID).getResultList();
		
		// checking the null value of the user table list
		if ((list != null) && (list.size() > 0)) {
			// userFound= true;
			log.debug("get successful,User details is found");

			userData = list.get(0);
			
			Address address = new Address();
			String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
			List<Address> addressList = sessionFactory.getCurrentSession()
					.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
					.getResultList();

			if ((addressList != null) && (addressList.size() > 0)) {
				// userFound= true;
				log.debug("get successful,Adress details is found");

				address = addressList.get(0);
				userData.setUserAddress(address);

				return userData;
			}
		}

		return userData;
	}catch (RuntimeException re)
	{
		log.error("get failed", re);
		throw re;
	}
	finally
	{
		/*if (sessionFactory != null)
		{
			sessionFactory.close();
		}*/
	
    }

}



/**
 * Getting all user Details in a list using shopID from user and address table 
 * **/
	@Override
	public List<User_Data> userDetailsByShopID(UpdateRequest updateRequest) {

		try{
			
		    log.debug("Entering userDAOImpl class - in userDetailsByShopID()");
	        //int count = 0;
	        //getting shopID from user
		    String Shop_ID = updateRequest.getShop_ID();		
			User_Data userData = new User_Data();
			
			// User_Data list of array content all userList
			List<User_Data> userList = new ArrayList();

			
			// select the list of a user data using shopID in user table
						String selectUserDetailsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID AND is_active =:is_active";
						List<User_Data> list = sessionFactory.getCurrentSession()
								.createQuery(selectUserDetailsByShopId, User_Data.class)
								.setParameter("Shop_ID", Shop_ID)
								.setParameter("is_active", true)
								.getResultList();
		
			// checking the null value of the user table list
			if ((list != null) && (list.size() > 0)) {
				log.debug("get successful,User details ShopID is found");
				
				//counting for one by one userList
			//	for (User_Data entity : list) {
				for( int count= 0; count<list.size();count++) {
					
					if(list.get(count).getUser_ID() != null) {
					userData = list.get(count);
					
					String User_ID = userData.getUser_ID();
					Address address = new Address();
					String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
					List<Address> addressList = sessionFactory.getCurrentSession()
							.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
							.getResultList();
					
					//Checking the addressList Empty or not
					if ((addressList != null) && (addressList.size() > 0)) {
						log.debug("get successful,Adress details is found");

						address = addressList.get(0);
						//Set the particular address in their user
						userData.setUserAddress(address);
						
						//Add the all object to array list
						userList.add(userData);
					}
					else {
						address = null;
						//Set the particular address in their user
						userData.setUserAddress(address);
						
						//Add the all object to array list
						userList.add(userData);
					}
					
				}
				}
				
				return userList;
				
			}

			return userList;
		}catch (RuntimeException re)
		{
			log.error("All UserList failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		
	    }
	}



/* (non-Javadoc)
 * @see com.backend.restapi.dao.UserDAO#send(org.springframework.http.HttpEntity)
 * This for pus notification implement 
 * FIREBASE_SERVER_KEY you have to create into firebase official site 
 * create firebase project and generate  Firebase server key 
 */
	@Override
	public CompletableFuture<String> send(HttpEntity<String> entity) {
		
		String FIREBASE_SERVER_KEY = "AIzaSyAuS9vJADBUEWM_pAQcgPDGR_GcNWP2knw"; // You FCM AUTH key
		String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send"; 
		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}



	/* (non-Javadoc)
	 * @see com.backend.restapi.dao.UserDAO#saveDeviceID(com.backend.restapi.user.dto.Pushnotification)
	 * 
	 * This is the saveDeviceID implement method in UserDAOImpl class 
	 * Save the Device_ID using Shop_ID
	 * 
	 */
	@Override
	public boolean saveDeviceID(Pushnotification pushNotification) {
		
		log.debug(" Enterring the UserDAOImpl class in saveDeviceID() method ");
		try {
			Pushnotification pushNotificationUpdate = null;
			String Shop_ID = pushNotification.getShop_ID();
			boolean Is_Active = true;
			
			/* Check the Shop_ID is already in table or not 
			 * get list of the device_id
			 * */			
			String shopIDQuery = "FROM Pushnotification WHERE  Shop_ID = :Shop_ID AND Is_Active= :Is_Active";
			List<Pushnotification> deviceIdList = sessionFactory.getCurrentSession()
					.createQuery(shopIDQuery, Pushnotification.class)
					.setParameter("Shop_ID", Shop_ID)
					.setParameter("Is_Active", Is_Active)
					.getResultList();
			
			// check list is null or not if list is null then add or save the new device_id into database
			// if list have the shop_id the update the device_id
				
				if((deviceIdList != null) && (deviceIdList.size() > 0)) {
				//System.out.println(userAddress);
					log.info("Enterring device token update method");
					pushNotificationUpdate = deviceIdList.get(0);
					pushNotificationUpdate.setDevice_ID(pushNotification.getDevice_ID());
					pushNotificationUpdate.setIs_Active(Is_Active);
					pushNotificationUpdate.setMessage_Body(pushNotification.getMessage_Body());
					sessionFactory.getCurrentSession().update(pushNotificationUpdate);
					sessionFactory.openSession().beginTransaction().commit();
					log.info("Returring  device token update method");
					return true;
				}
				else {
					log.info("Enterring device token save method");
					pushNotification.setIs_Active(true);
					sessionFactory.getCurrentSession().save(pushNotification);
					log.info("Returring  device token save method");
					return true;
				}
			
		}catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			} finally {
				/*
				 * if (sessionFactory != null) { sessionFactory.close(); }
				 */
			}
	}



	






}