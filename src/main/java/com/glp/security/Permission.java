package com.glp.security;

public enum Permission {
	NONE_PERMISSION(0),// 无权限 
	USER_INACTIVE(1), //注册未激活
	USER_ACTIVE(2), //正常
	USER_CLOSE(3);//关闭
	
	private Integer id;

	private Permission(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
};