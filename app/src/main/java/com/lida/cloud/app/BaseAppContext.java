package com.lida.cloud.app;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.mapapi.SDKInitializer;
import com.lida.cloud.activity.MainActivity;
import com.midian.base.app.AppContext;
import com.lida.cloud.util.ShareUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class BaseAppContext extends AppContext {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtil.init();
        CloudApiClient.init(this);
        SDKInitializer.initialize(this);
        startPush();
        initTenxunUpdate();
//        ZXingLibrary.initDisplayOpinion(this);
    }


    /**
     * 腾讯自动升级
     */
    private void initTenxunUpdate(){
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Bugly.init(getApplicationContext(), "bdab963a59", true);
    }

    /**
     * 启动推送
     */
    public void startPush() {
        System.out.println("startWork---启动推送");
        PushSettings.enableDebugMode(getApplicationContext(),true);
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "5koxQ4h3dLzqTOkVRrYdInhQUZ9kdxMd");
    }

    public void changePush() {
        if (isClosePush) {
            stopPush();
        } else {
            PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "5koxQ4h3dLzqTOkVRrYdInhQUZ9kdxMd");
        }
    }

    /**
     * 停用推送
     */
    public void stopPush() {
        System.out.println("stopPush");
        PushManager.stopWork(getApplicationContext());
    }
}
