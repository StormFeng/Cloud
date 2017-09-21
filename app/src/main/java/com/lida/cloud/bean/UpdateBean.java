package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 会员升级
 * Created by WeiQingFeng on 2017/5/16.
 */

public class UpdateBean extends NetResult {

    /**
     * data : {"price":"900","appid":"2017053107390673","key":"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgr6QGefTGQsG5dd6z9mOZhDg8AD1zSNz5Qtr9d40RKWMGTJJeL6h23Enbe7520CjgOj6Blgr9aCVjYN1kPXufgvPBxLtvY+fW1gQsXDay2LNPxdP/HVE7JrAPccJasBa5bNCVp0LsL2MMMhIaOVbm4L9u6vgZ/FPuhY1TuHiTgDt3R2TFVfjBAKeap1oBz2o3dqsVadTtpY4UOpXxloAJYR8cs52YJPgy6xN9ZMYnKOZ9PDCGeGEx/lqPHX8DVHFRbnojBHQuniJCt8nRpCjH2D73W0z8I70WOv9HKbK1dLukxCE1I8HFY5N0dq6PrWJewkyhv4o8Efqh05Tag/sXAgMBAAECggEBAJyYDwWwvHaqgBi5YfuH1rC5RgRTR4+eJptUKA5z/6oF51cwMERRtZ+ANwoKoicv3WYH3Pp0uukSIb2jnJ4fvOZKvDrN+swnVLztfMuuYjARN046wMsSSyOWJGYouvueN8ck4HG3u2RpGeG8uh+MWcBOrsDthDPuz+zXgk9gq0EyW+wZNuvaeS8+SjrMpkl7vMbXdmK0sba0U/ozNbB4zCgIIQSbk5lVv+Mpsj9mqaSCDUfxQoIQsMRnl3pIqr+ge7lfgxEBoFLKykwA/ga/RTWn963axzIMPgEnDw5AGTR+RT3Sxim8XEQufJDMsehvPJL8f2yfEAv+FTXvLRb6D8ECgYEA579p2UIj4VUOpmyT4XaV7YHs5RVyIjvg2VzSuOyO/POCe5lgE84wUXQwp3ua+4RAJxuLtL3MAkgCDfs1aZdZPY3MoFw9jdGoaizQBn5rLrvr460pVDb2amK1GoW5WiOAdDk7fUCZvrD53qw0OnYKxssGvrKIA7q5VTfoQlxymU8CgYEAsYB3c0hApTYTijXTE+VOZrerbAN7hS3O3DIcK/OjuD9tHtIDpQaB1WnkEfX7+IrYQifEjA9l/YI771JpMZ9vOFeavwwZ5dD6immFR51vN39jgk6YLaj2CnNe1hBntNJw+iwfdq0BZvrH8hBCUHx1W72TG/+8rWe/u+RcumDLf7kCgYB2hxjtDH3rbqRqjBfqdctiyVPPKvPP/I4YvffsqtjLGQSAkMZp+sgNsP59eqOexlpFZjpC+9vOwuOCIRyFX+o9qDujPOoikbuwajEPUTMimOyh6tu7fcz0Rn3YDdLp/kRhrS/29MZtmHiD4dFooR0L5aUSfZ9BQQwvQMPixQO70wKBgHB3mrgT3soPon/fLNgdYfjZhadR6ZDv/nMs2QbMQD89CPIPVbnZjqhfiOAMgVPw/aelNPcdxpzDSSCRkg+cGseTUnycuUgRir3qsEU5Lr/2WmG1VN0RgpwKj9uI130OHgGVtXXnlszeN5Rb0vjWczHmw3BpMO6anzT5M/vv2uEZAoGACl/pYrQ0LYSiVE8ioVbs/GbBXviGWIPSrcSjcPOnwj2NSjvQpKr4X99yMstYomAsZ8kPzllw8FB9R3ZIo1hCW9kCgZgS3RXTeNdwnXcawd41mGvvYAo+ReBYlqs/buLRUAWnplUhUwkhqk/d0tD9Met8wSCusEyjYfayYWJzYcE=","callback_url":"http://cs2.gzldkj.cn/index.php/Home/Inter/addjf","aes":"WE9fhs8W34YSvgmFtaTCtw==","name":"13510801040007522","desc":"云众利升级会员","title":"升级会员","selid":null,"jifen":null,"number":"A6145209337196207"}
     */

    private DataBean data;

    public static UpdateBean parse(String json) throws AppException {
        UpdateBean res = new UpdateBean();
        try{
            res = gson.fromJson(json, UpdateBean.class);
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
         * price : 900
         * appid : 2017053107390673
         * key : MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgr6QGefTGQsG5dd6z9mOZhDg8AD1zSNz5Qtr9d40RKWMGTJJeL6h23Enbe7520CjgOj6Blgr9aCVjYN1kPXufgvPBxLtvY+fW1gQsXDay2LNPxdP/HVE7JrAPccJasBa5bNCVp0LsL2MMMhIaOVbm4L9u6vgZ/FPuhY1TuHiTgDt3R2TFVfjBAKeap1oBz2o3dqsVadTtpY4UOpXxloAJYR8cs52YJPgy6xN9ZMYnKOZ9PDCGeGEx/lqPHX8DVHFRbnojBHQuniJCt8nRpCjH2D73W0z8I70WOv9HKbK1dLukxCE1I8HFY5N0dq6PrWJewkyhv4o8Efqh05Tag/sXAgMBAAECggEBAJyYDwWwvHaqgBi5YfuH1rC5RgRTR4+eJptUKA5z/6oF51cwMERRtZ+ANwoKoicv3WYH3Pp0uukSIb2jnJ4fvOZKvDrN+swnVLztfMuuYjARN046wMsSSyOWJGYouvueN8ck4HG3u2RpGeG8uh+MWcBOrsDthDPuz+zXgk9gq0EyW+wZNuvaeS8+SjrMpkl7vMbXdmK0sba0U/ozNbB4zCgIIQSbk5lVv+Mpsj9mqaSCDUfxQoIQsMRnl3pIqr+ge7lfgxEBoFLKykwA/ga/RTWn963axzIMPgEnDw5AGTR+RT3Sxim8XEQufJDMsehvPJL8f2yfEAv+FTXvLRb6D8ECgYEA579p2UIj4VUOpmyT4XaV7YHs5RVyIjvg2VzSuOyO/POCe5lgE84wUXQwp3ua+4RAJxuLtL3MAkgCDfs1aZdZPY3MoFw9jdGoaizQBn5rLrvr460pVDb2amK1GoW5WiOAdDk7fUCZvrD53qw0OnYKxssGvrKIA7q5VTfoQlxymU8CgYEAsYB3c0hApTYTijXTE+VOZrerbAN7hS3O3DIcK/OjuD9tHtIDpQaB1WnkEfX7+IrYQifEjA9l/YI771JpMZ9vOFeavwwZ5dD6immFR51vN39jgk6YLaj2CnNe1hBntNJw+iwfdq0BZvrH8hBCUHx1W72TG/+8rWe/u+RcumDLf7kCgYB2hxjtDH3rbqRqjBfqdctiyVPPKvPP/I4YvffsqtjLGQSAkMZp+sgNsP59eqOexlpFZjpC+9vOwuOCIRyFX+o9qDujPOoikbuwajEPUTMimOyh6tu7fcz0Rn3YDdLp/kRhrS/29MZtmHiD4dFooR0L5aUSfZ9BQQwvQMPixQO70wKBgHB3mrgT3soPon/fLNgdYfjZhadR6ZDv/nMs2QbMQD89CPIPVbnZjqhfiOAMgVPw/aelNPcdxpzDSSCRkg+cGseTUnycuUgRir3qsEU5Lr/2WmG1VN0RgpwKj9uI130OHgGVtXXnlszeN5Rb0vjWczHmw3BpMO6anzT5M/vv2uEZAoGACl/pYrQ0LYSiVE8ioVbs/GbBXviGWIPSrcSjcPOnwj2NSjvQpKr4X99yMstYomAsZ8kPzllw8FB9R3ZIo1hCW9kCgZgS3RXTeNdwnXcawd41mGvvYAo+ReBYlqs/buLRUAWnplUhUwkhqk/d0tD9Met8wSCusEyjYfayYWJzYcE=
         * callback_url : http://cs2.gzldkj.cn/index.php/Home/Inter/addjf
         * aes : WE9fhs8W34YSvgmFtaTCtw==
         * name : 13510801040007522
         * desc : 云众利升级会员
         * title : 升级会员
         * selid : null
         * jifen : null
         * number : A6145209337196207
         */

        private String price;
        private String appid;
        private String key;
        private String callback_url;
        private String aes;
        private String name;
        private String desc;
        private String title;
        private String selid;
        private String jifen;
        private String number;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCallback_url() {
            return callback_url;
        }

        public void setCallback_url(String callback_url) {
            this.callback_url = callback_url;
        }

        public String getAes() {
            return aes;
        }

        public void setAes(String aes) {
            this.aes = aes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSelid() {
            return selid;
        }

        public void setSelid(String selid) {
            this.selid = selid;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
