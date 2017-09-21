package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;

import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商户中心
 * Created by WeiQingFeng on 2017/5/20.
 */

public class ActivityApplyToShop extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applytoshop);
        ButterKnife.bind(this);
        topbar.setTitle("商户中心");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnApply)
    public void onViewClicked() {
        UIHelper.jumpForResult(_activity,ActivityShopAuth.class,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            finish();
        }
    }
}
