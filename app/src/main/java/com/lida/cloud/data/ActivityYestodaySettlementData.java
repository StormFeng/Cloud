package com.lida.cloud.data;

import android.app.Activity;
import android.content.Context;

import com.lida.cloud.activity.ActivityYestodaySettlement;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BalanceBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityYestodaySettlementData extends BaseListDataSource {

    public ActivityYestodaySettlementData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        final BalanceBean bean = AppUtil.getCloudApiClient(ac).balance();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData().getBan());
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(bean.getData().getMoney().get(0).getMo()==null){
                    ActivityYestodaySettlement.tvAllMoney.setText("0.0");
                }else{
                    ActivityYestodaySettlement.tvAllMoney.setText(bean.getData().getMoney().get(0).getMo());
                }
            }
        });
        hasMore = false;
        return models;
    }
}
