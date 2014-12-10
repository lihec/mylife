package com.life.jfx.pojo.account;

import com.life.jfx.pojo.base.BaseInfo;

public class QueryAccountList extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1087940946300545084L;
	
	/**
	 * 类型
	 */
	private Short type;
	
	/**
	 * 页索引
	 */
	private int pageno;
	
	/**
	 * 分页大小
	 */
	private int pagesize;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 账号
	 */
	private String aid;

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
	
}
