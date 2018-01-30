package com.backend.restapi.daoimpl;



import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.restapi.dao.UserDAO;
import com.backend.restapi.dto.User1;



/**
 * 
 * @author saddam
 *
 */
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	//	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/*@Override
	public boolean register(User user) {
		
		try{
			
			sessionFactory.getCurrentSession().persist(user);
			return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}*/
	
	

	@Override
	public List<User1> list() {
		
			return sessionFactory
					.getCurrentSession()
						.createQuery("FROM User" , User1.class)
							.getResultList();
		
	}
	
	

	//save image to database as a blob value
/*	@Override
	public Test saveImage(Test test) {
		
		  String url = test.getUrl();
		System.out.println(url);
		//save image into database
    	File file = new File(url);
        byte[] bFile = new byte[(int) file.length()];

        try {
	     FileInputStream fileInputStream = new FileInputStream(file);
	     //convert file into array of bytes
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        
        Test test1 = new Test();
        test1.setImage(bFile);
        test1.setUrl(url);
        sessionFactory.getCurrentSession().persist(test1);
        
        
        Integer test_ID = 2;
        
        String selectProductsByShopId = "FROM Test WHERE test_ID = :test_ID";
        List<Test> list= sessionFactory
				.getCurrentSession()
				.createQuery(selectProductsByShopId, Test.class)
					.setParameter("test_ID", test_ID)
				//	.setParameter("Availability", true)
						.getResultList();
        
       // for (int i=list.size()-1  ;i>0;i++) {
        //int count = list.size();
        byte[] bAvatar = list.get(0).getImage();
       // Blob blob = list.get(0).getImage();
        
        //Blob blob = rs.getBlob(cloumnName[i]);
       // byte[] bdata = bAvatar.getBytes(1, (int) blob.length());
        
      //  String s = new String(bAvatar);
       // byte[] bdata = new byte[(int) bAvatar.length];
       // String s = new String(bdata);
        String base64Encoded = DatatypeConverter.printBase64Binary(bAvatar);
        
        byte[] byteArray = DatatypeConverter.parseBase64Binary(base64Encoded);
        
        System.out.println(base64Encoded);
        System.out.println(byteArray);
        
          Test t1 = list.get(0);

        try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Inxts-Windows\\Pictures\\image\\tiger1.jpg");
            fos.write(bAvatar);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

		
		return t1;
	}*/



	@Override
	public boolean register(User1 user) {
		// TODO Auto-generated method stub
		return false;
	}




}