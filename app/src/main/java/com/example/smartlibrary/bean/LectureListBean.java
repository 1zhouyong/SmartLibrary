package com.example.smartlibrary.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/18
 * Description:{}
 */
public class LectureListBean {

    /**
     * success : true
     * result : [{"id":1,"name":"大学生心理素质讲座","descb":"大学生心理素质教育目的是\u2026\u2026","teacher":"张三","location":"1栋-201","beginTime":"2020-04-18T13:03:42.000+0800","endTime":"2020-04-18T15:03:46.000+0800","orderNumber":1,"sumNumber":90},{"id":2,"name":"大学生就业讲座","descb":"从学校走向社会，是一个全新的开始，这次讲座给","teacher":"李四","location":"3栋-103","beginTime":"2020-04-18T13:12:03.000+0800","endTime":"2020-04-18T15:12:12.000+0800","orderNumber":0,"sumNumber":90}]
     * errorCode : null
     * errorMsg : null
     */

    private boolean success;
    private Object errorCode;
    private Object errorMsg;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 1
         * name : 大学生心理素质讲座
         * descb : 大学生心理素质教育目的是……
         * teacher : 张三
         * location : 1栋-201
         * beginTime : 2020-04-18T13:03:42.000+0800
         * endTime : 2020-04-18T15:03:46.000+0800
         * orderNumber : 1
         * sumNumber : 90
         */

        private int id;
        private String name;
        private String descb;
        private String teacher;
        private String location;
        private Date beginTime;
        private Date endTime;
        private int orderNumber;
        private int sumNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescb() {
            return descb;
        }

        public void setDescb(String descb) {
            this.descb = descb;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Date getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Date beginTime) {
            this.beginTime = beginTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getSumNumber() {
            return sumNumber;
        }

        public void setSumNumber(int sumNumber) {
            this.sumNumber = sumNumber;
        }
    }
}
