/**
 * 
 */
package com.backend.restapi.common;

/**
 * @author Sk Saddam Hosan
 *
 */
public class SuccResponse {
	
	private String status_code;
	private String status_message;
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuccResponse [status_code=" + status_code + ", status_message=" + status_message + "]";
	}
	/**
	 * @return the status_code
	 */
	public String getStatus_code() {
		return status_code;
	}
	/**
	 * @param status_code the status_code to set
	 */
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	/**
	 * @return the status_message
	 */
	public String getStatus_message() {
		return status_message;
	}
	/**
	 * @param status_message the status_message to set
	 */
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	
	

}
