package com.example.smartlibrary.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/26
 * Description:{}
 */
public class LibraryTypeBean {

    /**
     * success : true
     * result : [{"id":28,"bookNumber":"9787121109416","name":"java分布式","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":29,"bookNumber":"9787121109416","name":"java分布式1","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":30,"bookNumber":"9787121109416","name":"java分布式2","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":31,"bookNumber":"9787121109416","name":"java分布式3","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":32,"bookNumber":"9787121109416","name":"java分布式4","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":33,"bookNumber":"9787121109416","name":"java分布式5","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":34,"bookNumber":"9787121109416","name":"java分布式6","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":35,"bookNumber":"9787121109416","name":"java分布式7","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":36,"bookNumber":"9787121109416","name":"java分布式8","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":37,"bookNumber":"9787121109416","name":"java分布式9","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"},{"id":38,"bookNumber":"9787121109416","name":"java分布式10","descb":"本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用\u2026\u2026","author":"林昊","publishAddress":"电子工业出版社","publishTime":"2010-06-01T13:57:08.000+0800","bookType":"1","status":"1","score":5,"picPath":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\bookpic\\2.jpg","path":"D:\\tmp\\libary\\web\\src\\main\\resources\\static\\book\\tuijian\\java分布式.pdf","createdBy":"system","createdAt":"2020-04-25T14:27:02.000+0800","updatedBy":"system","updatedAt":"2020-04-25T14:27:11.000+0800"}]
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
         * id : 28
         * bookNumber : 9787121109416
         * name : java分布式
         * descb : 本书介绍分布式java应用设计的知识点，分为基于java实现网络、RPC；基于SOA实现大型分布式java应用……
         * author : 林昊
         * publishAddress : 电子工业出版社
         * publishTime : 2010-06-01T13:57:08.000+0800
         * bookType : 1
         * status : 1
         * score : 5
         * picPath : D:\tmp\libary\web\src\main\resources\static\bookpic\2.jpg
         * path : D:\tmp\libary\web\src\main\resources\static\book\tuijian\java分布式.pdf
         * createdBy : system
         * createdAt : 2020-04-25T14:27:02.000+0800
         * updatedBy : system
         * updatedAt : 2020-04-25T14:27:11.000+0800
         */

        private int id;
        private String bookNumber;
        private String name;
        private String descb;
        private String author;
        private String publishAddress;
        private Date publishTime;
        private String bookType;
        private String status;
        private int score;
        private String picPath;
        private String path;
        private String createdBy;
        private String createdAt;
        private String updatedBy;
        private String updatedAt;

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

        public Date getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Date publishTime) {
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

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
