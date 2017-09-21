package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.library.BandCardEditText;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.DialogNotice;
import com.lida.cloud.widght.DialogNoticeAuth;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityGetMoney extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etBank)
    EditText etBank;
    @BindView(R.id.etCardNum)
    BandCardEditText etCardNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmoney);
        ButterKnife.bind(this);
        topbar.setTitle("提现");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvBalance.setText(ac.getProperty("yue"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.getCloudApiClient(ac).auth(ac.id,new BaseApiCallback(){
            @Override
            public void onApiStart(String tag) {
                super.onApiStart(tag);
                showLoadingDlg();
            }

            @Override
            public void onApiSuccess(NetResult res, String tag) {
                super.onApiSuccess(res, tag);
                hideLoadingDlg();
                if(!res.isOK()){
                    if("yes!".equals(res.getMessage())){
                        new DialogNotice(_activity,"资料正在审核中，请在审核通过后操作！").show();
                        return;
                    }
                    new DialogNoticeAuth(_activity).show();
                }
            }

            @Override
            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                super.onApiFailure(t, errorNo, strMsg, tag);
                hideLoadingDlg();
            }
        });
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String cardNum = etCardNum.getText().toString();
        String bank = etBank.getText().toString();
        String money = etNum.getText().toString();
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
            UIHelper.t(_activity, "请输入提现金额！");
            return;
        }
        if("0".equals(money)){
            UIHelper.t(_activity, "提现金额要大于0且只能是100的整数！");
            return;
        }
        double num = Double.valueOf(money);
        if(num%100!=0 || num<=0){
            UIHelper.t(_activity, "提现金额要大于0且只能是100的整数！");
            return;
        }
        double balance = Double.valueOf(ac.yue);
        if (num > balance) {
            UIHelper.t(_activity, "余额账户不足！");
            return;
        }
//        if (num < 100) {
//            UIHelper.t(_activity, "提现金额不能小于100元！");
//            return;
//        }
//        if (num % 100 != 0) {
//            UIHelper.t(_activity, "提现金额只能是100的倍数！");
//            return;
//        }
        AppUtil.getCloudApiClient(ac).paymoney(ac.name,account,money,cardNum,bank,this);
//        UIHelper.t(_activity, "申请提现成功！");
//        finish();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            new DialogNotice(_activity,"提现申请已提交，审核通过将在48小时内到账！").show();
        }else {
            UIHelper.t(_activity,res.getMessage());
        }
    }
}
