package com.lida.cloud.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.LoginBean;
import com.lida.cloud.widght.EditTextWithDeleteButton;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登陆页
 */
public class ActivityLogin extends BaseActivity {

    @BindView(R.id.et_pass)
    EditTextWithDeleteButton etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_name)
    EditTextWithDeleteButton etName;
    @BindView(R.id.tvRegister)
    TextView tvRegister;

    private long waitTime = 2000;
    private long touchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ac.isUserIdExsit()) {
            onDestroy();
            LogUtils.e(ac.isUserIdExsit());
            UIHelper.jump(_activity,MainActivity.class);
        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            etPass.setPassType();
        }
    }

    @OnClick({R.id.btn_login, R.id.tvRegister, R.id.tvForgetPass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                if ("".equals(name)) {
                    AnimatorUtils.onVibrationView(etName);
                    UIHelper.t(_activity, "请输入用户名");
                    return;
                }
                if ("".equals(pass)) {
                    AnimatorUtils.onVibrationView(etPass);
                    UIHelper.t(_activity, "请输入密码");
                    return;
                }
                AppUtil.getCloudApiClient(ac).login(name,pass,this);
                break;
            case R.id.tvRegister:
                UIHelper.jump(_activity,ActivityRegister.class);
                break;
            case R.id.tvForgetPass:
                UIHelper.jump(_activity,ActivityForgetPass.class);
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnLogin.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        hideLoadingDlg();
        btnLogin.setClickable(true);
        if (res.isOK()) {
            LoginBean bean = (LoginBean) res;
            ac.saveUserInfo(bean.getData().getMem_tx(),bean.getData().getMemid(),bean.getData().getMem_name(),bean.getData().getMem_name(), bean.getData().getMem_tel(),
                    bean.getData().getMem_tx(), bean.getData().getMem_state(), bean.getData().getMem_sex(),bean.getData().getMem_add(),
                    bean.getData().getMem_email(),bean.getData().getMem_jifen(),bean.getData().getMem_yue(),bean.getData().getMem_noyue());
            UIHelper.t(_activity, res.getMessage());
            UIHelper.jump(_activity,MainActivity.class);
            finish();
        } else {
            AnimatorUtils.onVibrationView(btnLogin);
            UIHelper.t(_activity, res.getMessage());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnLogin.setClickable(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
                long currentTime = System.currentTimeMillis();
                if ((currentTime - touchTime) >= waitTime) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    touchTime = currentTime;
                } else {
                    AppManager.getAppManager().finishAllActivity();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
