package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/5/16.
 */

public class BalanceBean extends NetResult {

    /**
     * data : {"money":[{"mo":"208.80"}],"ban":[{"ba_id":"36","ba_memid":"8","ba_time":"2017-06-02 11:21:51","ba_money":"98.90"},{"ba_id":"25","ba_memid":"8","ba_time":"2017-06-02 11:21:24","ba_money":"99.90"},{"ba_id":"14","ba_memid":"8","ba_time":"2017-06-02 11:20:01","ba_money":"5.00"},{"ba_id":"3","ba_memid":"8","ba_time":"2017-06-02 11:19:33","ba_money":"5.00"}]}
     */

    private DataBean data;

    public static BalanceBean parse(String json) throws AppException {
        BalanceBean res = new BalanceBean();
        try{
            res = gson.fromJson(json, BalanceBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        private List<MoneyBean> money;
        private List<BanBean> ban;

        public List<MoneyBean> getMoney() {
            return money;
        }

        public void setMoney(List<MoneyBean> money) {
            this.money = money;
        }

        public List<BanBean> getBan() {
            return ban;
        }

        public void setBan(List<BanBean> ban) {
            this.ban = ban;
        }

        public static class MoneyBean {
            /**
             * mo : 208.80
             */

            private String mo;

            public String getMo() {
                return mo;
            }

            public void setMo(String mo) {
                this.mo = mo;
            }
        }

        public static class BanBean extends NetResult{
            /**
             * ba_id : 36
             * ba_memid : 8
             * ba_time : 2017-06-02 11:21:51
             * ba_money : 98.90
             */

            private String ba_id;
            private String ba_memid;
            private String ba_time;
            private String ba_money;

            public String getBa_id() {
                return ba_id;
            }

            public void setBa_id(String ba_id) {
                this.ba_id = ba_id;
            }

            public String getBa_memid() {
                return ba_memid;
            }

            public void setBa_memid(String ba_memid) {
                this.ba_memid = ba_memid;
            }

            public String getBa_time() {
                return ba_time;
            }

            public void setBa_time(String ba_time) {
                this.ba_time = ba_time;
            }

            public String getBa_money() {
                return ba_money;
            }

            public void setBa_money(String ba_money) {
                this.ba_money = ba_money;
            }
        }
    }
}
