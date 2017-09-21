package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 会员价格
 * Created by WeiQingFeng on 2017/5/16.
 */

public class PriceBean extends NetResult {

    private List<DataBean> data;

    public static PriceBean parse(String json) throws AppException {
        PriceBean res = new PriceBean();
        try{
            res = gson.fromJson(json, PriceBean.class);
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "pri_id='" + pri_id + '\'' +
                    ", pri_type='" + pri_type + '\'' +
                    ", mem_type='" + mem_type + '\'' +
                    ", pri_back='" + pri_back + '\'' +
                    ", select=" + select +
                    '}';
        }

        /**
         * pri_id : 1
         * pri_type : 1000
         * mem_type : 初级会员
         * pri_back : 5.0s
         */



        private String pri_id;
        private String pri_type;
        private String mem_type;
        private String pri_back;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getPri_id() {
            return pri_id;
        }

        public void setPri_id(String pri_id) {
            this.pri_id = pri_id;
        }

        public String getPri_type() {
            return pri_type;
        }

        public void setPri_type(String pri_type) {
            this.pri_type = pri_type;
        }

        public String getMem_type() {
            return mem_type;
        }

        public void setMem_type(String mem_type) {
            this.mem_type = mem_type;
        }

        public String getPri_back() {
            return pri_back;
        }

        public void setPri_back(String pri_back) {
            this.pri_back = pri_back;
        }
    }

    @Override
    public String toString() {
        return "PriceBean{" +
                "data=" + data +
                '}';
    }
}
