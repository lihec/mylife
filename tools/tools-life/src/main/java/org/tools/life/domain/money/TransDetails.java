package org.tools.life.domain.money;

import org.tools.life.domain.base.BaseInfo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 交易流水
 * @author lihe
 *
 */
public class TransDetails extends BaseInfo{

    private static final long serialVersionUID = -1539432201040047480L;
    private String tid;

    private Integer type;

    private String typeName;

    private String fromCard;

    private String toCard;

    private BigDecimal money;
    /**
     * 提现手续费
     */
    private BigDecimal withdrawFee;

    private Date addtime;

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getAddtime() {
        return (Date) addtime.clone();
    }

    public void setAddtime(Date addtime) {
        this.addtime = (Date) addtime.clone();
    }

	public String getFromCard() {
		return fromCard;
	}

	public void setFromCard(String fromCard) {
		this.fromCard = fromCard;
	}

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }
}