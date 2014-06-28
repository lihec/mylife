package com.qianmi.boss.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TransDetails {
    private String tid;

    private Integer type;

    private String typeName;

    private String formCard;

    private String toCard;

    private BigDecimal money;

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

    public String getFormCard() {
        return formCard;
    }

    public void setFormCard(String formCard) {
        this.formCard = formCard == null ? null : formCard.trim();
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
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}