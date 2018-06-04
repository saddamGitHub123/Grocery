/**
 * 
 */
package com.backend.restapi.user.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sk Saddam Hosan
 *
 */
@Entity
@Table(name ="pushnotification_log")
public class Pushnotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129193332789919709L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Pushnotification_ID;
	private String Shop_ID;
	private String Device_ID;
	private boolean Is_Active;
	private String Message_Body;
	
	public Pushnotification() {
		
	}
	
	/*
	 * this constructor used for update the Device_ID
	 * **/
	
	
	/**
	 * @param shop_ID
	 * @param device_ID
	 * @param is_Active
	 *  
	 */
	public Pushnotification(String shop_ID, String device_ID, boolean is_Active) {
		super();
		Shop_ID = shop_ID;
		Device_ID = device_ID;
		Is_Active = is_Active;
	}




	/**
	 * @param pushnotification_ID
	 * @param shop_ID
	 * @param device_ID
	 * @param is_Active
	 * @param message_Body
	 */
	public Pushnotification(int pushnotification_ID, String shop_ID, String device_ID, boolean is_Active,
			String message_Body) {
		super();
		Pushnotification_ID = pushnotification_ID;
		Shop_ID = shop_ID;
		Device_ID = device_ID;
		Is_Active = is_Active;
		Message_Body = message_Body;
	}



	@Override
	public String toString() {
		return "Pushnotification [Pushnotification_ID=" + Pushnotification_ID + ", Shop_ID=" + Shop_ID + ", Device_ID="
				+ Device_ID + ", Is_Active=" + Is_Active + ", Message_Body=" + Message_Body + "]";
	}
	
	
	public int getPushnotification_ID() {
		return Pushnotification_ID;
	}
	public void setPushnotification_ID(int pushnotification_ID) {
		Pushnotification_ID = pushnotification_ID;
	}
	public String getShop_ID() {
		return Shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}
	public String getDevice_ID() {
		return Device_ID;
	}
	public void setDevice_ID(String device_ID) {
		Device_ID = device_ID;
	}
	public boolean isIs_Active() {
		return Is_Active;
	}
	public void setIs_Active(boolean is_Active) {
		Is_Active = is_Active;
	}
	public String getMessage_Body() {
		return Message_Body;
	}
	public void setMessage_Body(String message_Body) {
		Message_Body = message_Body;
	}
	
	
	
	
	


}
