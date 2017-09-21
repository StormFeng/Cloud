package com.lida.cloud.data;

import android.content.Context;

import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 已完成订单
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentBookCompleteData extends BaseListDataSource {

    public FragmentBookCompleteData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            models.add(new NetResult());
        }
        hasMore = false;
        return models;
    }
}
