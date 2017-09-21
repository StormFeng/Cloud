package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.OrderInfoUtil2_0;
import com.lida.cloud.alipay.PayResult;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BookBean;
import com.lida.cloud.bean.PayMoneyBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 充值
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityPayMoney extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etAccount)
    EditText etAccount;

    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymoney);
        ButterKnife.bind(this);
        topbar.setTitle("充值");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnNext)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        if("".equals(account)){
            UIHelper.t(_activity,"请输入充值金额！");
            return;
        }
        AppUtil.getCloudApiClient(ac).comemoney(ac.id,account,this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if(res.isOK()){
            PayMoneyBean bean = (PayMoneyBean) res;
            String app_id = bean.getData().getAppid();
            String privateKey = bean.getData().getKey();
            String pay_price = bean.getData().getPrice();
            String pay_desc = bean.getData().getDesc();
            String pay_title = bean.getData().getTitle();
            String order_sn = OrderInfoUtil2_0.getOutTradeNo();
            String notify_url = bean.getData().getCallback_url();
            String orderNo = bean.getData().getNumber();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(new Date());

            Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(app_id,pay_price,timestamp,
                    notify_url, pay_title, pay_desc, orderNo, true);
            String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
            LogUtils.e("params:"+params);
            String sign = OrderInfoUtil2_0.getSign(params, privateKey, true);
            final String orderInfo = orderParam + "&" + sign;
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(_activity);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Log.i("msp", result.toString());
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);

                }
            };
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        UIHelper.t(_activity,"支付成功");
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。]
                        UIHelper.t(_activity,"支付失败，请重试！");
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
