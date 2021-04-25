package com.longsheng.trader;

public class ErrorInfo {

	private int errorCode;
	private String errMsg;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public ErrorInfo() {

	}

	public ErrorInfo(int errorCode, String errMsg) {
		this.errorCode = errorCode;
		this.errMsg = errMsg;
	}
}
