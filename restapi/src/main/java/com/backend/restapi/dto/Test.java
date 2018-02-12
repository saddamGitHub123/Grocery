/**
 * 
 */
package com.backend.restapi.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sk Saddam Hosan
 *
 */

//@Entity
//@Table(name = "test")
public class Test {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer test_ID;
	private String name;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Test [test_ID=" + test_ID + ", name=" + name + "]";
	}
	
	public Test() {}
	
	/**
	 * @param test_ID
	 * @param name
	 */
	public Test(int test_ID, String name) {
		super();
		this.test_ID = test_ID;
		this.name = name;
	}
	/**
	 * @return the test_ID
	 */
	public int getTest_ID() {
		return test_ID;
	}
	/**
	 * @param test_ID the test_ID to set
	 */
	public void setTest_ID(int test_ID) {
		this.test_ID = test_ID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
