package com.lida.cloud.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/5/16.
 */

public class RecommendBean extends NetResult {

    /**
     * data : {"money":[{"m":"2592.50"}],"credit":[{"c":null}],"member":"5","shop":"2","memli":[{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"1","mem_hname":"","mem_nc":null,"mem_tx":null}],"shopli":[{"mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_hname":"","mem_nc":null,"mem_tx":null}]}
     */

    private DataBean data;

    public static RecommendBean parse(String json) throws AppException {
        RecommendBean res = new RecommendBean();
        try{
            res = gson.fromJson(json, RecommendBean.class);
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
        /**
         * money : [{"m":"2592.50"}]
         * credit : [{"c":null}]
         * member : 5
         * shop : 2
         * memli : [{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"2","mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_state":"1","mem_hname":"","mem_nc":null,"mem_tx":null}]
         * shopli : [{"mem_hname":"","mem_nc":null,"mem_tx":null},{"mem_hname":"","mem_nc":null,"mem_tx":null}]
         */

        private String member;
        private String shop;
        private List<MoneyBean> money;
        private List<CreditBean> credit;
        private List<MemliBean> memli;
        private List<ShopliBean> shopli;

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public List<MoneyBean> getMoney() {
            return money;
        }

        public void setMoney(List<MoneyBean> money) {
            this.money = money;
        }

        public List<CreditBean> getCredit() {
            return credit;
        }

        public void setCredit(List<CreditBean> credit) {
            this.credit = credit;
        }

        public List<MemliBean> getMemli() {
            return memli;
        }

        public void setMemli(List<MemliBean> memli) {
            this.memli = memli;
        }

        public List<ShopliBean> getShopli() {
            return shopli;
        }

        public void setShopli(List<ShopliBean> shopli) {
            this.shopli = shopli;
        }

        public static class MoneyBean{
            /**
             * m : 2592.50
             */

            private String m;

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }
        }

        public static class CreditBean {
            /**
             * c : null
             */

            private String c;

            public String getC() {
                return c;
            }

            public void setC(String c) {
                this.c = c;
            }
        }

        public static class MemliBean extends NetResult{
            /**
             * mem_state : 2
             * mem_hname :
             * mem_nc : null
             * mem_tx : null
             */

            private String mem_state;
            private String mem_hname;
            private String mem_nc;
            private String mem_tx;
            private String mem_name;

            public String getMem_name() {
                return mem_name;
            }

            public void setMem_name(String mem_name) {
                this.mem_name = mem_name;
            }

            public String getMem_state() {
                return mem_state;
            }

            public void setMem_state(String mem_state) {
                this.mem_state = mem_state;
            }

            public String getMem_hname() {
                return mem_hname;
            }

            public void setMem_hname(String mem_hname) {
                this.mem_hname = mem_hname;
            }

            public String getMem_nc() {
                return mem_nc;
            }

            public void setMem_nc(String mem_nc) {
                this.mem_nc = mem_nc;
            }

            public String getMem_tx() {
                return mem_tx;
            }

            public void setMem_tx(String mem_tx) {
                this.mem_tx = mem_tx;
            }
        }

        public static class ShopliBean extends NetResult{
            /**
             * mem_hname :
             * mem_nc : null
             * mem_tx : null
             */

            private String mem_hname;
            private String mem_nc;
            private String mem_tx;

            public String getMem_hname() {
                return mem_hname;
            }

            public void setMem_hname(String mem_hname) {
                this.mem_hname = mem_hname;
            }

            public String getMem_nc() {
                return mem_nc;
            }

            public void setMem_nc(String mem_nc) {
                this.mem_nc = mem_nc;
            }

            public String getMem_tx() {
                return mem_tx;
            }

            public void setMem_tx(String mem_tx) {
                this.mem_tx = mem_tx;
            }

        }
    }
}
