package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivitySearchReslutBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/7.
 */

public class ActivitySearchResultData extends BaseListDataSource {

    private String con = "";

    public ActivitySearchResultData(Context context, String con) {
        super(context);
        this.con = con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public ActivitySearchResultData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ActivitySearchReslutBean bean = AppUtil.getCloudApiClient(ac).search(con);
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
