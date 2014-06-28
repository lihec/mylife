package org.tools.life.domain.money;

import org.tools.life.domain.base.BaseInfo;

import java.math.BigDecimal;

/**
 * 资产负债统计
 * 
 * @author lihe
 * 
 */
public class FinanceView extends BaseInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6078090975450746555L;
	/**
	 * 净资产
	 */
	private BigDecimal netAssets;
	/**
	 * 资产总额
	 */
	private BigDecimal totalAssets;
	/**
	 * 储蓄余额
	 */
	private BigDecimal bankAssets;
	/**
	 * 其他资产余额
	 */
	private BigDecimal otherAssets;

	/**
	 * 信用卡欠款总额
	 */
	private BigDecimal creditCardDebt;

	/**
	 * 总额度
	 */
	private int creditCardLimit;

	/**
	 * 可用额度
	 */
	private BigDecimal avlCreditCardLimit;

    public BigDecimal getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(BigDecimal netAssets) {
        this.netAssets = netAssets;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getBankAssets() {
        return bankAssets;
    }

    public void setBankAssets(BigDecimal bankAssets) {
        this.bankAssets = bankAssets;
    }

    public BigDecimal getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(BigDecimal otherAssets) {
        this.otherAssets = otherAssets;
    }

    public BigDecimal getCreditCardDebt() {
        return creditCardDebt;
    }

    public void setCreditCardDebt(BigDecimal creditCardDebt) {
        this.creditCardDebt = creditCardDebt;
    }

    public int getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(int creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public BigDecimal getAvlCreditCardLimit() {
        return avlCreditCardLimit;
    }

    public void setAvlCreditCardLimit(BigDecimal avlCreditCardLimit) {
        this.avlCreditCardLimit = avlCreditCardLimit;
    }
}
