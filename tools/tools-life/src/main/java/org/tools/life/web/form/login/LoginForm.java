package org.tools.life.web.form.login;

import org.tools.life.domain.base.BaseInfo;

public class LoginForm extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911560901710027566L;

	private String usercode;
	
	private String password;
	
	private String isRemem;

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

	public String getIsRemem() {
		return isRemem;
	}

	public void setIsRemem(String isRemem) {
		this.isRemem = isRemem;
	}
}
