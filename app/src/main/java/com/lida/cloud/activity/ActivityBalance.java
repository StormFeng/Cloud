package com.lida.cloud.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.MemDetailBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 余额
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityBalance extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvYuE)
    TextView tvYuE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        topbar.setTitle("余额");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCloudApiClient(ac).getPersonalDataInfo(ac.id,this);
    }

    @OnClick({R.id.llRecharge, R.id.llGetMoney, R.id.llTransferMoney, R.id.tvDetailList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRecharge:
//                UIHelper.jump(_activity, ActivityWithdrawals.class);
                UIHelper.jumpForResult(_activity, ActivityPayMoney.class,1001);
                break;
            case R.id.llGetMoney:
                UIHelper.jump(_activity, ActivityGetMoney.class);
                break;
            case R.id.llTransferMoney:
                UIHelper.jump(_activity, ActivityTransferMoney.class);
                break;
            case R.id.tvDetailList:
                UIHelper.jump(_activity, ActivityBalanceDetailList.class);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            MemDetailBean bean = (MemDetailBean) res;
            tvYuE.setText(bean.getData().get(0).getMem_yue());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK==resultCode){
            AppUtil.getCloudApiClient(ac).getPersonalDataInfo(ac.id,this);
        }
    }
}
