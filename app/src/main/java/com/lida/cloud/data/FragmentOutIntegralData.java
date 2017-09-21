package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.KCJFBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentOutIntegralData extends BaseListDataSource {

    private String selid;

    public FragmentOutIntegralData(Context context, String selid) {
        super(context);
        this.selid = selid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        final KCJFBean bean = AppUtil.getCloudApiClient(ac).jifen(selid);
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData().getIl());
        hasMore = false;
        return models;
    }

}
