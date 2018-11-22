package com.yuxindemo.testproject.mvp.entity;

/**
 * Created by LiYang on 2018/10/25.
 */

public class TestBean {


    /**
     * msg : success
     * code : 1
     * data : {"subRecommendedCount":0,"code":"http://api.ttli.com/register.html?id=A165556","blockCny":5,"userStatus":1,"shopCoin":"0","level":"V1","blockCoin":"0","dynamicMoney":"0","frozzenMoney":"12000","systemTime":1540436078899,"investment_time":1539993087000,"sumCoin":"0","number":"A165556","frozzenCoin":"1000","recommended_name":"晶晶","scheduleCoin":"0","userRecommend":0,"name":"棒棒","staticMoney":"0","activeCoin":"30"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * subRecommendedCount : 0
         * code : http://api.ttli.com/register.html?id=A165556
         * blockCny : 5
         * userStatus : 1
         * shopCoin : 0
         * level : V1
         * blockCoin : 0
         * dynamicMoney : 0
         * frozzenMoney : 12000
         * systemTime : 1540436078899
         * investment_time : 1539993087000
         * sumCoin : 0
         * number : A165556
         * frozzenCoin : 1000
         * recommended_name : 晶晶
         * scheduleCoin : 0
         * userRecommend : 0
         * name : 棒棒
         * staticMoney : 0
         * activeCoin : 30
         */

        private int subRecommendedCount;
        private String code;
        private int blockCny;
        private int userStatus;
        private String shopCoin;
        private String level;
        private String blockCoin;
        private String dynamicMoney;
        private String frozzenMoney;
        private long systemTime;
        private long investment_time;
        private String sumCoin;
        private String number;
        private String frozzenCoin;
        private String recommended_name;
        private String scheduleCoin;
        private int userRecommend;
        private String name;
        private String staticMoney;
        private String activeCoin;

        public int getSubRecommendedCount() {
            return subRecommendedCount;
        }

        public void setSubRecommendedCount(int subRecommendedCount) {
            this.subRecommendedCount = subRecommendedCount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getBlockCny() {
            return blockCny;
        }

        public void setBlockCny(int blockCny) {
            this.blockCny = blockCny;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getShopCoin() {
            return shopCoin;
        }

        public void setShopCoin(String shopCoin) {
            this.shopCoin = shopCoin;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getBlockCoin() {
            return blockCoin;
        }

        public void setBlockCoin(String blockCoin) {
            this.blockCoin = blockCoin;
        }

        public String getDynamicMoney() {
            return dynamicMoney;
        }

        public void setDynamicMoney(String dynamicMoney) {
            this.dynamicMoney = dynamicMoney;
        }

        public String getFrozzenMoney() {
            return frozzenMoney;
        }

        public void setFrozzenMoney(String frozzenMoney) {
            this.frozzenMoney = frozzenMoney;
        }

        public long getSystemTime() {
            return systemTime;
        }

        public void setSystemTime(long systemTime) {
            this.systemTime = systemTime;
        }

        public long getInvestment_time() {
            return investment_time;
        }

        public void setInvestment_time(long investment_time) {
            this.investment_time = investment_time;
        }

        public String getSumCoin() {
            return sumCoin;
        }

        public void setSumCoin(String sumCoin) {
            this.sumCoin = sumCoin;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getFrozzenCoin() {
            return frozzenCoin;
        }

        public void setFrozzenCoin(String frozzenCoin) {
            this.frozzenCoin = frozzenCoin;
        }

        public String getRecommended_name() {
            return recommended_name;
        }

        public void setRecommended_name(String recommended_name) {
            this.recommended_name = recommended_name;
        }

        public String getScheduleCoin() {
            return scheduleCoin;
        }

        public void setScheduleCoin(String scheduleCoin) {
            this.scheduleCoin = scheduleCoin;
        }

        public int getUserRecommend() {
            return userRecommend;
        }

        public void setUserRecommend(int userRecommend) {
            this.userRecommend = userRecommend;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStaticMoney() {
            return staticMoney;
        }

        public void setStaticMoney(String staticMoney) {
            this.staticMoney = staticMoney;
        }

        public String getActiveCoin() {
            return activeCoin;
        }

        public void setActiveCoin(String activeCoin) {
            this.activeCoin = activeCoin;
        }
    }
}
