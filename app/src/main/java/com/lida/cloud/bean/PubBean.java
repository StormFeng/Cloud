package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 首页公益活动
 * Created by WeiQingFeng on 2017/5/16.
 */

public class PubBean extends NetResult {

    public static PubBean parse(String json) throws AppException {
        PubBean res = new PubBean();
        try{
            res = gson.fromJson(json, PubBean.class);
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
        private String pubid;
        private String pubimage;

        public String getPubid() {
            return pubid;
        }

        public void setPubid(String pubid) {
            this.pubid = pubid;
        }

        public String getPubimage() {
            return pubimage;
        }

        public void setPubimage(String pubimage) {
            this.pubimage = pubimage;
        }
    }
}
