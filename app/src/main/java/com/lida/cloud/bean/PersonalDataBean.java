package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 修改个人信息
 * Created by WeiQingFeng on 2017/5/16.
 */

public class PersonalDataBean extends NetResult {

    public static PersonalDataBean parse(String json) throws AppException {
        PersonalDataBean res = new PersonalDataBean();
        try{
            res = gson.fromJson(json, PersonalDataBean.class);
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

    public static class DataBean extends NetResult{
        private String memid;
        private String mem_nc;
        private String mem_sex;
        private String mem_tel;
        private String mem_add;
        private String mem_email;
        private String mem_tx;

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
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

        public String getMem_tel() {
            return mem_tel;
        }

        public void setMem_tel(String mem_tel) {
            this.mem_tel = mem_tel;
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

        public String getMem_tx() {
            return mem_tx;
        }

        public void setMem_tx(String mem_tx) {
            this.mem_tx = mem_tx;
        }
    }
}
