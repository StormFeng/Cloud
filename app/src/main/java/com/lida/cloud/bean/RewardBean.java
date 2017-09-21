package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 奖励的金额
 */

public class RewardBean extends NetResult {


    private List<DataBean> data;

    public static RewardBean parse(String json) throws AppException {
        RewardBean res = new RewardBean();
        try{
            res = gson.fromJson(json, RewardBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean extends NetResult{
        /**
         * mem_hname :
         * mem_nc : null
         * mem_tx : null
         * rec_type : 1
         * rec_price : 425.00
         * rec_time : 2017-06-02 19:21:06
         */

        private String mem_hname;
        private String mem_nc;
        private String mem_tx;
        private String rec_type;
        private String rec_price;
        private String rec_time;

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

        public String getRec_type() {
            return rec_type;
        }

        public void setRec_type(String rec_type) {
            this.rec_type = rec_type;
        }

        public String getRec_price() {
            return rec_price;
        }

        public void setRec_price(String rec_price) {
            this.rec_price = rec_price;
        }

        public String getRec_time() {
            return rec_time;
        }

        public void setRec_time(String rec_time) {
            this.rec_time = rec_time;
        }
    }
}
