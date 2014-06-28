package org.tools.life.domain.money;

import org.tools.life.domain.base.PageableVO;

public class CardListVo extends PageableVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3640784725669843484L;

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
