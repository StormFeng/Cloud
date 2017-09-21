package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 收藏列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class CollectBean extends NetResult {

    public static CollectBean parse(String json) throws AppException {
        CollectBean res = new CollectBean();
        try{
            res = gson.fromJson(json, CollectBean.class);
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
        private String selid;
        private String selshopname;
        private String selimage;
        private String selshoptype;
        private String col_id;
        private String selback;

        public String getSelback() {
            return selback;
        }

        public void setSelback(String selback) {
            this.selback = selback;
        }

        public String getCol_id() {
            return col_id;
        }

        public void setCol_id(String col_id) {
            this.col_id = col_id;
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

        public String getSelshoptype() {
            return selshoptype;
        }

        public void setSelshoptype(String selshoptype) {
            this.selshoptype = selshoptype;
        }
    }
}
