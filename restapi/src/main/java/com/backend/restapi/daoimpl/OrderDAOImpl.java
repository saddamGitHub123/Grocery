package com.backend.restapi.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.restapi.dao.OrderDAO;
import com.backend.restapi.order.dto.Order;
import com.backend.restapi.order.model.DispatchRequest;
import com.backend.restapi.order.model.OrderRequest;
import com.backend.restapi.order.model.OrderRequestAdd;
import com.backend.restapi.order.model.OrderSizeModel;
import com.backend.restapi.order.model.OrderSizeRequest;
import com.backend.restapi.order.model.Ordered_List;
import com.backend.restapi.product.dto.Price;
import com.backend.restapi.user.dto.Address;
import com.backend.restapi.user.dto.User;

/**
 * 
 * @author saddam
 *
 */
@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO {

	private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add order list from OrderControllr
	 **/

	@Override
	public boolean addOrder(OrderRequest orderRequest) {

		try {
			log.debug("Add all the order list in addOrder() method ");

			// int count = 0;

			Order order = new Order();

			// Get all the order list from OrderRequest class
			List<Order> orderList = orderRequest.getOrderList();

			// Add one by one order list using loop
			for (int count = 0; count < orderList.size(); count++) {

				order = orderList.get(count);
				// Set shopID and UserID in the order list class
				order.setShop_ID(orderRequest.getShop_ID());
				order.setUser_ID(orderRequest.getUser_ID());

				// timeStamp coming from user and set that timeStamp
				order.setCurrentTimestamp(orderRequest.getTimeStamp());
				// Save the order list
				sessionFactory.getCurrentSession().persist(order);
				// count++;
				// System.out.println(order);
			}
			log.debug(" Returnning from addOrder() method  ");
			return true;

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
		// return false;
	}

	/**
	 * Add order list from OrderControllr
	 **/

	@Override
	public String addOrderAndOrderID(OrderRequestAdd orderRequest) {

		try {
			log.debug("Add all the order list ");

			Order order = new Order();

			boolean dispatchValue = true;

			// Get all the order list from OrderRequest class
			List<Order> orderList = orderRequest.getOrderList();

			for (int count = 0; count < orderList.size(); count++) {

				order = orderList.get(count);
				// Set shopID and UserID in the order list class
				order.setShop_ID(orderRequest.getShop_ID());
				order.setUser_ID(orderRequest.getUser_ID());
				order.setOrder_ID(orderRequest.getOrder_ID());
				order.setDispatch(dispatchValue);
				order.setTotal_Amount(orderRequest.getTotal_Amount());
				order.setOrder_Active(true);

				System.out.println(orderRequest.getOrder_ID());

				// timeStamp coming from user and set that timeStamp
				order.setCurrentTimestamp(orderRequest.getTimeStamp());
				// Save the order list
				sessionFactory.getCurrentSession().persist(order);

				log.debug("Returing from the addOrderAndOrderID  method");
			}
			System.out.println("the list of order" + order);
			System.out.println(orderRequest.getOrder_ID());
			// sessionFactory.openSession().flush();
			// sessionFactory.openSession().beginTransaction().commit();
			return orderRequest.getOrder_ID();

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
		// return false;
	}

	/**
	 * Returning all user details and order details using by shopID
	 ***/

	@Override
	public List<Ordered_List> userOrderListByShopId(String Shop_ID, String User_ID1, boolean Dispatch) {

		try {
			log.debug("Entering userOrderListByShopId() - at OrderDAOImpl class ");

			Ordered_List orderedList;
			java.sql.Timestamp timeStamp;
			String orderID;
			double Total_Amount;

			// int count = 0;
			List<Ordered_List> orderAddList = new ArrayList<>();
			Address address = new Address();

			// This list of array and set are using getting unique list from database using
			// hasCode() and
			// equeals() in the Order model class

			List<Order> userList = new ArrayList<>();
			Set<Order> uniqueSet = new HashSet<>();

			// getting userID List from user Table

			String selectUserByShopId = null;

			// getting unique order_id from order table
			if (Dispatch == true && User_ID1 == null) {

				// this is return only active orderList
				selectUserByShopId = "from Order where Shop_ID = :Shop_ID AND Dispatch = :Dispatch ";

			} else if (Dispatch == false && User_ID1 == null) {
				// this is return dispatch orderList
				selectUserByShopId = "from Order where Shop_ID = :Shop_ID AND Dispatch = :Dispatch ";
			} else {

				// this is for particular user order details
				String User_ID = User_ID1;
				selectUserByShopId = "from Order where Shop_ID = :Shop_ID AND User_ID = :User_ID";

				List<Order> allOrderList = sessionFactory.getCurrentSession()
						.createQuery(selectUserByShopId, Order.class).setParameter("Shop_ID", Shop_ID)
						.setParameter("User_ID", User_ID).getResultList();

				for (Order obj : allOrderList) {
					if (uniqueSet.add(obj)) {
						userList.add(obj);
					}
				}

				if (userList.isEmpty()) {

					log.debug("Returrning empty order list");
					return orderAddList;
				}

				// for (Order entity : userList) {

				for (int count = 0; count < userList.size(); count++) {
					// getting userID from user table using shopID
					User_ID = userList.get(count).getUser_ID();
					String Order_ID = userList.get(count).getOrder_ID();

					// getting list of order using userID and orderID
					String selectOrderByUserID = "from Order where User_ID = :User_ID AND Order_ID = :Order_ID ";

					List<Order> orderList = sessionFactory.getCurrentSession()
							.createQuery(selectOrderByUserID, Order.class).setParameter("User_ID", User_ID)
							.setParameter("Order_ID", Order_ID).getResultList();

					// set timeStamp and OrderID
					if ((orderList != null) && (orderList.size() > 0)) {
						timeStamp = orderList.get(0).getCurrentTimestamp();
						orderID = orderList.get(0).getOrder_ID();
						Total_Amount = orderList.get(0).getTotal_Amount();

					} else {
						timeStamp = null;
						orderID = null;
						Total_Amount = 0;
					}

					// getting Address using userID
					String selectAddressByUserID = "from Address where User_ID = :User_ID AND Shop_ID = :Shop_ID";

					List<Address> addressList = sessionFactory.getCurrentSession()
							.createQuery(selectAddressByUserID, Address.class).setParameter("User_ID", User_ID)
							.setParameter("Shop_ID", Shop_ID).getResultList();

					// convert address list to object
					if ((addressList != null) && (addressList.size() > 0)) {
						address = addressList.get(0);

					} else {
						address = null;
					}

					orderedList = new Ordered_List(User_ID, address, orderList, timeStamp, orderID, Total_Amount);
					orderAddList.add(orderedList);
					// count++;
				}
				log.debug("Returrning order list");

				return orderAddList;
			}

			// if dispatch will come true or false then run this part

			List<Order> allOrderList = sessionFactory.getCurrentSession().createQuery(selectUserByShopId, Order.class)
					.setParameter("Shop_ID", Shop_ID).setParameter("Dispatch", Dispatch).getResultList();
			/**
			 * It is used for getting unique list from List of object Using hashCode() and
			 * equals()
			 * 
			 */
			for (Order obj : allOrderList) {
				if (uniqueSet.add(obj)) {
					userList.add(obj);
				}
			}

			// System.out.println(userList);

			for (int count = 0; count < userList.size(); count++) {
				// getting userID from user table using shopID
				String User_ID = userList.get(count).getUser_ID();
				String Order_ID = userList.get(count).getOrder_ID();

				// getting list of order using userID and orderID
				String selectOrderByUserID = "from Order where User_ID = :User_ID AND Order_ID = :Order_ID ";

				List<Order> orderList = sessionFactory.getCurrentSession().createQuery(selectOrderByUserID, Order.class)
						.setParameter("User_ID", User_ID).setParameter("Order_ID", Order_ID).getResultList();

				// set timeStamp and OrderID
				if ((orderList != null) && (orderList.size() > 0)) {
					timeStamp = orderList.get(0).getCurrentTimestamp();
					orderID = orderList.get(0).getOrder_ID();
					Total_Amount = orderList.get(0).getTotal_Amount();
				} else {
					timeStamp = null;
					orderID = null;
					Total_Amount = 0;
				}

				// getting Address using userID
				String selectAddressByUserID = "from Address where User_ID = :User_ID AND Shop_ID = :Shop_ID";

				List<Address> addressList = sessionFactory.getCurrentSession()
						.createQuery(selectAddressByUserID, Address.class).setParameter("User_ID", User_ID)
						.setParameter("Shop_ID", Shop_ID).getResultList();

				// convert address list to object
				if ((addressList != null) && (addressList.size() > 0)) {
					address = addressList.get(0);

				} else {
					address = null;
				}

				orderedList = new Ordered_List(User_ID, address, orderList, timeStamp, orderID, Total_Amount);
				orderAddList.add(orderedList);
				// count++;
			}

			log.debug("Returrning order list");

			// System.out.println(orderedList);

			return orderAddList;
		} catch (RuntimeException re) {
			log.error("Returrning order list failed", re);
			throw re;
		}

	}

	/*
	 * Order Dispatch method for dispatch the orderList
	 **/

	@Override
	public boolean orderDispatchOrderID(DispatchRequest dispatchRequest) {

		String Order_ID = dispatchRequest.getOrder_ID();
		String Shop_ID = dispatchRequest.getShop_ID();
		boolean Dispatch = dispatchRequest.isDispatch();

		try {
			log.debug("Entering orderDispatchOrderID() - at OrderDAOImpl class ");

			// getting list of order using shopID and orderID
			String selectOrderByOrderID = "from Order where Shop_ID = :Shop_ID AND Order_ID = :Order_ID ";

			List<Order> orderList = sessionFactory.getCurrentSession().createQuery(selectOrderByOrderID, Order.class)
					.setParameter("Shop_ID", Shop_ID).setParameter("Order_ID", Order_ID).getResultList();

			// get one by one orderlist

			System.out.println(orderList.size());
			for (int i = 0; i < orderList.size(); i++) {
				// Price pri;
				// Set list of value
				// Shop_ID = orderList.get(i).getShop_ID();
				String Product_ID = orderList.get(i).getProduct_ID();
				String Price = orderList.get(i).getPrice();
				String qty = orderList.get(i).getQty();
				String unit = orderList.get(i).getUnits();
				// String Stock = qty * unit;
				int integerQty = Integer.parseInt(qty);
				int integerUnit = Integer.parseInt(unit);
				int totalStock = integerQty * integerUnit;

				// update dispatch value using productID,shopID and orderID and price

				String updateDispatch = "UPDATE Order SET Dispatch = :Dispatch WHERE Product_ID = :Product_ID AND Order_ID = :Order_ID AND Price =:Price";

				int updatedDispatch = sessionFactory.getCurrentSession().createQuery(updateDispatch)
						.setParameter("Dispatch", Dispatch).setParameter("Price", Price)
						.setParameter("Order_ID", Order_ID).setParameter("Product_ID", Product_ID).executeUpdate();
				System.out.println("Dispatch update" + updatedDispatch);

				// list of price using shopID and price and productID

				String selectOrderByPrice = "from Price where Shop_ID = :Shop_ID AND Product_ID =:Product_ID AND Price =:Price ";

				// Getting single price ,quty and stock
				List<Price> priceList = sessionFactory.getCurrentSession().createQuery(selectOrderByPrice, Price.class)
						.setParameter("Shop_ID", Shop_ID).setParameter("Product_ID", Product_ID)
						.setParameter("Price", Price).getResultList();

				// getting that particular qty_price and id
				String Qty_Price = priceList.get(0).getQty_Price();
				int ID = priceList.get(0).getID();
				// convert stock price string to integer
				int requestStockFromUser = Integer.parseInt(priceList.get(0).getStock());

				// Check stock is empty or less from order
				if (requestStockFromUser > totalStock) {
					int remainingStock = (Integer.parseInt(priceList.get(0).getStock()) - totalStock);
					String Stock = String.valueOf(remainingStock);

					// If qty_price is empty or not
					if (Qty_Price != null) {

						// Qty_Price not empty
						// update particular column using ID and productID and shopID
						String updateSingleValu = "UPDATE Price SET Price = :Price , Qty_Price =:Qty_Price , Stock =:Stock WHERE ID = :ID AND Product_ID = :Product_ID";

						// set the data base value using hibernate query
						int updatedEntities = sessionFactory.getCurrentSession().createQuery(updateSingleValu)
								.setParameter("ID", ID).setParameter("Price", Price)
								.setParameter("Qty_Price", Qty_Price).setParameter("Stock", Stock)
								.setParameter("Product_ID", Product_ID).executeUpdate();

						System.out.println("Price update " + updatedEntities);

					} else {
						// pri = new Price(Product_ID,Shop_ID,Price, Stock);
						System.out.println(Stock);

						// If qty_price is empty
						// update particular column using ID and productID and shopID
						String updateSingleValu = "UPDATE Price SET Price = :Price, Stock =:Stock WHERE ID = :ID AND Product_ID = :Product_ID";

						// set the data base value usign hibernat query
						int updatedEntities = sessionFactory.getCurrentSession().createQuery(updateSingleValu)
								.setParameter("ID", ID).setParameter("Price", Price)
								// .setParameter( "Qty_Price", Qty_Price )
								.setParameter("Stock", Stock).setParameter("Product_ID", Product_ID).executeUpdate();

						System.out.println("Price update" + updatedEntities);

					}

					// return true;
				} else {
					System.out.println("Stock is not avalible");
					return false;
				}

			}

			return true;
		} catch (RuntimeException re) {
			log.error("Returrning order list failed", re);
			throw re;
		}

	}

	/**
	 * for mobile app returring orderList for orderID ,order size,total amount and
	 * timeStamp
	 */

	@Override
	public List<OrderSizeModel> orderSizeList(OrderSizeRequest orderSizeRequest) {

		String Shop_ID = orderSizeRequest.getShop_ID();
		String User_ID = orderSizeRequest.getUser_ID();

		List<OrderSizeModel> orderAddList = new ArrayList<>();

		// create new order list and order set for getting unique order list
		List<Order> userList = new ArrayList<>();
		Set<Order> userSet = new HashSet<>();

		OrderSizeModel orderSizeModel = null;

		try {
			log.debug("Add all the order list ");

			// getting unique orderList using shopid and userid
			String selectUserByShopId = "from Order where Shop_ID = :Shop_ID AND User_ID = :User_ID ";

			List<Order> AllUserList = sessionFactory.getCurrentSession().createQuery(selectUserByShopId, Order.class)
					.setParameter("Shop_ID", Shop_ID).setParameter("User_ID", User_ID).getResultList();

			// getting unique orderlist from AllUserList
			// Using hasHCode() and equals()
			for (Order obj : AllUserList) {
				if (userSet.add(obj)) {
					userList.add(obj);
				}
			}

			System.out.println(userList.size());

			// se
			for (int i = 0; i < userList.size(); i++) {

				// int listSize;

				String Order_ID = userList.get(i).getOrder_ID();

				double Total_Amount = userList.get(i).getTotal_Amount();

				java.sql.Timestamp TimeStamp = userList.get(i).getCurrentTimestamp();

				// getting list of order using shopID and orderID
				String selectOrderByOrderID = "from Order where Shop_ID = :Shop_ID AND Order_ID = :Order_ID ";

				List<Order> orderList = sessionFactory.getCurrentSession()
						.createQuery(selectOrderByOrderID, Order.class).setParameter("Shop_ID", Shop_ID)
						.setParameter("Order_ID", Order_ID).getResultList();

				int listSize = orderList.size();
				orderSizeModel = new OrderSizeModel(listSize, Order_ID, Total_Amount, TimeStamp);

				orderAddList.add(orderSizeModel);

			}

			return orderAddList;

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
	}

	/**
	 * Greeting orderList using orderID for mobile application
	 **/

	@Override
	public List<Order> orderList(OrderSizeRequest orderSizeReques) {
		try {
			log.debug("Add all the order list ");

			// int count = 0;

			String Shop_ID = orderSizeReques.getShop_ID();
			String Order_ID = orderSizeReques.getOrder_ID();
			String User_ID = orderSizeReques.getUser_ID();

			// Order order = new Order();

			// getting list of order using shopID and orderID
			String selectOrderByOrderID = "from Order where User_ID = :User_ID AND Shop_ID = :Shop_ID AND Order_ID = :Order_ID ";

			List<Order> orderList = sessionFactory.getCurrentSession().createQuery(selectOrderByOrderID, Order.class)
					.setParameter("Shop_ID", Shop_ID).setParameter("Order_ID", Order_ID)
					.setParameter("User_ID", User_ID).getResultList();

			return orderList;

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
	}

	@Override
	public List<User> userPhoneNumber(DispatchRequest dispatchRequest) {
		try {
			log.debug("getting user deatails using shopid and userid ");

			String Shop_ID = dispatchRequest.getShop_ID();
			String User_ID = dispatchRequest.getUser_ID();

			String selectOrderByOrderID = "from User where User_ID = :User_ID AND Shop_ID = :Shop_ID AND is_active = :is_active";

			List<User> listUser = sessionFactory.getCurrentSession().createQuery(selectOrderByOrderID, User.class)
					.setParameter("Shop_ID", Shop_ID).setParameter("is_active", true).setParameter("User_ID", User_ID)
					.getResultList();

			return listUser;

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.backend.restapi.dao.OrderDAO#listOfOrder()
	 */
	@Override
	public List<Order> listOfOrder() {

		List<Order> uniqueListOrder = new ArrayList<>();
		Set<Order> uniqueSet = new HashSet<>();
		log.debug("Enterring the listofOrder() method in OrderDAOImpl");
		try {
			String orderListQuery = "From Order";
			List<Order> orderList = sessionFactory.getCurrentSession().createQuery(orderListQuery, Order.class)
					.getResultList();
			if (orderList != null && orderList.size() > 0) {
				for (Order obj : orderList) {
					if (uniqueSet.add(obj)) {
						uniqueListOrder.add(obj);
					}
				}

				return uniqueListOrder;
			} else {
				return null;
			}

		} catch (RuntimeException re) {
			log.error("Save product failed", re);
			throw re;
		}
	}

}
