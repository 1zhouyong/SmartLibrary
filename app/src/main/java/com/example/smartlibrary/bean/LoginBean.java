package com.example.smartlibrary.bean;

/**
 * author: ${周勇}
 * Date: 2020/4/6
 * Description:{}
 */
public class LoginBean {

    /**
     * success : true
     * result : eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyaWQiOiIxOTA0MDExMDExIiwibG9naW5fdXNlcl9rZXkiOiJmOTk5YTM1Ni02MzllLTQ3MmYtOTdlOS00ZWZmOGY3NmFmMmYifQ.hIU9ecN2W_e_nQsOo3nRbgndd29WDtAaAJC4GZcpFV28i-Y4KIogumObZdqRf5NjW2SNma6Bh8ftt8kPA87-nw
     * errorCode : null
     * errorMsg : null
     */

    private boolean success;
    private String result;
    private Object errorCode;
    private Object errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
