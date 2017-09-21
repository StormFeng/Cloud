package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 银行列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class BankBean extends NetResult {

    public static BankBean parse(String json) throws AppException {
        BankBean res = new BankBean();
        try{
            res = gson.fromJson(json, BankBean.class);
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

    public static class DataBean extends NetResult{
        private String bank_id;
        private String bank_name;
        private String bank_image;

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_image() {
            return bank_image;
        }

        public void setBank_image(String bank_image) {
            this.bank_image = bank_image;
        }
    }
}
