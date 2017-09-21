package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 用户信息
 * Created by WeiQingFeng on 2017/5/16.
 */

public class MemDetailBean extends NetResult {

    private List<DataBean> data;

    public static MemDetailBean parse(String json) throws AppException {
        MemDetailBean res = new MemDetailBean();
        try{
            res = gson.fromJson(json, MemDetailBean.class);
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


    public static class DataBean {
        /**
         * memid : 63
         * mem_hname : 123
         * mem_tname : 123
         * mem_number : 123
         * mem_name : test1
         * mem_passwd : 202cb962ac59075b964b07152d234b70
         * mem_tx : 2017-06-07/59380e6960024.jpg
         * mem_state : null
         * mem_tel : 13888888888
         * mem_nc : null
         * mem_sex : null
         * mem_add : null
         * mem_email : null
         * mem_jifen : 0.00
         * mem_yue : 0.00
         * mem_noyue : 0.00
         */

        private String memid;
        private String mem_hname;
        private String mem_tname;
        private String mem_number;
        private String mem_name;
        private String mem_passwd;
        private String mem_tx;
        private String mem_state;
        private String mem_tel;
        private String mem_nc;
        private String mem_sex;
        private String mem_add;
        private String mem_email;
        private String mem_jifen;
        private String mem_yue;
        private String mem_noyue;

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
        }

        public String getMem_hname() {
            return mem_hname;
        }

        public void setMem_hname(String mem_hname) {
            this.mem_hname = mem_hname;
        }

        public String getMem_tname() {
            return mem_tname;
        }

        public void setMem_tname(String mem_tname) {
            this.mem_tname = mem_tname;
        }

        public String getMem_number() {
            return mem_number;
        }

        public void setMem_number(String mem_number) {
            this.mem_number = mem_number;
        }

        public String getMem_name() {
            return mem_name;
        }

        public void setMem_name(String mem_name) {
            this.mem_name = mem_name;
        }

        public String getMem_passwd() {
            return mem_passwd;
        }

        public void setMem_passwd(String mem_passwd) {
            this.mem_passwd = mem_passwd;
        }

        public String getMem_tx() {
            return mem_tx;
        }

        public void setMem_tx(String mem_tx) {
            this.mem_tx = mem_tx;
        }

        public String getMem_state() {
            return mem_state;
        }

        public void setMem_state(String mem_state) {
            this.mem_state = mem_state;
        }

        public String getMem_tel() {
            return mem_tel;
        }

        public void setMem_tel(String mem_tel) {
            this.mem_tel = mem_tel;
        }

        public String getMem_nc() {
            return mem_nc;
        }

        public void setMem_nc(String mem_nc) {
            this.mem_nc = mem_nc;
        }

        public String getMem_sex() {
            return mem_sex;
        }

        public void setMem_sex(String mem_sex) {
            this.mem_sex = mem_sex;
        }

        public String getMem_add() {
            return mem_add;
        }

        public void setMem_add(String mem_add) {
            this.mem_add = mem_add;
        }

        public String getMem_email() {
            return mem_email;
        }

        public void setMem_email(String mem_email) {
            this.mem_email = mem_email;
        }

        public String getMem_jifen() {
            return mem_jifen;
        }

        public void setMem_jifen(String mem_jifen) {
            this.mem_jifen = mem_jifen;
        }

        public String getMem_yue() {
            return mem_yue;
        }

        public void setMem_yue(String mem_yue) {
            this.mem_yue = mem_yue;
        }

        public String getMem_noyue() {
            return mem_noyue;
        }

        public void setMem_noyue(String mem_noyue) {
            this.mem_noyue = mem_noyue;
        }
    }
}
