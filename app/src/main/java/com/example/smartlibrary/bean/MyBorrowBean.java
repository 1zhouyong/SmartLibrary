package com.example.smartlibrary.bean;

import java.util.Date;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public class MyBorrowBean {

    /**
     * success : true
     * result : [{"id":2,"bookNumber":"9787302070269","name":"程序开发心理学1","descb":"\u201c明白自己在做什么\u201d，是走向成功的必要条件，大部分成功者靠的一半天分，一半勤奋。本书就来从心理学的视角来了解一个程序开发，如何趋利避害\u2026\u2026","author":"杰拉尔德.温伯格","publishAddress":"清华大学出版社","publishTime":"2019-09-03T13:57:08.000+0800","bookType":"3","status":"2","score":4,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\1.png","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\xinshu\\程序开发心理学.pdf","createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null,"borrowingTime":"2020-05-01T00:00:00.000+0800","preBackTime":"2020-05-05T00:00:00.000+0800","actualBackTime":null,"stop":"0"},{"id":3,"bookNumber":"9787302070269","name":"程序开发心理学2","descb":"\u201c明白自己在做什么\u201d，是走向成功的必要条件，大部分成功者靠的一半天分，一半勤奋。本书就来从心理学的视角来了解一个程序开发，如何趋利避害\u2026\u2026","author":"杰拉尔德.温伯格","publishAddress":"清华大学出版社","publishTime":"2019-09-03T13:57:08.000+0800","bookType":"3","status":"2","score":4,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\1.png","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\xinshu\\程序开发心理学.pdf","createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null,"borrowingTime":"2020-05-01T00:00:00.000+0800","preBackTime":"2020-04-05T00:00:00.000+0800","actualBackTime":null,"stop":"0"},{"id":40,"bookNumber":"9787115333834","name":"Hive编程指南1","descb":"本书旨在介绍如何使用Hive\u2026\u2026","author":"Edward Capriolo;Dean Wampler","publishAddress":"人民邮电出版社","publishTime":"2013-12-01T13:57:08.000+0800","bookType":"2","status":"2","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\3.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\bangdan\\Hive编程指南.pdf","createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null,"borrowingTime":"2020-05-02T00:00:00.000+0800","preBackTime":"2020-05-08T00:00:00.000+0800","actualBackTime":null,"stop":"0"}]
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

    public static class ResultBean {
        /**
         * id : 2
         * bookNumber : 9787302070269
         * name : 程序开发心理学1
         * descb : “明白自己在做什么”，是走向成功的必要条件，大部分成功者靠的一半天分，一半勤奋。本书就来从心理学的视角来了解一个程序开发，如何趋利避害……
         * author : 杰拉尔德.温伯格
         * publishAddress : 清华大学出版社
         * publishTime : 2019-09-03T13:57:08.000+0800
         * bookType : 3
         * status : 2
         * score : 4
         * picPath : D:\tmp\libary\web\src\main\resources\static\bookpic\1.png
         * path : D:\tmp\libary\web\src\main\resources\static\book\xinshu\程序开发心理学.pdf
         * createdBy : null
         * createdAt : null
         * updatedBy : null
         * updatedAt : null
         * borrowingTime : 2020-05-01T00:00:00.000+0800
         * preBackTime : 2020-05-05T00:00:00.000+0800
         * actualBackTime : null
         * stop : 0
         */

        private int id;
        private String bookNumber;
        private String name;
        private String descb;
        private String author;
        private String publishAddress;
        private String publishTime;
        private String bookType;
        private String status;
        private int score;
        private String picPath;
        private String path;
        private Object createdBy;
        private Object createdAt;
        private Object updatedBy;
        private Object updatedAt;
        private Date borrowingTime;
        private Date preBackTime;
        private Object actualBackTime;
        private String stop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBookNumber() {
            return bookNumber;
        }

        public void setBookNumber(String bookNumber) {
            this.bookNumber = bookNumber;
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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublishAddress() {
            return publishAddress;
        }

        public void setPublishAddress(String publishAddress) {
            this.publishAddress = publishAddress;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getBookType() {
            return bookType;
        }

        public void setBookType(String bookType) {
            this.bookType = bookType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
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

        public Date getBorrowingTime() {
            return borrowingTime;
        }

        public void setBorrowingTime(Date borrowingTime) {
            this.borrowingTime = borrowingTime;
        }

        public Date getPreBackTime() {
            return preBackTime;
        }

        public void setPreBackTime(Date preBackTime) {
            this.preBackTime = preBackTime;
        }

        public Object getActualBackTime() {
            return actualBackTime;
        }

        public void setActualBackTime(Object actualBackTime) {
            this.actualBackTime = actualBackTime;
        }

        public String getStop() {
            return stop;
        }

        public void setStop(String stop) {
            this.stop = stop;
        }
    }
}
