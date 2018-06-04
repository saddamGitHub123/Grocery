/**
 * 
 */
package com.backend.restapi.order.dto;

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
@Table(name = "groupby")
public class GroupBy {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int ID; 
		//private String code;
	    private String Name;
	    private String User_ID;
	    private String L_Name;
	    
	    
		@Override
		public String toString() {
			return "GroupBy [ID=" + ID + ", Name=" + Name + ", User_ID=" + User_ID + ", L_Name=" + L_Name + "]";
		}
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getUser_ID() {
			return User_ID;
		}
		public void setUser_ID(String user_ID) {
			User_ID = user_ID;
		}
		public String getL_Name() {
			return L_Name;
		}
		public void setL_Name(String l_Name) {
			L_Name = l_Name;
		}
	    
	    
	    


}
