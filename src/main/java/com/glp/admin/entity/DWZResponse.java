package com.glp.admin.entity;


import java.util.HashMap;
import java.util.Map;

/**
 *  "statusCode":"200",

      "message":"操作成功",

      "navTabId":"",

      "rel":"",

      "callbackType":"closeCurrent",

      "forwardUrl":""
 * 服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容.
 * callbackType如果是closeCurrent就会关闭当前tab
 * 只有callbackType="forward"时需要forwardUrl值
 * form提交后返回json数据结构statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因.
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent", "rel"."xxxId"}
 * {"statusCode":"300", "message":"操作失败"}
 * {"statusCode":"301", "message":"会话超时"}
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

	public DWZResponse(String statusCode, String message, String callbackType,String navTabId) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.callbackType = callbackType;
		this.navTabId = navTabId;
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
