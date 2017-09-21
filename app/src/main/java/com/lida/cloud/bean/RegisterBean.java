package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 注册
 * Created by WeiQingFeng on 2017/5/16.
 */

public class RegisterBean extends NetResult {
    public static RegisterBean parse(String json) throws AppException {
        RegisterBean res = new RegisterBean();
        try{
            res = gson.fromJson(json, RegisterBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String memid;
        private String mem_name;
        private String mem_number;
        private String mem_tx;
        private String mem_staus;
        private String price;
        private String appid;
        private String key;
        private String callback_url;
        private String desc;
        private String title;
        private String number;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCallback_url() {
            return callback_url;
        }

        public void setCallback_url(String callback_url) {
            this.callback_url = callback_url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
        }

        public String getMem_name() {
            return mem_name;
        }

        public void setMem_name(String mem_name) {
            this.mem_name = mem_name;
        }

        public String getMem_number() {
            return mem_number;
        }

        public void setMem_number(String mem_number) {
            this.mem_number = mem_number;
        }

        public String getMem_tx() {
            return mem_tx;
        }

        public void setMem_tx(String mem_tx) {
            this.mem_tx = mem_tx;
        }

        public String getMem_staus() {
            return mem_staus;
        }

        public void setMem_staus(String mem_staus) {
            this.mem_staus = mem_staus;
        }
    }
}
