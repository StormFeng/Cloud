package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 首页banner
 * Created by WeiQingFeng on 2017/5/16.
 */

public class BannerBean extends NetResult {

    public static BannerBean parse(String json) throws AppException {
        BannerBean res = new BannerBean();
        try{
            res = gson.fromJson(json, BannerBean.class);
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

    public static class DataBean {
        private String bnimage;
        private String bnid;

        public String getBnid() {
            return bnid;
        }

        public void setBnid(String bnid) {
            this.bnid = bnid;
        }

        public String getBnimage() {
            return bnimage;
        }

        public void setBnimage(String bnimage) {
            this.bnimage = bnimage;
        }
    }
}
