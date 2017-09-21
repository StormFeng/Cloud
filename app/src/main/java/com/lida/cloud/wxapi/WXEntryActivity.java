package com.lida.cloud.wxapi;

import com.apkfuns.logutils.LogUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * Created by Administrator on 2017/5/31.
 */

public class WXEntryActivity extends WXCallbackActivity {
    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);
        LogUtils.e(resp);
        if(resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            LogUtils.e("onPayFinish,erroeCode= "+resp.errStr);
        }
    }
}
