package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 商家详情
 * Created by WeiQingFeng on 2017/5/16.
 */

public class AboutUsBean extends NetResult {

    /**
     * data : {"url":"192.168.0.122/huaer/admin.php/Home/Inter/about_us"}
     */

    private DataBean data;

    public static AboutUsBean parse(String json) throws AppException {
        AboutUsBean res = new AboutUsBean();
        try{
            res = gson.fromJson(json, AboutUsBean.class);
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

    public static class DataBean {
        /**
         * url : 192.168.0.122/huaer/admin.php/Home/Inter/about_us
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
