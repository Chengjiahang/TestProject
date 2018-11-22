package com.yuxindemo.testproject.mvp.entity;

/**
 * Created by Administrator on 2018/7/19.
 */

public class UserBean {


    /**
     * code : 200
     * data : {"education":"本科","inProcessing":0,"openId":"A3F81372CCD3A9522609A5816AFE06F1","serialVersionUID":-2566441764189220519,"phoneNunber":"15071333409","isRealAuth":1,"vip":"0","registerDate":"2018-06-22 14:54:55","workStatus":"","pursueReason":null,"unionId":"oeFIjv7qW59YTFZjbGGm6lOmYy58","isCompanyAuth":0,"nickName":"Studio","sex":"男","userId":101,"applyForTime":null,"lastLoginTime":"2018-09-14 09:51:48","preProcessing":2,"orderComplete":0,"postEnabled":"0","shopSign":null,"contactWay":null,"userIcon":"http://thirdwx.qlogo.cn/mmopen/vi_32/yYpJXjAuvKK2s0ySQTyLmB0YXuQ8ibOkTaHSxLML00oZ4sodFASJBFFxh4vXk6zmZckXY9L4DORiaDNM7Erb4vqw/132","age":28,"socialIdentify":"上班族"}
     */

    private String code;
    private String phone;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * education : 本科
         * inProcessing : 0
         * openId : A3F81372CCD3A9522609A5816AFE06F1
         * serialVersionUID : -2566441764189220519
         * phoneNunber : 15071333409
         * isRealAuth : 1
         * vip : 0
         * registerDate : 2018-06-22 14:54:55
         * workStatus :
         * pursueReason : null
         * unionId : oeFIjv7qW59YTFZjbGGm6lOmYy58
         * isCompanyAuth : 0
         * nickName : Studio
         * sex : 男
         * userId : 101
         * applyForTime : null
         * lastLoginTime : 2018-09-14 09:51:48
         * preProcessing : 2
         * orderComplete : 0
         * postEnabled : 0
         * shopSign : null
         * contactWay : null
         * userIcon : http://thirdwx.qlogo.cn/mmopen/vi_32/yYpJXjAuvKK2s0ySQTyLmB0YXuQ8ibOkTaHSxLML00oZ4sodFASJBFFxh4vXk6zmZckXY9L4DORiaDNM7Erb4vqw/132
         * age : 28
         * socialIdentify : 上班族
         */

        private String education;
        private int inProcessing;
        private String openId;
        private long serialVersionUID;
        private String phoneNunber;
        private int isRealAuth;
        private String vip;
        private String registerDate;
        private String workStatus;
        private Object pursueReason;
        private String unionId;
        private int isCompanyAuth;
        private String nickName;
        private String sex;
        private int userId;
        private Object applyForTime;
        private String lastLoginTime;
        private int preProcessing;
        private int orderComplete;
        private String postEnabled;
        private Object shopSign;
        private Object contactWay;
        private String userIcon;
        private int age;
        private String socialIdentify;

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public int getInProcessing() {
            return inProcessing;
        }

        public void setInProcessing(int inProcessing) {
            this.inProcessing = inProcessing;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public long getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setSerialVersionUID(long serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }

        public String getPhoneNunber() {
            return phoneNunber;
        }

        public void setPhoneNunber(String phoneNunber) {
            this.phoneNunber = phoneNunber;
        }

        public int getIsRealAuth() {
            return isRealAuth;
        }

        public void setIsRealAuth(int isRealAuth) {
            this.isRealAuth = isRealAuth;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }

        public Object getPursueReason() {
            return pursueReason;
        }

        public void setPursueReason(Object pursueReason) {
            this.pursueReason = pursueReason;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }

        public int getIsCompanyAuth() {
            return isCompanyAuth;
        }

        public void setIsCompanyAuth(int isCompanyAuth) {
            this.isCompanyAuth = isCompanyAuth;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getApplyForTime() {
            return applyForTime;
        }

        public void setApplyForTime(Object applyForTime) {
            this.applyForTime = applyForTime;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getPreProcessing() {
            return preProcessing;
        }

        public void setPreProcessing(int preProcessing) {
            this.preProcessing = preProcessing;
        }

        public int getOrderComplete() {
            return orderComplete;
        }

        public void setOrderComplete(int orderComplete) {
            this.orderComplete = orderComplete;
        }

        public String getPostEnabled() {
            return postEnabled;
        }

        public void setPostEnabled(String postEnabled) {
            this.postEnabled = postEnabled;
        }

        public Object getShopSign() {
            return shopSign;
        }

        public void setShopSign(Object shopSign) {
            this.shopSign = shopSign;
        }

        public Object getContactWay() {
            return contactWay;
        }

        public void setContactWay(Object contactWay) {
            this.contactWay = contactWay;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSocialIdentify() {
            return socialIdentify;
        }

        public void setSocialIdentify(String socialIdentify) {
            this.socialIdentify = socialIdentify;
        }
    }
}
