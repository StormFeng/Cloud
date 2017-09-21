package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.data.ActivityYestodaySettlementData;
import com.lida.cloud.tpl.ActivityYestodaySettlementTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityYestodaySettlement extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    public static TextView tvAllMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvAllMoney = (TextView) findViewById(R.id.tvAllMoney);
        topbar.setTitle("累计结算");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yestodaysettlement;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityYestodaySettlementData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityYestodaySettlementTpl.class;
    }
}
