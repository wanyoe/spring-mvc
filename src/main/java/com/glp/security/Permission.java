package com.glp.security;

public enum Permission {
	NONE_PERMISSION(0),// ��Ȩ�� 
	USER_INACTIVE(1), //ע��δ����
	USER_ACTIVE(2), //����
	USER_CLOSE(3);//�ر�
	
	private Integer id;

	private Permission(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
};