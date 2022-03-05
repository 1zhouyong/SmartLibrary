package com.example.smartlibrary.bean;

import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/10
 * Description:{}
 */
public class SeatStatusBean {

    /**
     * success : true
     * result : [{"id":1,"place":"一楼-101","number":"1排1列","status":"1","date":null,"orderBy":{"id":1,"studyId":"1904011011","password":null,"name":"张三","identity":null,"type":null,"classes":null,"instructor":null,"headPic":null,"headPicture":null,"phone":null,"age":null,"sex":null,"descb":null,"intake":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":2,"place":"一楼-101","number":"1排2列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":3,"place":"一楼-101","number":"1排3列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":4,"place":"一楼-101","number":"1排4列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":5,"place":"一楼-101","number":"1排5列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":6,"place":"一楼-101","number":"2排1列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":7,"place":"一楼-101","number":"2排2列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":8,"place":"一楼-101","number":"2排3列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":9,"place":"一楼-101","number":"2排4列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":10,"place":"一楼-101","number":"2排5列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":11,"place":"一楼-101","number":"3排1列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":12,"place":"一楼-101","number":"3排2列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":13,"place":"一楼-101","number":"3排3列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":14,"place":"一楼-101","number":"3排4列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":15,"place":"一楼-101","number":"3排5列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":16,"place":"一楼-101","number":"4排1列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":17,"place":"一楼-101","number":"4排2列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":18,"place":"一楼-101","number":"4排3列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":19,"place":"一楼-101","number":"4排4列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null},{"id":20,"place":"一楼-101","number":"4排5列","status":"0","date":null,"orderBy":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null}]
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
         * id : 1
         * place : 一楼-101
         * number : 1排1列
         * status : 1
         * date : null
         * orderBy : {"id":1,"studyId":"1904011011","password":null,"name":"张三","identity":null,"type":null,"classes":null,"instructor":null,"headPic":null,"headPicture":null,"phone":null,"age":null,"sex":null,"descb":null,"intake":null,"deleteFlag":null,"createdBy":null,"createdAt":null,"updatedBy":null,"updatedAt":null}
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
        private OrderByBean orderBy;
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

        public OrderByBean getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(OrderByBean orderBy) {
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

        public static class OrderByBean {
            /**
             * id : 1
             * studyId : 1904011011
             * password : null
             * name : 张三
             * identity : null
             * type : null
             * classes : null
             * instructor : null
             * headPic : null
             * headPicture : null
             * phone : null
             * age : null
             * sex : null
             * descb : null
             * intake : null
             * deleteFlag : null
             * createdBy : null
             * createdAt : null
             * updatedBy : null
             * updatedAt : null
             */

            private int id;
            private String studyId;
            private Object password;
            private String name;
            private Object identity;
            private Object type;
            private Object classes;
            private Object instructor;
            private Object headPic;
            private Object headPicture;
            private Object phone;
            private Object age;
            private Object sex;
            private Object descb;
            private Object intake;
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

            public String getStudyId() {
                return studyId;
            }

            public void setStudyId(String studyId) {
                this.studyId = studyId;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getIdentity() {
                return identity;
            }

            public void setIdentity(Object identity) {
                this.identity = identity;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getClasses() {
                return classes;
            }

            public void setClasses(Object classes) {
                this.classes = classes;
            }

            public Object getInstructor() {
                return instructor;
            }

            public void setInstructor(Object instructor) {
                this.instructor = instructor;
            }

            public Object getHeadPic() {
                return headPic;
            }

            public void setHeadPic(Object headPic) {
                this.headPic = headPic;
            }

            public Object getHeadPicture() {
                return headPicture;
            }

            public void setHeadPicture(Object headPicture) {
                this.headPicture = headPicture;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getDescb() {
                return descb;
            }

            public void setDescb(Object descb) {
                this.descb = descb;
            }

            public Object getIntake() {
                return intake;
            }

            public void setIntake(Object intake) {
                this.intake = intake;
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
}
