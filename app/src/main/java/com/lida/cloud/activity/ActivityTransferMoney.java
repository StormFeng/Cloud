package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityTransferMoney extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etAccount)
    EditText etAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfermoney);
        ButterKnife.bind(this);
        topbar.setTitle("转账");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnNext)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        UIHelper.jumpForResult(_activity, ActivityTransferMoneyNext.class,bundle, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            finish();
        }
    }
}
