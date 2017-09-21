package com.lida.cloud.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityMyCardData;
import com.lida.cloud.tpl.ActivityMyCardTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的银行卡
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityMyCard extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("我的银行卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        listView.setDivider(null);
        listView.setBackgroundColor(Color.WHITE);
        listView.setPadding(0,Func.Dp2Px(_activity,10),0,0);
        listView.setDividerHeight(Func.Dp2Px(_activity,10));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityMyCardData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityMyCardTpl.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            listViewHelper.refresh();
        }
    }
}
