package com.glp.admin.query;

import java.util.HashMap;
import java.util.Map;

/**
 * user query
 * @author springpig
 * @date 2014-05-20
 */
public class QueryUser extends QueryBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9218935888440154710L;

	private String order;			// 排序
	private String keyword_type;	// 搜索关键字类型
	private String keyword;			// 搜索的关键字
	private String status;			// 状态
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKeyword_type() {
		return keyword_type;
	}
	public void setKeyword_type(String keyword_type) {
		this.keyword_type = keyword_type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Map<String, Object> getParameters() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order", order);
		param.put("keyword_type", keyword_type);
		param.put("keyword", keyword);
		param.put("status", status);
		return param;
	}
}
