package com.glp.security;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.eyeieye.melody.web.cookyjar.SelfDependence;
import com.eyeieye.melody.web.cookyjar.util.SelfUtil;


/**
 * user information that's usually saved in the cookie.
 *
 */
public class UserAgent implements Serializable, SelfDependence {

	private static final long serialVersionUID = -3921778874150895446L;
	public static final String agentTag = "userAgent";

	private Long id;

	private String email;

	private String username;

	private BigInteger permissions;

    private String userStatus;    //用户状态

    private long refreshTime = 0;  //下一个更新时间点

    public UserAgent() {
    }

    public UserAgent(Long userId, String userName, String email) {
		this.id = userId;
		this.email = email;
		this.username = userName;
//		this.permissions = new BigInteger("0"); 
	}

	@Override
	public String lieDown() {
		return SelfUtil.format(
				this.getId().toString(), 
				this.getEmail(),
				this.getUsername(),
                this.getUserStatus(),
                this.getRefreshTime()+""
//				this.permissions.toString(36)
		);
	}

	@Override
	public SelfDependence riseUp(String value) {
		String[] values = SelfUtil.recover(value);
		this.setId(Long.parseLong(values[0]));
		this.setEmail(values[1]);
		this.setUsername(values[2]);
        if(values.length > 4) {
            this.userStatus = values[3];
            this.refreshTime = Long.parseLong(values[4]);
        }

//		this.setPermissions(new BigInteger(values[3]));
		return this;
	}


	public boolean havePermission(int index) {
		return this.permissions.testBit(index);
	}

	public boolean havePermission(Permission fe) {
		return havePermission(fe.getId());
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = new BigInteger("0");
		for (Permission en : permissions) {
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void updateRefreshTime(long time, TimeUnit unit) {
        this.refreshTime = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time,TimeUnit.MINUTES);

    }

    public boolean needRefresh(){
        return System.currentTimeMillis() > refreshTime;
    }

    public long getRefreshTime() {
        return refreshTime;
    }
}
