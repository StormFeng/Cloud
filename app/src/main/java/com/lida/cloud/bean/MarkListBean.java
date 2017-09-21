package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 积分列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class MarkListBean extends NetResult {

    private List<DataBean> data;

    public static MarkListBean parse(String json) throws AppException {
        MarkListBean res = new MarkListBean();
        try{
            res = gson.fromJson(json, MarkListBean.class);
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
         * jf_id : 6
         * jf_name : zxc
         * jf_time : 2017-06-18 00:00:04
         * jf_state : 2
         * jf_jf : 0.18
         */

        private String jf_id;
        private String jf_name;
        private String jf_time;
        private String jf_state;
        private String jf_jf;

        public String getJf_id() {
            return jf_id;
        }

        public void setJf_id(String jf_id) {
            this.jf_id = jf_id;
        }

        public String getJf_name() {
            return jf_name;
        }

        public void setJf_name(String jf_name) {
            this.jf_name = jf_name;
        }

        public String getJf_time() {
            return jf_time;
        }

        public void setJf_time(String jf_time) {
            this.jf_time = jf_time;
        }

        public String getJf_state() {
            return jf_state;
        }

        public void setJf_state(String jf_state) {
            this.jf_state = jf_state;
        }

        public String getJf_jf() {
            return jf_jf;
        }

        public void setJf_jf(String jf_jf) {
            this.jf_jf = jf_jf;
        }
    }
}
