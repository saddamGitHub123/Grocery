package com.backend.restapi.daoimpl;



import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.dto.Address;
import com.backend.restapi.dto.User;
import com.backend.restapi.dto.User1;
import com.backend.restapi.dto.UserAddress;
import com.backend.restapi.model.UpdateUserShopRequest;
import com.backend.restapi.model.UpdateUserShopResponse;
import com.backend.restapi.model.UserRequest;
import com.backend.restapi.model.User_Data;



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
	public List<UserAddress> listOfShop(UserRequest userRequest) {
		
	
		log.debug("Inserting UserDAOImpl class of listOfShop() method");
		
		try {

			if (userRequest.getShop_ID() == null || userRequest.getShop_ID().isEmpty()) {
				String uniqueShopID = "from UserAddress where Shop_Count = :Shop_Count GROUP BY Shop_ID ";
				List<UserAddress> list = sessionFactory.getCurrentSession().createQuery(uniqueShopID, UserAddress.class)
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
		            String selectProductsByShopId = "FROM UserAddress WHERE Shop_ID = :Shop_ID ";
		            List<UserAddress> list= sessionFactory
							.getCurrentSession()
							.createQuery(selectProductsByShopId, UserAddress.class)
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
				 userResponse = new UpdateUserShopResponse(userData);
				 
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












	






}