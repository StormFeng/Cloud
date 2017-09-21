package com.lida.cloud.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置新密码
 * Created by WeiQingFeng on 2017/5/9.
 */

public class ActivityForgetPass extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.etPass)
    EditText etPass;

    private CountDownTimer mCountDownTimer;

    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("设置新密码");
    }

    @OnClick({R.id.btnCode, R.id.btnOk})
    public void onViewClicked(View view) {
        String phone = etPhone.getText().toString();
        String code = etCode.getText().toString();
        pass = etPass.getText().toString();
        switch (view.getId()) {
            case R.id.btnCode:
                if (!Func.isMobileNO(phone)) {
                    UIHelper.t(_activity, "手机号码格式不正确！");
                    return;
                }
                AppUtil.getCloudApiClient(ac).code(phone, this);
                break;
            case R.id.btnOk:
                if ("".equals(phone)) {
                    UIHelper.t(_activity, "请输入手机号码");
                    return;
                }
                if ("".equals(code)) {
                    UIHelper.t(_activity, "请输入验证码");
                    return;
                }
                if("".equals(pass)){
                    UIHelper.t(_activity, "请输入新的密码");
                    return;
                }
                AppUtil.getCloudApiClient(ac).pdcode(code, this);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("code".equals(tag)) {
                UIHelper.t(_activity, "发送成功！");
                downTime();
            }
            if ("pdcode".equals(tag)) {
                if ("no!".equals(res.getMessage())) {
                    UIHelper.t(_activity, "验证码不正确！");
                    AnimatorUtils.onVibrationView(btnOk);
                    return;
                }
                AppUtil.getCloudApiClient(ac).forgetPass(etPhone.getText().toString(),etPass.getText().toString(),this);
            }
            if("forgetPass".equals(tag)){
                UIHelper.t(_activity, "修改密码成功！");
                finish();
            }
        }
    }

    private void downTime() {
        mCountDownTimer = new CountDownTimer(59 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timeText = "秒";
                btnCode.setEnabled(false);
                btnCode.setText(millisUntilFinished / 1000 + timeText);
            }

            @Override
            public void onFinish() {
                btnCode.setEnabled(true);
                btnCode.setText("验证码");
            }
        };
        mCountDownTimer.start();
    }
}
