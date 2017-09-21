package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BankBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 发卡银行
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityBankListData extends BaseListDataSource {

    public ActivityBankListData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        BankBean bank = AppUtil.getCloudApiClient(ac).bank();
        models.addAll(bank.getData());
        hasMore = false;
        return models;
    }
}
