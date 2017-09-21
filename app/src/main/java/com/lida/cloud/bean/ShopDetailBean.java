package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;

/**
 * 商户详情
 * Created by WeiQingFeng on 2017/5/16.
 */

public class ShopDetailBean extends NetResult implements Serializable{

    /**
     * data : {"selid":"1","sel_memid":"1","selname":"陈光","selshopname":"bbb","selshoptime":"6:00-18:00","selshoptype":"类型一","selshopadd":"很远","seltel":"123","seldetail":"啊是大飒飒大苏打似的","selimage":"2017-05-16/591ad4da51fc6.jpg","selemail":"jjj.hhh@.cn","selback":"10.00"}
     */

    private DataBean data;

    public static ShopDetailBean parse(String json) throws AppException {
        ShopDetailBean res = new ShopDetailBean();
        try{
            res = gson.fromJson(json, ShopDetailBean.class);
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


    public static class DataBean extends NetResult{
        /**
         * selid : 1
         * sel_memid : 1
         * selname : 陈光
         * selshopname : bbb
         * selshoptime : 6:00-18:00
         * selshoptype : 类型一
         * selshopadd : 很远
         * seltel : 123
         * seldetail : 啊是大飒飒大苏打似的
         * selimage : 2017-05-16/591ad4da51fc6.jpg
         * selemail : jjj.hhh@.cn
         * selback : 10.00
         */

        private String selid;
        private String sel_memid;
        private String selname;
        private String selshopname;
        private String selshoptime;
        private String selshoptype;
        private String selshopadd;
        private String seltel;
        private String seldetail;
        private String selimage;
        private String selemail;
        private String selback;
        private String d1_name;
        private String d2_name;
        private String d3_name;
        private String lon;
        private String dime;
        private String seljifen;

        public String getSeljifen() {
            return seljifen;
        }

        public void setSeljifen(String seljifen) {
            this.seljifen = seljifen;
        }

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

        public String getD1_name() {
            return d1_name;
        }

        public void setD1_name(String d1_name) {
            this.d1_name = d1_name;
        }

        public String getD2_name() {
            return d2_name;
        }

        public void setD2_name(String d2_name) {
            this.d2_name = d2_name;
        }

        public String getD3_name() {
            return d3_name;
        }

        public void setD3_name(String d3_name) {
            this.d3_name = d3_name;
        }

        public String getSelid() {
            return selid;
        }

        public void setSelid(String selid) {
            this.selid = selid;
        }

        public String getSel_memid() {
            return sel_memid;
        }

        public void setSel_memid(String sel_memid) {
            this.sel_memid = sel_memid;
        }

        public String getSelname() {
            return selname;
        }

        public void setSelname(String selname) {
            this.selname = selname;
        }

        public String getSelshopname() {
            return selshopname;
        }

        public void setSelshopname(String selshopname) {
            this.selshopname = selshopname;
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

        public String getSeldetail() {
            return seldetail;
        }

        public void setSeldetail(String seldetail) {
            this.seldetail = seldetail;
        }

        public String getSelimage() {
            return selimage;
        }

        public void setSelimage(String selimage) {
            this.selimage = selimage;
        }

        public String getSelemail() {
            return selemail;
        }

        public void setSelemail(String selemail) {
            this.selemail = selemail;
        }

        public String getSelback() {
            return selback;
        }

        public void setSelback(String selback) {
            this.selback = selback;
        }
    }
}
