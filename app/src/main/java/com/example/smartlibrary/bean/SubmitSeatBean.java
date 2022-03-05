package com.example.smartlibrary.bean;

/**
 * author: ${周勇}
 * Date: 2020/4/13
 * Description:{}
 */
public class SubmitSeatBean {

    /**
     * success : true
     * result : true
     * errorCode : null
     * errorMsg : null
     */

    private boolean success;
    private boolean result;
    private Object errorCode;
    private Object errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }
}
