package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by WeiQingFeng on 2017/5/16.
 */

public class AreaBean extends NetResult {
    public static AreaBean parse(String json) throws AppException {
        AreaBean res = new AreaBean();
        try{
            res = gson.fromJson(json, AreaBean.class);
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

        private String d3_id;
        private String d3_fid_d1;
        private String d3_fid_d2;
        private String d3_name;


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
    }
}
