package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 奖励的积分
 */

public class RewardMarkBean extends NetResult {


    private List<DataBean> data;

    public static RewardMarkBean parse(String json) throws AppException {
        RewardMarkBean res = new RewardMarkBean();
        try{
            res = gson.fromJson(json, RewardMarkBean.class);
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
         * cre_credit : 25.00
         * cre_time : 2017-06-02 19:59:05
         */

        private String mem_hname;
        private String mem_nc;
        private String mem_tx;
        private String cre_credit;
        private String cre_time;

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

        public String getCre_credit() {
            return cre_credit;
        }

        public void setCre_credit(String cre_credit) {
            this.cre_credit = cre_credit;
        }

        public String getCre_time() {
            return cre_time;
        }

        public void setCre_time(String cre_time) {
            this.cre_time = cre_time;
        }
    }
}
