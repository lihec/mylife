package org.tools.util.mail;

public enum MailConstants {
	smtp163("smtp.163.com", 25, "13776607509@163.com", "Lihe841009", "贺");

	private String hostname;

	private int port;

	private String formAddress;

	private String pwd;

	private String username;

	private MailConstants(String hostname, int port, String formAddress,
			String pwd, String username) {
		this.hostname = hostname;
		this.port = port;
		this.formAddress = formAddress;
		this.pwd = pwd;
		this.username = username;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFormAddress() {
		return formAddress;
	}

	public void setFormAddress(String formAddress) {
		this.formAddress = formAddress;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
