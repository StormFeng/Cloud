package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.util.DataCleanManager;
import com.lida.cloud.widght.DialogIfSignOut;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.version.VersionUpdateUtil;
import com.midian.base.widget.BaseLibTopbarView;
import com.tencent.bugly.beta.Beta;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivitySetting extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.cbWuRao)
    CheckBox cbWuRao;
    @BindView(R.id.cbNoticeSetting)
    CheckBox cbNoticeSetting;
    @BindView(R.id.tvCacheSize)
    TextView tvCacheSize;
    @BindView(R.id.btnOut)
    TextView btnOut;

    private File cachePath;
    private String cacheSize = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        topbar.setTitle("设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        cachePath = _activity.getCacheDir();
        LogUtils.e(cachePath);
        try {
            cacheSize = DataCleanManager.getCacheSize(cachePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvCacheSize.setText(cacheSize);
    }

    @OnClick({R.id.tvAccountManege, R.id.llClearCache, R.id.btnOut, R.id.tvChangePass, R.id.btnAbout, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvAccountManege:
                startActivityForResult(new Intent(_activity, ActivityPersonalData.class), 1001);
                break;
            case R.id.llClearCache:
                DataCleanManager.deleteFolderFile(cachePath.getPath(),false);
                UIHelper.t(_activity,"清除成功！");
                tvCacheSize.setText("");
                break;
            case R.id.btnOut:
                new DialogIfSignOut(_activity).show();
                break;
            case R.id.tvChangePass:
                UIHelper.jump(_activity,ActivityFindPass.class);
                break;
            case R.id.btnAbout:
                UIHelper.jump(_activity,ActivityAboutUs.class);
                break;
            case R.id.btnUpdate:
                Beta.checkUpgrade();
                break;
        }
    }
}
