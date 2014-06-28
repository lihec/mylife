package org.tools.life.domain.money;

import org.tools.life.domain.base.BaseInfo;

/**
 * 交易流水
 * @author lihe
 *
 */
public class TransDetailsBo extends BaseInfo{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7799219618762288687L;

	private String tid;
	
	private Integer toptype;

    private Integer type;

    private String typeName;

    private String fromCard;

    private String toCard;

    private String money;

    private String addtime;
    
    private String mark;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }


    public String getToCard() {
        return toCard;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard == null ? null : toCard.trim();
    }

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getFromCard() {
		return fromCard;
	}

	public void setFromCard(String fromCard) {
		this.fromCard = fromCard;
	}

	public Integer getToptype() {
		return toptype;
	}

	public void setToptype(Integer toptype) {
		this.toptype = toptype;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}


}