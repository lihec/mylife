package com.life.jfx.pojo.account;

import com.life.jfx.pojo.base.BaseInfo;

public class Account extends BaseInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3882338911817280936L;

	private Integer id;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 网址
	 */
	private String url;
	/**
	 * 账号
	 */
	private String aid;
	/**
	 * 密码信息
	 */
	private String apwd;
	/**
	 * 备注
	 */
	private String mark;

	/**
	 * 添加时间
	 */
	private String addtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
