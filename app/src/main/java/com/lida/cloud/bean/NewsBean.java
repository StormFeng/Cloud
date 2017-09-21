package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 首页公告
 * Created by WeiQingFeng on 2017/5/16.
 */

public class NewsBean extends NetResult {

    public static NewsBean parse(String json) throws AppException {
        NewsBean res = new NewsBean();
        try{
            res = gson.fromJson(json, NewsBean.class);
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
        private String newid;
        private String new_title;

        public String getNewid() {
            return newid;
        }

        public void setNewid(String newid) {
            this.newid = newid;
        }

        public String getNew_title() {
            return new_title;
        }

        public void setNew_title(String new_title) {
            this.new_title = new_title;
        }
    }
}
