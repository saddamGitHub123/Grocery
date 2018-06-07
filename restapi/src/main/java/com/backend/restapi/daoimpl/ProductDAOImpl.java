/**
 * 
 */
package com.backend.restapi.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import com.backend.restapi.dao.ProductDAO;
import com.backend.restapi.product.dto.Price;
import com.backend.restapi.product.dto.Product;
import com.backend.restapi.product.model.Product_Model;
import com.backend.restapi.product.model.UniqueProduct;
import com.backend.restapi.user.dto.HeaderRequestInterceptor;

/**
 * @author Sk Saddam Hosan
 *
 */
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

private static final Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Product> listProductByShopId(String Shop_ID) {
		
		
	
		try{
					
		    log.debug("Add all the product ProductDAOImpl class");	
           // String selectProductsByShopId = "FROM Product_Data WHERE Shop_ID = :Shop_ID AND Availability = :Availability";
		    String selectProductsByShopId = "FROM Product WHERE Shop_ID = :Shop_ID";
           /* return sessionFactory
					.getCurrentSession()
						.createQuery(selectProductsByShopId, Product_Data.class)
							.setParameter("Shop_ID", Shop_ID)
							.setParameter("Availability", true)
								.getResultList();*/
            List<Product> list= sessionFactory
					.getCurrentSession()
					.createQuery(selectProductsByShopId, Product.class)
						.setParameter("Shop_ID", Shop_ID)
					//	.setParameter("Availability", true)
							.getResultList();
            System.out.println("list size is"+list.size());

		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			log.debug("get successful,Product list is found");
			return list;
		}
		else {
			log.debug("get successful,Product List is not found ");
			
		 }

		//session.close();
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
	
	
	
	

	/**
	 * 
	 * Add the all product using hibernate updated of price and avalibel 
	 * **/
	@Override
	public boolean addProduct(Product_Model product) {
		
	try{
		    log.debug("Add all the product");
		  //  Product pro = new Product();
		    Product pro = null;
		    
		    //get all data from Product_Data class
		     int ID = product.getID();	
			// String code = product.getCode();
			 String Product_Name = product.getProduct_Name();
			 String Product_ID = product.getProduct_ID();
			 String Shop_ID = product.getShop_ID();
			// private int Product_Price;
			 byte[] Product_Image = product.getProduct_Image();
			 String Product_Category = product.getProduct_Category();
			  String Product_Type = product.getProduct_Type();
			  boolean Availability =product.isAvailability();
			  boolean Product_Active = true;
			  
			  
			 pro = new Product(ID, Product_Name, Product_ID, Shop_ID, Product_Image,
						Product_Category, Product_Type, Availability, Product_Active); 
		   
			  //set all the data from Product_Data.java to Product.java class
			  /*pro.setID(ID);
			  pro.setProduct_Category(Product_Category);
			
			  pro.setProduct_ID(Product_ID);
			  pro.setProduct_Name(Product_Name);
			  pro.setShop_ID(Shop_ID);
			  pro.setProduct_Image(Product_Image);
			  pro.setProduct_Type(Product_Type);
			  pro.setAvailability(Availability);
			  pro.setProduct_Active(true);*/
			  
			  //save the Product value
			  sessionFactory.getCurrentSession().persist(pro);
			  
			  
			  
			 // Price pri = new Price();
			  
			  List<String> items = product.getProduct_Price();
			  
			  List<String> unit = product.getUnit();
			  
			  List<String> stock = product.getStock_Value();
			  
			   System.out.println(items.size());
			  for (int i = 0 ;i<items.size();i++) {
				  Price pri = null;
				  //Price pri1 = null;
				 
				  //If quantity is empty then its save null value to database 
				  if(unit == null || unit.isEmpty()) {
					  
					  /*for(int j= 0 ;j< items.size() ;j++) {
					  unit.add(null);
					 
					  System.out.println(unit.get(i));
					  }*/
					  
					  // Set only three value when quantity is not available
					  pri = new Price(Product_ID,Shop_ID,items.get(i),stock.get(i));
					  //pri = new Price(Product_ID,Shop_ID,items.get(i));
					  sessionFactory.getCurrentSession().persist(pri);
					  
				  }   
				  else {
					  
			     //Set all value		  
				  pri = new Price(Product_ID,Shop_ID,items.get(i),unit.get(i),stock.get(i));
					 // pri = new Price(Product_ID,Shop_ID,items.get(i),unit.get(i));
				  sessionFactory.getCurrentSession().persist(pri);
				  
				  }
				  
			  }		  
			  
			//  sessionFactory.openSession().flush();
			 // sessionFactory.openSession().beginTransaction().commit();
			return true;
			
		}catch (RuntimeException re)
		{
			log.error("Save product failed", re);
			throw re;
		}
	}
	
	
	
	
	
	/**
	 * Update product list using shopid and productid
	 * only product list will be updated ,prices is not updated
	 * ***/

	/*@Override
	public Product_Data updateProduct(String Shop_ID,Product_Data productData) {*/
	@Override
	public Product_Model updateProduct(String Shop_ID,Product_Model productData) {
		
		String Product_ID  = productData.getProduct_ID();
		//String product_Name = productData.getProduct_Name();
		
		try{				
		
		String selectProductsByShopId = "FROM Product WHERE Shop_ID = :Shop_ID AND Product_ID = :Product_ID";
		List <Product> list = sessionFactory
				.getCurrentSession()
					.createQuery(selectProductsByShopId, Product.class)
						.setParameter("Shop_ID", Shop_ID)
						.setParameter("Product_ID", Product_ID)
							.getResultList();

		 Product product = new Product();
	        if ((list != null) && (list.size() > 0)) {
				//userFound= true;
				log.debug("get successful,product update  found");
				
				product = list.get(0);
				//set the updated product value 
				product.setProduct_Name(productData.getProduct_Name());
				product.setProduct_Category(productData.getProduct_Category());
				product.setProduct_Image(productData.getProduct_Image());
				product.setProduct_Type(productData.getProduct_Type());
				product.setAvailability(productData.isAvailability());
				//update product
				sessionFactory.getCurrentSession().update(product);
				
				//update price and qty price
				String selectProductsByShopId1 = "FROM Price WHERE Shop_ID = :Shop_ID AND Product_ID = :Product_ID";
				List <Price> priceList = sessionFactory
						.getCurrentSession()
							.createQuery(selectProductsByShopId1, Price.class)
								.setParameter("Shop_ID", Shop_ID)
								.setParameter("Product_ID", Product_ID)
									.getResultList();
				
				 List<String> items = productData.getProduct_Price();
				  
				  List<String> unit = productData.getUnit();
				  
				  List<String> Stock_value = productData.getStock_Value();
				  
				  System.out.println(priceList.size());
				  Price pri;
				for(int i = 0 ;i< items.size();i++) {
					pri = priceList.get(i);
					
					int ID = priceList.get(i).getID();
					String Price = items.get(i);
					String Qty_Price = unit.get(i);
					
					String Stock = Stock_value.get(i);
					
					
					// update particular column using ID and productID and shopID
					String updateSingleValu = "UPDATE Price SET Price = :Price , Qty_Price =:Qty_Price , Stock =:Stock WHERE ID = :ID AND Product_ID = :Product_ID";
					
					//set the data base value usign hibernat query
					int updatedEntities = sessionFactory.getCurrentSession()
							 .createQuery( updateSingleValu )
							 .setParameter( "ID", ID )
					        .setParameter( "Price", Price )
					        .setParameter( "Qty_Price", Qty_Price )
					        .setParameter( "Stock", Stock )
					        .setParameter( "Product_ID", Product_ID )
					        .executeUpdate();
					
					
					
					//int id = priceList.get(i).getID();
					// pri = new Price(Product_ID, Shop_ID,items.get(i), unit.get(i));
					//pri.setShop_ID(Shop_ID);
					//pri.setProduct_ID(Product_ID);
					//pri.setPrice(items.get(i));
					//pri.setQty_Price(unit.get(i));
					 
					 // sessionFactory.getCurrentSession().saveOrUpdate(pri);
					// sessionFactory.getCurrentSession().clear();
					// sessionFactory.getCurrentSession().update(pri);
				}
				
				productData.setShop_ID(Shop_ID);
				
				return productData;
			}
	        return null;
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
	
	
	
	@Override
	public List<UniqueProduct> productsByShopId(String Shop_ID) {
		
		UniqueProduct product;
		Product uniqProduct;
		Price pri;
		//List<Price> list = new ArrayList();
		List<UniqueProduct> uniList = new ArrayList();

		try{
			
			//Get all product list using shop_id
			   //String selectProductsByShopId = "FROM Product WHERE Shop_ID = :Shop_ID Group By Product_Name";
			String selectProductsByShopId = "FROM Product WHERE Shop_ID = :Shop_ID ";
			
			List <Product> listProduct = sessionFactory
					.getCurrentSession()
						.createQuery(selectProductsByShopId, Product.class)
							.setParameter("Shop_ID", Shop_ID)
								.getResultList();
			
			//check list value is empty or not
			if ((listProduct != null) && (listProduct.size() > 0)) {
     		log.debug("get successful,Product list is found");
     		
     		for(int i=0;i<listProduct.size();i++) {
				
				 uniqProduct = listProduct.get(i);
				//String Shop_ID = uniqProduct.getShop_ID();
				String Product_ID = uniqProduct.getProduct_ID();
				
				String uniq = "FROM Price WHERE Shop_ID = :Shop_ID AND Product_ID = :Product_ID";
				List<Price> pricelist = sessionFactory.getCurrentSession()
						.createQuery(uniq, Price.class).setParameter("Shop_ID", Shop_ID)
						.setParameter("Product_ID", Product_ID)
						.getResultList();
				
				System.out.println(pricelist.size());
				//Set all data 
				String Product_Name = uniqProduct.getProduct_Name();
				byte[] Product_Image = uniqProduct.getProduct_Image();
				String Product_Category =uniqProduct.getProduct_Category();
				String Product_Type = uniqProduct.getProduct_Type();
				boolean Availability = uniqProduct.isAvailability();
				//String Product_Price = uniqProduct.get
				//String Unit = 
				
				//create array list for unit and product price
				ArrayList<String> Unit = new ArrayList<String>();
				ArrayList<String> Product_Price = new ArrayList<String>();
				
				ArrayList<String> Stock = new ArrayList<String>();
				for(int j=0;j<pricelist.size();j++) {
					
					     //get particular price 
					      pri = pricelist.get(j);

					      //add the value to array list
				           String priValue =  pri.getPrice();
				           Product_Price.add(priValue);				           
				           String qtyPri = pri.getQty_Price();
				           Unit.add(qtyPri);
				           
				           String Stock_Value = pri.getStock();
				           Stock.add(Stock_Value);
				           
				           System.out.println(priValue+" "+qtyPri+""+Stock_Value);
	
				}
				
				//call uniqueProduct constructor
				product = new UniqueProduct(Product_ID,Shop_ID,Product_Name,Product_Image,Product_Category,Product_Type,Availability,Product_Price,Unit,Stock);
				
				uniList.add(product);
				
				
			}
				return uniList;
			}
			else {
				log.debug("get successful,Product List is not found ");
				
			 }

			//session.close();
			//return list; 
			return uniList;
			
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
	 * Get all unique productList using product name in productDAOImpl class
	 * **/

	@Override
	public List<UniqueProduct> uniqueProductList() {

		log.debug("Show Unique product list ProductDAOImpl class");
		
		UniqueProduct product;
		Product uniqProduct;
		
		// Getting unique product details using product name
		List<Product> listProduct = new ArrayList<>();
		Set<Product> setProduct = new HashSet<>();
		
		Price pri;
		
		List<UniqueProduct> uniList = new ArrayList<>();
		try {

			List<Product> list = sessionFactory.getCurrentSession()
					.createQuery("FROM Product").getResultList();
			

			System.out.println(listProduct);
			
			for(Product obj : list) {
				if(setProduct.add(obj)) {
					listProduct.add(obj);
				}
			}

			if ((listProduct != null) && (listProduct.size() > 0)) {
				// userFound= true;
				log.debug("get successful,unique productlist found");
				
				for(int i=0;i<listProduct.size();i++) {
					
					uniqProduct = listProduct.get(i);
					String Shop_ID = uniqProduct.getShop_ID();
					String Product_ID = uniqProduct.getProduct_ID();
					
					String uniq = "FROM Price WHERE Shop_ID = :Shop_ID AND Product_ID = :Product_ID";
					List<Price> pricelist = sessionFactory.getCurrentSession()
							.createQuery(uniq, Price.class).setParameter("Shop_ID", Shop_ID)
							.setParameter("Product_ID", Product_ID)
							.getResultList();
					System.out.println(pricelist.size());
					String Product_Name = uniqProduct.getProduct_Name();
					byte[] Product_Image = uniqProduct.getProduct_Image();
					String Product_Category =uniqProduct.getProduct_Category();
					String Product_Type = uniqProduct.getProduct_Type();
					//String Product_Price = uniqProduct.get
					//String Unit = 
					ArrayList<String> Unit = new ArrayList<String>();
					ArrayList<String> Product_Price = new ArrayList<String>();
					ArrayList<String> Stock = new ArrayList<String>();
					
					for(int j=0;j<pricelist.size();j++) {
						pri = pricelist.get(j);
						
						 // pri.getPrice();
					           String priValue =  pri.getPrice();
					           Product_Price.add(priValue);
					           
					           String qtyPri = pri.getQty_Price();
					           Unit.add(priValue);
					           
					           String Stock_Value = pri.getStock();
					           Stock.add(Stock_Value);
					           
					           System.out.println(priValue+" "+qtyPri);
					           //List<String> newList = new ArrayList<String>();   
						 // List<String> Product_Price = newList.add(pri1) ;
                          // List<String> Unit;
						 // pri.getQty_Price();
						//list.add(pri);
						//System.out.println(list);
					}
					
					//product = new UniqueProduct(Product_ID,Shop_ID,Product_Name,Product_Image,Product_Category,Product_Type,Product_Price,Unit,Stock);
					
					product = new UniqueProduct(Product_ID,Shop_ID,Product_Name,Product_Image,Product_Category,Product_Type,Product_Price,Unit,Stock);
					
					
					uniList.add(product);
					
					
				}
				
				
				// Product product = list;
				//return listProduct;
				return uniList;
			} else {
				log.debug("get successful,No Product List is there ");

			}
			return null;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */

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
	

}
