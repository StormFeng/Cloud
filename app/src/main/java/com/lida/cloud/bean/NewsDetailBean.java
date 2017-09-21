package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;


/**
 * 首页公告
 * Created by WeiQingFeng on 2017/5/16.
 */

public class NewsDetailBean extends NetResult {

    public static NewsDetailBean parse(String json) throws AppException {
        NewsDetailBean res = new NewsDetailBean();
        try{
            res = gson.fromJson(json, NewsDetailBean.class);
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
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
