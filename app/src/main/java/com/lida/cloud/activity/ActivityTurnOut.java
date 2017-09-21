package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
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
 * 转出积分
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityTurnOut extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    private String integral;//接收上个页面存库积分

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnout);
        ButterKnife.bind(this);
        integral = mBundle.getString("integral");
        topbar.setTitle("转出积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String number = etNumber.getText().toString();
        if("".equals(account)||"".equals(number)){
            UIHelper.t(_activity,"信息不完整，请确认账号和积分正确！");
            return;
        }
        double i = 0.0;
        if(integral!=null&&!"".equals(integral)){
            i = Double.valueOf(integral);//库存积分
        }
        double j = Double.valueOf(number);//转出积分
        if(i<j){
            LogUtils.e(i);
            LogUtils.e(j);
            UIHelper.t(_activity,"库存积分不足，请充值后重试！");
            return;
        }
        AppUtil.getCloudApiClient(ac).out(mBundle.getString("selid"),account,number,this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnConfirm.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if(res.isOK()){
            if("yes!".equals(res.getMessage())){
                UIHelper.t(_activity,"转出积分成功！");
                finish();
            }
            if("no!".equals(res.getMessage())){
                UIHelper.t(_activity,"转账用户不存在！");
                return;
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnConfirm.setClickable(true);
    }
}
