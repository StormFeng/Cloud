package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityBalanceListData;
import com.lida.cloud.tpl.ActivityBalanceListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ActivityBalanceDetailList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("余额明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityBalanceListData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityBalanceListTpl.class;
    }
}
