package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 银行卡列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class BankCardBean extends NetResult {

    public static BankCardBean parse(String json) throws AppException {
        BankCardBean res = new BankCardBean();
        try{
            res = gson.fromJson(json, BankCardBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends NetResult {
        private String b_id;
        private String b_memid;
        private String b_number;
        private String b_name;
        private String b_bankid;
        private boolean isLast;

        public String getB_id() {
            return b_id;
        }

        public void setB_id(String b_id) {
            this.b_id = b_id;
        }

        public String getB_memid() {
            return b_memid;
        }

        public void setB_memid(String b_memid) {
            this.b_memid = b_memid;
        }

        public String getB_number() {
            return b_number;
        }

        public void setB_number(String b_number) {
            this.b_number = b_number;
        }

        public String getB_name() {
            return b_name;
        }

        public void setB_name(String b_name) {
            this.b_name = b_name;
        }

        public String getB_bankid() {
            return b_bankid;
        }

        public void setB_bankid(String b_bankid) {
            this.b_bankid = b_bankid;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            isLast = last;
        }
    }
}

