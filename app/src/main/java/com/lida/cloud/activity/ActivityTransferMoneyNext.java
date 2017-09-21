package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityTransferMoneyNext extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    private String account = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfermoneynext);
        ButterKnife.bind(this);
        tvName.setText(ac.mem_nc);
        ac.setImage(ivHead, Constant.IMGBASEURLPB + ac.mem_tx);
        topbar.setTitle("转账");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        account = mBundle.getString("account");
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String money = etMoney.getText().toString();
        if("".equals(money)){
            UIHelper.t(_activity, "请输入转账金额！");
            return;
        }
        if("0".equals(money)){
            UIHelper.t(_activity, "转账金额要大于0且只能是100的整数！");
            return;
        }
        int num = Integer.valueOf(money);
        if(num%100!=0 || num<=0){
            UIHelper.t(_activity, "转账金额要大于0且只能是100的整数！");
            return;
        }
        AppUtil.getCloudApiClient(ac).zz(ac.id,account,num+"",this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if(res.isOK()){
            setResult(RESULT_OK);
            finish();
            UIHelper.t(_activity, "转账成功！");
        }else{
            UIHelper.t(_activity,"转账用户不存在！");
            return;
        }
    }
}
