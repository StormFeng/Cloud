package com.lida.cloud.activity;

import android.os.Bundle;

import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员信呗权益
 * Created by Administrator on 2017/6/16.
 */

public class ActivityLoan extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        ButterKnife.bind(this);
        topbar.setTitle("会员信呗权益");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }
}
