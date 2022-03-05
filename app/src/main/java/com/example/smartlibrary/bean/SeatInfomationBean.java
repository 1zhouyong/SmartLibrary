package com.example.smartlibrary.bean;

/**
 * author: ${周勇}
 * Date: 2020/4/14
 * Description:{}
 */
public class SeatInfomationBean {

    /**
     * success : true
     * result : {"id":4,"place":"一楼-101","number":"2排1列","status":"1","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null}
     * errorCode : null
     * errorMsg : null
     */

    private boolean success;
    private ResultBean result;
    private Object errorCode;
    private Object errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * id : 4
         * place : 一楼-101
         * number : 2排1列
         * status : 1
         * date : null
         * orderBy : null
         * deleteFlag : null
         * createdBy : null
         * createdAt : null
         * updatedBy : null
         * updatedAt : null
         */

        private int id;
        private String place;
        private String number;
        private String status;
        private Object date;
        private Object orderBy;
        private Object deleteFlag;
        private Object createdBy;
        private Object createdAt;
        private Object updatedBy;
        private Object updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(Object deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
