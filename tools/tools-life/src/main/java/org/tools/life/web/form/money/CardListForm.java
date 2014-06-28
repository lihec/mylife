package org.tools.life.web.form.money;

import org.tools.life.domain.base.BaseInfo;

public class CardListForm extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4456995288287094469L;
	
	private String bankName;
	
	private String bankNum;
	
	private String type;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
