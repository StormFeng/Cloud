package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商家商品图片
 * Created by WeiQingFeng on 2017/5/16.
 */

public class GoodBean extends NetResult {

    private List<DataBean> data;

    public static GoodBean parse(String json) throws AppException {
        GoodBean res = new GoodBean();
        try{
            res = gson.fromJson(json, GoodBean.class);
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
         * sp_id : 2
         * sp_selid : 1
         * sp_name : 阿斯达
         * sp_image : 2017-05-18/591d5e5d343d2.jpg
         */

        private String sp_id;
        private String sp_selid;
        private String sp_name;
        private String sp_image;

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
