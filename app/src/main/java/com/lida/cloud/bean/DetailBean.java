package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商家详情
 * Created by WeiQingFeng on 2017/5/16.
 */

public class DetailBean extends NetResult {

    public static DetailBean parse(String json) throws AppException {
        DetailBean res = new DetailBean();
        try{
            res = gson.fromJson(json, DetailBean.class);
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
        private Res res;
        private String isc;
        private String sl;

        public String getSl() {
            return sl;
        }

        public void setSl(String sl) {
            this.sl = sl;
        }

        public Res getRes() {
            return res;
        }

        public void setRes(Res res) {
            this.res = res;
        }

        public String getIsc() {
            return isc;
        }

        public void setIsc(String isc) {
            this.isc = isc;
        }
    }

    public static class Res{
        private String selid;
        private String selshopname;
        private String selimage;
        private String selback;
        private String selshopadd;
        private String seltel;
        private String selshoptime;
        private String selshoptype;
        private String seldetail;
        private String lon;
        private String dime;

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getDime() {
            return dime;
        }

        public void setDime(String dime) {
            this.dime = dime;
        }

        public String getSelid() {
            return selid;
        }

        public void setSelid(String selid) {
            this.selid = selid;
        }

        public String getSelshopname() {
            return selshopname;
        }

        public void setSelshopname(String selshopname) {
            this.selshopname = selshopname;
        }

        public String getSelimage() {
            return selimage;
        }

        public void setSelimage(String selimage) {
            this.selimage = selimage;
        }

        public String getSelback() {
            return selback;
        }

        public void setSelback(String selback) {
            this.selback = selback;
        }

        public String getSelshopadd() {
            return selshopadd;
        }

        public void setSelshopadd(String selshopadd) {
            this.selshopadd = selshopadd;
        }

        public String getSeltel() {
            return seltel;
        }

        public void setSeltel(String seltel) {
            this.seltel = seltel;
        }

        public String getSelshoptime() {
            return selshoptime;
        }

        public void setSelshoptime(String selshoptime) {
            this.selshoptime = selshoptime;
        }

        public String getSelshoptype() {
            return selshoptype;
        }

        public void setSelshoptype(String selshoptype) {
            this.selshoptype = selshoptype;
        }

        public String getSeldetail() {
            return seldetail;
        }

        public void setSeldetail(String seldetail) {
            this.seldetail = seldetail;
        }
    }
}
