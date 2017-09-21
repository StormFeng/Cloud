package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.library.BandCardEditText;
import com.lida.cloud.R;
import com.lida.cloud.widght.DialogNotice;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现
 * Created by Administrator on 2017/6/14.
 */

public class ActivityWithdrawals extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etBank)
    EditText etBank;
    @BindView(R.id.etCardNum)
    BandCardEditText etCardNum;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activitywithdrawals);
        ButterKnife.bind(this);
        topbar.setTitle("提现");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String cardNum = etCardNum.getText().toString();
        String bank = etBank.getText().toString();
        String money = etMoney.getText().toString();
        if("".equals(account)){
            UIHelper.t(_activity,"请输入用户名！");
            return;
        }
        if("".equals(bank)){
            UIHelper.t(_activity,"请输入银行卡开户行！");
            return;
        }
        if("".equals(cardNum)){
            UIHelper.t(_activity,"请输入银行卡号！");
            return;
        }
        if("".equals(money)){
            UIHelper.t(_activity,"请输入提现金额！");
            return;
        }
//        new DialogNotice(_activity,"").show();
    }
}
