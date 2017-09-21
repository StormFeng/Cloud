package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceListBean;
import com.lida.cloud.bean.MarkListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 积分明细
 * Created by Administrator on 2017/6/16.
 */

public class ActivityMarkListData extends BaseListDataSource {

    public ActivityMarkListData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        MarkListBean bean = AppUtil.getCloudApiClient(ac).creditdetail(ac.name);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
