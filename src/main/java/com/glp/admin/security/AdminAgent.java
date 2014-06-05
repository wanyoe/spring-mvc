package com.glp.admin.security;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.eyeieye.melody.web.cookyjar.SelfDependence;
import com.eyeieye.melody.web.cookyjar.util.SelfUtil;


/**
 * user information that's usually saved in the cookie.
 *
 */
public class AdminAgent implements Serializable, SelfDependence {

	private static final long serialVersionUID = -3921778874150895446L;
	public static final String agentTag = "adminAgent";

	private Long id;

	private String email;

	private String username;

	private BigInteger permissions;

	public AdminAgent() {
		super();
	}

	public AdminAgent(Long userId, String userName ,String email) {
		this.id = userId;
		this.email = email;
		this.username = userName;
		this.permissions = new BigInteger("0"); 
	}

	@Override
	public String lieDown() {
		return SelfUtil.format(
				this.getId().toString(), 
				this.getEmail(),
				this.getUsername(), 
				this.permissions.toString(36)
		);
	}

	@Override
	public SelfDependence riseUp(String value) {
		String[] values = SelfUtil.recover(value);
		this.setId(Long.parseLong(values[0]));
		this.setEmail(values[1]);
		this.setUsername(values[2]);
		this.setPermissions(new BigInteger(values[3]));
		return this;
	}


	public boolean havePermission(int index) {
		return this.permissions.testBit(index);
	}

	public boolean havePermission(AdminPermission fe) {
		return havePermission(fe.getId());
	}

	public void setPermissions(List<AdminPermission> permissions) {
		this.permissions = new BigInteger("0");
		for (AdminPermission en : permissions) {
			this.permissions = this.permissions.setBit(en.getId());
		}
	}

	public void setPermissions(int pos) {
		if (this.permissions == null) {
			this.permissions = new BigInteger("0");
		}
		this.permissions = this.permissions.setBit(pos);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigInteger getPermissions() {
		return permissions;
	}

	public void setPermissions(BigInteger functions) {
		this.permissions = functions;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}
}
