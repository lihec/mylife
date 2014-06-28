package org.tools.life.domain.user;

import org.tools.life.domain.base.BaseInfo;

public class User extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5211049364000173414L;
	
	/**
	 * 用户编号
	 */
	private String usercode;
	
	/**
	 * 用户密码
	 */
	private String password;

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
