package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 银行列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class ActivitySearchReslutBean extends NetResult {

    private List<DataBean> data;

    public static ActivitySearchReslutBean parse(String json) throws AppException {
        ActivitySearchReslutBean res = new ActivitySearchReslutBean();
        try{
            res = gson.fromJson(json, ActivitySearchReslutBean.class);
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
         * selid : 1
         * sel_memid : 1
         * selname : 陈光
         * selshopname : aaa
         * selshoptime : 6:00-18:00
         * selshoptype :
         * selshopadd :
         * seltel :
         * seldetail :
         * selimage : 2017-05-16/591ad4da51fc6.jpg
         * selemail :
         * selback : 10
         * selfname : null
         * selnumber : null
         * selimg1 : null
         * selimg2 : null
         * selimg3 : null
         * seljifen : 710000
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
        private String selfname;
        private String selnumber;
        private String selimg1;
        private String selimg2;
        private String selimg3;
        private String seljifen;
        private String d3_name;

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

        public String getSelfname() {
            return selfname;
        }

        public void setSelfname(String selfname) {
            this.selfname = selfname;
        }

        public String getSelnumber() {
            return selnumber;
        }

        public void setSelnumber(String selnumber) {
            this.selnumber = selnumber;
        }

        public String getSelimg1() {
            return selimg1;
        }

        public void setSelimg1(String selimg1) {
            this.selimg1 = selimg1;
        }

        public String getSelimg2() {
            return selimg2;
        }

        public void setSelimg2(String selimg2) {
            this.selimg2 = selimg2;
        }

        public String getSelimg3() {
            return selimg3;
        }

        public void setSelimg3(String selimg3) {
            this.selimg3 = selimg3;
        }

        public String getSeljifen() {
            return seljifen;
        }

        public void setSeljifen(String seljifen) {
            this.seljifen = seljifen;
        }
    }
}
