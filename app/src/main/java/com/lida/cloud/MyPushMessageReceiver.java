package com.lida.cloud;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.midian.base.app.AppContext;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyPushMessageReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid=" + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        Log.d(TAG, responseString);
        System.out.println("onBind绑定成功" + responseString);
        if (errorCode == 0) {
			((AppContext) context.getApplicationContext()).saveDeviceToken(channelId);
            System.out.println("百度云推送绑定成功;:::channelId=" + channelId);
        }
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {
        String notifyString = "onNotificationArrived  title=\"" + title
                + "\" description=\"" + description + "\" customContent="
                + customContentString;
        Log.d(TAG, notifyString);
    }
}
