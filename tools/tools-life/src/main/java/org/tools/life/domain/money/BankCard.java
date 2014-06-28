package org.tools.life.domain.money;

import java.math.BigDecimal;

import org.tools.life.domain.base.BaseInfo;

public class BankCard extends BaseInfo{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3483310694909775354L;

	private Integer id;

    private String bankName;

    private Integer type;

    private String bankNum;

    private String owner;

    private String netName;

    private String netPwd;

    private String udPwd;

    private Integer billDay;

    private Integer payDay;

    private String validDate;

    private String safeCode;

    private String creditAply;

    private BigDecimal balance;

    private Integer fixCredit;

    private BigDecimal availableCredit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName == null ? null : netName.trim();
    }

    public String getNetPwd() {
        return netPwd;
    }

    public void setNetPwd(String netPwd) {
        this.netPwd = netPwd == null ? null : netPwd.trim();
    }


    public String getUdPwd() {
		return udPwd;
	}

	public void setUdPwd(String udPwd) {
		this.udPwd = udPwd;
	}

	public Integer getBillDay() {
        return billDay;
    }

    public void setBillDay(Integer billDay) {
        this.billDay = billDay;
    }

    public Integer getPayDay() {
        return payDay;
    }

    public void setPayDay(Integer payDay) {
        this.payDay = payDay;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate == null ? null : validDate.trim();
    }

    public String getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode == null ? null : safeCode.trim();
    }

    public String getCreditAply() {
        return creditAply;
    }

    public void setCreditAply(String creditAply) {
        this.creditAply = creditAply == null ? null : creditAply.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getFixCredit() {
        return fixCredit;
    }

    public void setFixCredit(Integer fixCredit) {
        this.fixCredit = fixCredit;
    }

    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }
}