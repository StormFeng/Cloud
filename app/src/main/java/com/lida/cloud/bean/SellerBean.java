package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商家列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class SellerBean extends NetResult {


    /**
     * data : {"cy":[{"selid":"1","sel_memid":"43","selname":null,"selshopname":"上海贪玩游戏","selshoptime":null,"selshoptype":"美食","selshopadd":"菁晖街10-117号","seltel":"15978787878","seldetail":null,"selimage":null,"selemail":null,"selback":"100","selfname":"小美","selnumber":"123456","selimg1":"","selimg2":"","selimg3":"","seljifen":"11100","seltj":"1","lon":"113.42637772623034","dime":"23.1180459065262","dqid":null,"d3_id":null,"d3_fid_d1":null,"d3_fid_d2":null,"d3_name":null,"mi":0,"shu":"126.11830910968"},{"selid":"2","sel_memid":"49","selname":null,"selshopname":"南岸奥迪","selshoptime":null,"selshoptype":"汽车","selshopadd":"林和西横路63号","seltel":"18688265462","seldetail":"军统","selimage":null,"selemail":"","selback":"100","selfname":"UI","selnumber":"441488598998","selimg1":"","selimg2":"","selimg3":"","seljifen":"1924000","seltj":"1","lon":"113.32821988318747","dime":"23.151677765767413","dqid":null,"d3_id":null,"d3_fid_d1":null,"d3_fid_d2":null,"d3_name":null,"mi":1,"shu":"126.00916598455"}]}
     */

    private DataBean data;

    public static SellerBean parse(String json) throws AppException {
        SellerBean res = new SellerBean();
        try{
            res = gson.fromJson(json, SellerBean.class);
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
        private List<CyBean> cy;

        public List<CyBean> getCy() {
            return cy;
        }

        public void setCy(List<CyBean> cy) {
            this.cy = cy;
        }

        public static class CyBean extends NetResult{
            /**
             * selid : 1
             * sel_memid : 43
             * selname : null
             * selshopname : 上海贪玩游戏
             * selshoptime : null
             * selshoptype : 美食
             * selshopadd : 菁晖街10-117号
             * seltel : 15978787878
             * seldetail : null
             * selimage : null
             * selemail : null
             * selback : 100
             * selfname : 小美
             * selnumber : 123456
             * selimg1 : 
             * selimg2 : 
             * selimg3 : 
             * seljifen : 11100
             * seltj : 1
             * lon : 113.42637772623034
             * dime : 23.1180459065262
             * dqid : null
             * d3_id : null
             * d3_fid_d1 : null
             * d3_fid_d2 : null
             * d3_name : null
             * mi : 0
             * shu : 126.11830910968
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
            private String lon;
            private String dime;
            private String dqid;
            private String d3_id;
            private String d3_fid_d1;
            private String d3_fid_d2;
            private String d3_name;
            private String mi;
            private String shu;

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

            public String getDqid() {
                return dqid;
            }

            public void setDqid(String dqid) {
                this.dqid = dqid;
            }

            public String getD3_id() {
                return d3_id;
            }

            public void setD3_id(String d3_id) {
                this.d3_id = d3_id;
            }

            public String getD3_fid_d1() {
                return d3_fid_d1;
            }

            public void setD3_fid_d1(String d3_fid_d1) {
                this.d3_fid_d1 = d3_fid_d1;
            }

            public String getD3_fid_d2() {
                return d3_fid_d2;
            }

            public void setD3_fid_d2(String d3_fid_d2) {
                this.d3_fid_d2 = d3_fid_d2;
            }

            public String getD3_name() {
                return d3_name;
            }

            public void setD3_name(String d3_name) {
                this.d3_name = d3_name;
            }

            public String getMi() {
                return mi;
            }

            public void setMi(String mi) {
                this.mi = mi;
            }

            public String getShu() {
                return shu;
            }

            public void setShu(String shu) {
                this.shu = shu;
            }
        }
    }
}
