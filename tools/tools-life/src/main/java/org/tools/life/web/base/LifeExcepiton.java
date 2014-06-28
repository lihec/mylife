package org.tools.life.web.base;

public class LifeExcepiton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6295534666540418073L;

	private int errorCode;

	private String errorMsg;

	public LifeExcepiton() {
		super();
	}

	public LifeExcepiton(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public LifeExcepiton(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorMsg = errorMsg;
	}

	public LifeExcepiton(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public LifeExcepiton(int errorCode, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
