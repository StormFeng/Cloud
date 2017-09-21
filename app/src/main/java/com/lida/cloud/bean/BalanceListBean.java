package com.lida.cloud.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 余额明细
 * Created by WeiQingFeng on 2017/5/16.
 */

public class BalanceListBean extends NetResult {

    private List<DataBean> data;

    public static BalanceListBean parse(String json) throws AppException {
        BalanceListBean res = new BalanceListBean();
        try{
            res = gson.fromJson(json, BalanceListBean.class);
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
         * zhi : 2017-07-05 10:50:02,100.00,充值
         */

        private String zhi;

        public String getZhi() {
            return zhi;
        }

        public void setZhi(String zhi) {
            this.zhi = zhi;
        }
    }
}
