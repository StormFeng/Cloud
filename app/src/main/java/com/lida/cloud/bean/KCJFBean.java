package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/5/16.
 */

public class KCJFBean extends NetResult {


    /**
     * data : {"kc":"11300","li":[{"jf_id":"1","jf_selid":"1","jf_number":"9000","jf_time":"2017-06-03 10:06:24"},{"jf_id":"2","jf_selid":"1","jf_number":"200","jf_time":"2017-06-03 10:06:32"},{"jf_id":"3","jf_selid":"1","jf_number":"2000","jf_time":"2017-06-03 10:06:35"},{"jf_id":"4","jf_selid":"1","jf_number":"400","jf_time":"2017-06-03 10:06:43"},{"jf_id":"5","jf_selid":"1","jf_number":"800","jf_time":"2017-06-03 10:06:47"},{"jf_id":"8","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 10:57:03"},{"jf_id":"9","jf_selid":"1","jf_number":"10000","jf_time":"2017-06-13 11:02:04"},{"jf_id":"10","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 11:03:29"},{"jf_id":"11","jf_selid":"1","jf_number":"10000","jf_time":"2017-06-13 13:16:07"},{"jf_id":"12","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 13:16:29"}],"il":[{"out_id":"1","out_selid":"1","out_name":"Jack","out_credit":"100.00","out_time":"2017-06-13 16:53:41"}]}
     */

    private DataBean data;

    public static KCJFBean parse(String json) throws AppException {
        KCJFBean res = new KCJFBean();
        try{
            res = gson.fromJson(json, KCJFBean.class);
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
         * kc : 11300
         * li : [{"jf_id":"1","jf_selid":"1","jf_number":"9000","jf_time":"2017-06-03 10:06:24"},{"jf_id":"2","jf_selid":"1","jf_number":"200","jf_time":"2017-06-03 10:06:32"},{"jf_id":"3","jf_selid":"1","jf_number":"2000","jf_time":"2017-06-03 10:06:35"},{"jf_id":"4","jf_selid":"1","jf_number":"400","jf_time":"2017-06-03 10:06:43"},{"jf_id":"5","jf_selid":"1","jf_number":"800","jf_time":"2017-06-03 10:06:47"},{"jf_id":"8","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 10:57:03"},{"jf_id":"9","jf_selid":"1","jf_number":"10000","jf_time":"2017-06-13 11:02:04"},{"jf_id":"10","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 11:03:29"},{"jf_id":"11","jf_selid":"1","jf_number":"10000","jf_time":"2017-06-13 13:16:07"},{"jf_id":"12","jf_selid":"1","jf_number":"1000","jf_time":"2017-06-13 13:16:29"}]
         * il : [{"out_id":"1","out_selid":"1","out_name":"Jack","out_credit":"100.00","out_time":"2017-06-13 16:53:41"}]
         */

        private String kc;
        private List<LiBean> li;
        private List<IlBean> il;

        public String getKc() {
            return kc;
        }

        public void setKc(String kc) {
            this.kc = kc;
        }

        public List<LiBean> getLi() {
            return li;
        }

        public void setLi(List<LiBean> li) {
            this.li = li;
        }

        public List<IlBean> getIl() {
            return il;
        }

        public void setIl(List<IlBean> il) {
            this.il = il;
        }

        public static class LiBean extends NetResult{
            /**
             * jf_id : 1
             * jf_selid : 1
             * jf_number : 9000
             * jf_time : 2017-06-03 10:06:24
             */

            private String jf_id;
            private String jf_selid;
            private String jf_number;
            private String jf_time;

            public String getJf_id() {
                return jf_id;
            }

            public void setJf_id(String jf_id) {
                this.jf_id = jf_id;
            }

            public String getJf_selid() {
                return jf_selid;
            }

            public void setJf_selid(String jf_selid) {
                this.jf_selid = jf_selid;
            }

            public String getJf_number() {
                return jf_number;
            }

            public void setJf_number(String jf_number) {
                this.jf_number = jf_number;
            }

            public String getJf_time() {
                return jf_time;
            }

            public void setJf_time(String jf_time) {
                this.jf_time = jf_time;
            }
        }

        public static class IlBean extends NetResult{
            /**
             * out_id : 1
             * out_selid : 1
             * out_name : Jack
             * out_credit : 100.00
             * out_time : 2017-06-13 16:53:41
             */

            private String out_id;
            private String out_selid;
            private String out_name;
            private String out_credit;
            private String out_time;

            public String getOut_id() {
                return out_id;
            }

            public void setOut_id(String out_id) {
                this.out_id = out_id;
            }

            public String getOut_selid() {
                return out_selid;
            }

            public void setOut_selid(String out_selid) {
                this.out_selid = out_selid;
            }

            public String getOut_name() {
                return out_name;
            }

            public void setOut_name(String out_name) {
                this.out_name = out_name;
            }

            public String getOut_credit() {
                return out_credit;
            }

            public void setOut_credit(String out_credit) {
                this.out_credit = out_credit;
            }

            public String getOut_time() {
                return out_time;
            }

            public void setOut_time(String out_time) {
                this.out_time = out_time;
            }
        }
    }
}
