package org.tools.life.domain.money;

import org.tools.life.domain.base.BaseInfo;

import java.util.List;

/**
 * 收支类型
 * @author lihe
 *
 */
public class PaymentType extends BaseInfo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4027312259029066948L;

	private Integer pid;

    private Integer ptype;

    private String pname;

    private Integer topPid;

    private String mark;

    private List<PaymentType> childPayments;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getTopPid() {
        return topPid;
    }

    public void setTopPid(Integer topPid) {
        this.topPid = topPid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public List<PaymentType> getChildPayments() {
        return childPayments;
    }

    public void setChildPayments(List<PaymentType> childPayments) {
        this.childPayments = childPayments;
    }
}