package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RecommendBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 推荐的会员
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRecommendMemData extends BaseListDataSource {

    public ActivityRecommendMemData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        RecommendBean bean = AppUtil.getCloudApiClient(ac).commend();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData().getMemli());
        hasMore = false;
        return models;
    }
}
