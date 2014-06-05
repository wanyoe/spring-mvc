package com.glp.admin.security;

public enum AdminPermission {
    NONE_PERMISSION(0),// 无权限
    ROLE_ADMIN(1), //管理员
    ROLE_USER(2), //一般用户
    ROLE_CLOSE(3);//关闭

    private Integer id;

    private AdminPermission(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
};