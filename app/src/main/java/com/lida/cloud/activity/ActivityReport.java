package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
 * Created by Administrator on 2017/6/2.
 */

public class ActivityReport extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.btnReport)
    Button btnReport;

    private String selid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        selid = mBundle.getString("selid");
        topbar.setTitle("举报");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnReport)
    public void onViewClicked() {
        String s = etContent.getText().toString();
        if("".equals(s)){
            UIHelper.t(_activity,"请输入举报内容");
            return;
        }
        AppUtil.getCloudApiClient(ac).tip(selid,s,this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            UIHelper.t(_activity,"举报成功！");
            finish();
        }
    }
}
