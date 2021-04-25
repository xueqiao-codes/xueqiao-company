package com.longsheng.trader;


public class BaseResult {
    private ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public BaseResult setErrorCode(int errorCode) {
        if (errorInfo == null) {
            errorInfo = new ErrorInfo();
        }
        errorInfo.setErrorCode(errorCode);
        return this;
    }

    public BaseResult setErrorMessage(String errorMessage) {
        if (errorInfo == null) {
            errorInfo = new ErrorInfo();
        }
        errorInfo.setErrMsg(errorMessage);
        return this;
    }
}
