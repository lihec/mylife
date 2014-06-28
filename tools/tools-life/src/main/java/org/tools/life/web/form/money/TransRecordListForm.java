package org.tools.life.web.form.money;

import org.tools.life.domain.base.BaseInfo;

public class TransRecordListForm extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014247497426298220L;

	private String type;

	private String formCard;

	private String toCard;

	private String starttime;

	private String endtime;

	public String getFormCard() {
		return formCard;
	}

	public void setFormCard(String formCard) {
		this.formCard = formCard;
	}

	public String getToCard() {
		return toCard;
	}

	public void setToCard(String toCard) {
		this.toCard = toCard;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
