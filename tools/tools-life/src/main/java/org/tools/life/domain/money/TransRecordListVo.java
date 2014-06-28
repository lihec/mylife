package org.tools.life.domain.money;

import org.tools.life.domain.base.PageableVO;

public class TransRecordListVo extends PageableVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631388275457472722L;

	private int type;

	private String fromCard;

	private String toCard;

	private String starttime;

	private String endtime;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getFromCard() {
		return fromCard;
	}

	public void setFromCard(String fromCard) {
		this.fromCard = fromCard;
	}


}
