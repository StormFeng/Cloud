package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RecommendBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 推荐的商户
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRecommendShopData extends BaseListDataSource {

    public ActivityRecommendShopData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        RecommendBean bean = AppUtil.getCloudApiClient(ac).commend();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData().getShopli());
        hasMore = false;
        return models;
    }
}
