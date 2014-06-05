package com.glp.admin.dwz;
import java.util.HashMap;
import java.util.Map;

/**
 *  "statusCode":"200", 

      "message":"²Ù×÷³É¹¦", 

      "navTabId":"", 

      "rel":"", 

      "callbackType":"closeCurrent",

      "forwardUrl":""
 * 
 * 
 * @author Administrator
 *
 */
public class DWZResponse {
	
	public static final String CODE_SUCCESS = "200";
	public static final String CODE_FAILUR = "300";
	public static final String CODE_TIMEOUT = "301";
	public static final String CLOSE_CURRENT = "closeCurrent";
	public static final String FORWARD = "forward";
	
	String statusCode;
	String message;
	String navTabId;
	String rel;
	String callbackType = CLOSE_CURRENT;
	String forwardUrl;		
	
	Map<String,Object> attr; 
	
	public DWZResponse(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public DWZResponse(String statusCode, String message, String callbackType) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.callbackType = callbackType;
	}
	
	public DWZResponse(String statusCode, String message, String navTabId,
			String rel, String callbackType, String forwardUrl) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
	}
	public DWZResponse() {
	}

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
	public Map<String, Object> getAttr() {
		return attr;
	}
	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}
	public void addAttr(String key , String value){
		if(attr == null) 
			attr = new HashMap<String, Object>();
		this.attr.put(key, value);
	}
}
