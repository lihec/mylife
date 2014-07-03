package org.tools.life.domain.money;

import org.tools.life.domain.base.BaseInfo;

import java.math.BigDecimal;

/**
 * 交易流水入参
 * @author 李贺[of253]
 * @date 2014/6/30 20:22
 */
public class TransDetailsVo extends BaseInfo{

    private static final long serialVersionUID = -5628945196848232028L;
    /**
     * 收支类型
     */
    private int ptype;

    /**
     * 交易类型
     */
    private Integer type;
    /**
     * 交易类型名称
     */
    private String typeName;

    private String fromCard;

    private String toCard;

    private BigDecimal money;

    private BigDecimal withdrawFee;

    private String addtime;

    private String mark;

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
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
        this.typeName = typeName;
    }


    public String getFromCard() {
        return fromCard;
    }

    public void setFromCard(String fromCard) {
        this.fromCard = fromCard;
    }

    public String getToCard() {
        return toCard;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
