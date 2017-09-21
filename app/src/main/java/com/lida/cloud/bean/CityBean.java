package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by WeiQingFeng on 2017/5/16.
 */

public class CityBean extends NetResult {
    public static CityBean parse(String json) throws AppException {
        CityBean res = new CityBean();
        try{
            res = gson.fromJson(json, CityBean.class);
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

        private String d2_id;
        private String d2_fid;
        private String d2_name;

        public String getD2_id() {
            return d2_id;
        }

        public void setD2_id(String d2_id) {
            this.d2_id = d2_id;
        }

        public String getD2_fid() {
            return d2_fid;
        }

        public void setD2_fid(String d2_fid) {
            this.d2_fid = d2_fid;
        }

        public String getD2_name() {
            return d2_name;
        }

        public void setD2_name(String d2_name) {
            this.d2_name = d2_name;
        }
    }
}
