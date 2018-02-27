/**
 * 
 */
package com.backend.restapi.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.restapi.dao.LoginDAO;
import com.backend.restapi.user.dto.User;

/**
 * @author Sk Saddam Hosan
 *
 */
@Repository("loginDAO")
@Transactional
public class LoginDAOImpl implements LoginDAO {
	
	private static final Logger log = LoggerFactory.getLogger(LoginDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.backend.restapi.dao.LoginDAO#checkLogin(com.backend.restapi.dto.User)
	 * 
	 * this is for login user or shopKeeper login validation
	 * 
	 */
	@Override
	public User checkLogin(User loginuser) {
		
		log.debug("Inserting LoginDAOImpl class of checkLogin() method");
		User user = null;
		String User_Name = loginuser.getUser_Name();
		String User_Password = loginuser.getUser_Password();
		try {
			String selectLogin = "FROM User WHERE User_Name = ? AND User_Password = ? AND is_Active =:is_Active";
			Query query = sessionFactory.getCurrentSession().createQuery(selectLogin);

			query.setParameter(0, User_Name);
			query.setParameter(1, User_Password);
			query.setParameter("is_Active", true);
			List<User> list = query.getResultList();

			if ((list != null) && (list.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");
				user = list.get(0);
				return user;
			} else {
				log.debug("get successful,No User Name and Password found ");
				return user;
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
