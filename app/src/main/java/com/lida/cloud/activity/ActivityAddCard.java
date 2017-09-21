package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.library.BandCardEditText;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityAddCard extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.tvChooseBank)
    TextView tvChooseBank;
    @BindView(R.id.etCardNumber)
    BandCardEditText etCardNumber;
    @BindView(R.id.etName)
    EditText etName;

    private String bankId = "";
    private String number = "";
    private String bank = "";
    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        ButterKnife.bind(this);
        topbar.setTitle("添加银行卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.llChooseBank, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llChooseBank:
                UIHelper.jumpForResult(_activity, ActivityBankList.class, 1001);
                break;
            case R.id.btnConfirm:
                number = etCardNumber.getText().toString();
                bank = tvChooseBank.getText().toString();
                name = etName.getText().toString();
                if("".equals(number)){
                    UIHelper.t(_activity, "银行卡卡号不能为空！");
                    return;
                }
                if(etCardNumber.checkBankCard(number)){
                    UIHelper.t(_activity, "银行卡卡号不正确！");
                    return;
                }
                if("".equals(bank)){
                    UIHelper.t(_activity, "请选择发卡银行！");
                    return;
                }
                if("".equals(name)){
                    UIHelper.t(_activity, "请填写持卡人姓名！");
                    return;
                }
                LogUtils.e(number);
                String s = number.replaceAll(" ", "");
                AppUtil.getCloudApiClient(ac).bankcard(bankId, s, name, this);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            UIHelper.t(_activity, "银行卡添加成功！");
            setResult(RESULT_OK);
            finish();
        }else{
            UIHelper.t(_activity, "添加失败！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            LogUtils.e(data.getStringExtra("name"));
            tvChooseBank.setText(data.getStringExtra("name"));
            bankId = data.getStringExtra("bankId");
        }
    }
}
