package com.lida.cloud.activity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.AboutUsBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于我们
 * Created by Administrator on 2017/6/3.
 */

public class ActivityAboutUs extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        topbar.setTitle("关于云众利");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        AppUtil.getCloudApiClient(ac).aboutUs(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            AboutUsBean bean = (AboutUsBean) res;
            webView.loadUrl("http://"+bean.getData().getUrl());
        }
    }
}
