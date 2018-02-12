/**
 * 
 */
package com.backend.restapi.daoimpl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.restapi.dao.TestDAO;
import com.backend.restapi.dto.Test;

/**
 * @author Sk Saddam Hosan
 *
 */

@Repository("testDAO")
@Transactional
public class TestDAOImpl implements TestDAO {

	/* (non-Javadoc)
	 * @see com.backend.restapi.dao.TestDAO#addTest(com.backend.restapi.model.Test)
	 */
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean addTest(Test test) {
		// TODO Auto-generated method stub
		System.out.println("Enter testDAOImpl class");
		
          try{
			
			sessionFactory.getCurrentSession().save(test);
			System.out.println("Returring pl class");
			return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		//return false;
	}

	

}













