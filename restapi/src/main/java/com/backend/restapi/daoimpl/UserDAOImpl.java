package com.backend.restapi.daoimpl;



import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;
import org.hibernate.*;
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
import com.backend.restapi.model.UserRequest;



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

		String uniqueShopID = "from UserAddress where Shop_Count = :Shop_Count GROUP BY Shop_ID ";
        List<UserAddress> list = sessionFactory
				.getCurrentSession()
				.createQuery(uniqueShopID, UserAddress.class)
					//.setParameter("Shop_ID", Shop_ID)
					.setParameter("Shop_Count", true)
						.getResultList();
        System.out.println(list.size());

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
	 * @see com.backend.restapi.dao.UserDAO#addShop(com.backend.restapi.model.UserRequest)
	 * 
	 * Add new shop keeper details
	 */
	@Override
	public boolean addShop(UserRequest userRequest) {

		log.debug("Inserting UserDAOImpl class of addShop() method");

		try {
			UserAddress userAddress= null;
			
			String Shop_ID = userRequest.getShop_ID();
			boolean is_Active = true;
			boolean shop_Count = true;
			boolean address_Active = true;
			
			User user = userRequest.getUserDetails();
			Address address = userRequest.getUserAddress();
			
			
			userAddress = new UserAddress(Shop_ID, user.getName(), user.getUser_Name(), user.getUser_Password(),
					user.getContact(),user.getEmail(), is_Active, shop_Count,userRequest.getShop_ID(),
					address.getHouse_No(), address.getLocality(),address.getLandmark(),address.getLocality(),
					address.getArea(), address.getCity(),address_Active);
			
			sessionFactory.getCurrentSession().persist(userAddress);
			

			return true;
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
		// TODO Auto-generated method stub
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













	






}