package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 余额明细
 * Created by Administrator on 2017/6/16.
 */

public class ActivityBalanceListData extends BaseListDataSource {

    public ActivityBalanceListData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        BalanceListBean bean = AppUtil.getCloudApiClient(ac).getBalanceList(ac.name);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
