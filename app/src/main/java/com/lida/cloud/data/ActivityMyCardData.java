package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BankCardBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 我的银行卡
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityMyCardData extends BaseListDataSource {

    public ActivityMyCardData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        BankCardBean bean = AppUtil.getCloudApiClient(ac).card();
        for (int i = 0; i < bean.getData().size(); i++) {
            bean.getData().get(i).setLast(false);
            models.add(bean.getData().get(i));
        }
        BankCardBean.DataBean lastBean = new BankCardBean.DataBean();
        lastBean.setLast(true);
        models.add(lastBean);
        hasMore = false;
        return models;
    }
}
