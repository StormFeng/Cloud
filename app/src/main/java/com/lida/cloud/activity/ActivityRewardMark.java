package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityRewardData;
import com.lida.cloud.data.ActivityRewardMarkData;
import com.lida.cloud.tpl.ActivityRewardMarkTpl;
import com.lida.cloud.tpl.ActivityRewardTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 奖励的积分
 * Created by Administrator on 2017/6/2.
 */

public class ActivityRewardMark extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("奖励的积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRewardMarkData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRewardMarkTpl.class;
    }
}
