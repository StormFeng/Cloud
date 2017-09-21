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
 * 商家评论
 * Created by Administrator on 2017/6/2.
 */

public class ActivityComment extends BaseActivity {
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
        setContentView(R.layout.activity_commitcomment);
        ButterKnife.bind(this);
        selid = mBundle.getString("selid");
        topbar.setTitle("评论");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnReport)
    public void onViewClicked() {
        String s = etContent.getText().toString();
        if("".equals(s)){
            UIHelper.t(_activity,"请输入评论内容");
            return;
        }
        AppUtil.getCloudApiClient(ac).comment(selid,s,this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        btnReport.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            UIHelper.t(_activity,"评论成功！");
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        btnReport.setClickable(true);
    }
}
