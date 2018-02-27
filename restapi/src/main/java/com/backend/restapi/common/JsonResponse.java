package com.backend.restapi.common;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class JsonResponse {
    @JsonIgnore
    public static final String CODE__OK = "200";
    @JsonIgnore
    public static final String CODE__BLOCKED = "BLOCKED";
    @JsonIgnore
    public static final String CODE__NOT_BLOCKED = "NOT_BLOCKED";
    @JsonIgnore
    public static final String CODE__ERROR = "ERROR";
    @JsonIgnore
    public static final String CODE__EMPTY = "400";
    
    @JsonIgnore
    public static final String CODE__EXCEPTION = "Exception";
    @JsonIgnore
    public static final String CODE__INVALID_REQUEST = "INVALID_REQUEST";
    @JsonIgnore
    public static final String CODE__REQUEST_DENIED = "REQUEST_DENIED";
    @JsonIgnore
    public static final String CODE__UNKNOWN_ERROR = "UNKNOWN_ERROR";
    
    @JsonIgnore
    public static final String CODE__SUCCESS = "Your Order is Placed Successfully";
    public static final String DISPATCH__SUCCESS = "Your Order is Dispatch Successfully";
    
    
    public static final String LIST__ERRORE = "List is Empty";
    

    
    private String code;
    private List<String> messages;

    public JsonResponse() {
        super();
    }

    public JsonResponse(String code, List<String> messages) {
        super();
        this.code = code;
        this.messages = messages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addMessage(String message) {
        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

	@Override
	public String toString() {
		return "JsonResponse [code=" + code + ", messages=" + messages + "]";
	}
    
	

 
}
