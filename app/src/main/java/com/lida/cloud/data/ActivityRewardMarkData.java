package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RewardBean;
import com.lida.cloud.bean.RewardMarkBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 奖励的积分
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRewardMarkData extends BaseListDataSource {

    public ActivityRewardMarkData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        RewardMarkBean bean = AppUtil.getCloudApiClient(ac).clickcredit();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
