package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 用户信息
 * Created by WeiQingFeng on 2017/5/16.
 */

public class RecommendShopBean extends NetResult {

    private List<DataBean> data;

    public static RecommendShopBean parse(String json) throws AppException {
        RecommendShopBean res = new RecommendShopBean();
        try{
            res = gson.fromJson(json, RecommendShopBean.class);
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
         * seltj : 2
         * long :
         * dime :
         * sp_id : 2
         * sp_selid : 1
         * sp_name : 阿斯达
         * sp_image : 2017-05-18/591d5e5d343d2.jpg
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
        private String seltj;
        @SerializedName("long")
        private String longX;
        private String dime;
        private String sp_id;
        private String sp_selid;
        private String sp_name;
        private String sp_image;

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

        public String getSeltj() {
            return seltj;
        }

        public void setSeltj(String seltj) {
            this.seltj = seltj;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getDime() {
            return dime;
        }

        public void setDime(String dime) {
            this.dime = dime;
        }

        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
            this.sp_id = sp_id;
        }

        public String getSp_selid() {
            return sp_selid;
        }

        public void setSp_selid(String sp_selid) {
            this.sp_selid = sp_selid;
        }

        public String getSp_name() {
            return sp_name;
        }

        public void setSp_name(String sp_name) {
            this.sp_name = sp_name;
        }

        public String getSp_image() {
            return sp_image;
        }

        public void setSp_image(String sp_image) {
            this.sp_image = sp_image;
        }
    }
}
