package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 评论列表
 * Created by WeiQingFeng on 2017/5/16.
 */

public class CommentBean extends NetResult {

    private List<DataBean> data;

    /**
     * data : {"money":[{"mo":"208.80"}],"ban":[{"ba_id":"36","ba_memid":"8","ba_time":"2017-06-02 11:21:51","ba_money":"98.90"},{"ba_id":"25","ba_memid":"8","ba_time":"2017-06-02 11:21:24","ba_money":"99.90"},{"ba_id":"14","ba_memid":"8","ba_time":"2017-06-02 11:20:01","ba_money":"5.00"},{"ba_id":"3","ba_memid":"8","ba_time":"2017-06-02 11:19:33","ba_money":"5.00"}]}
     */

    public static CommentBean parse(String json) throws AppException {
        CommentBean res = new CommentBean();
        try{
            res = gson.fromJson(json, CommentBean.class);
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
         * mem_tx : null
         * mem_nc : 这是昵称
         * com_con : ahoadsfad
         * com_time : 2017-06-05 09:45:01
         */

        private String mem_tx;
        private String mem_nc;
        private String com_con;
        private String com_time;
        private String mem_name;

        public String getMem_name() {
            return mem_name;
        }

        public void setMem_name(String mem_name) {
            this.mem_name = mem_name;
        }

        public Object getMem_tx() {
            return mem_tx;
        }

        public void setMem_tx(String mem_tx) {
            this.mem_tx = mem_tx;
        }

        public String getMem_nc() {
            return mem_nc;
        }

        public void setMem_nc(String mem_nc) {
            this.mem_nc = mem_nc;
        }

        public String getCom_con() {
            return com_con;
        }

        public void setCom_con(String com_con) {
            this.com_con = com_con;
        }

        public String getCom_time() {
            return com_time;
        }

        public void setCom_time(String com_time) {
            this.com_time = com_time;
        }
    }
}
