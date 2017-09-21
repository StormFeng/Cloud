package com.lida.cloud.wxapi;

import com.apkfuns.logutils.LogUtils;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by Administrator on 2017/5/31.
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler{

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.e(baseResp.errCode);
        LogUtils.e(baseResp.errStr);
        if(baseResp.errCode == 0){
            UIHelper.t(_activity,"支付成功");
        }else{
            UIHelper.t(_activity,"支付失败，请重试");
        }
        finish();
    }
}
