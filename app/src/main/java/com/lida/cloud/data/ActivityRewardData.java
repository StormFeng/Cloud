package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RewardBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 奖励的金额
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRewardData extends BaseListDataSource {

    public ActivityRewardData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        RewardBean bean = AppUtil.getCloudApiClient(ac).clickmoney();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
