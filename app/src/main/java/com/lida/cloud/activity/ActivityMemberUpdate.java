package com.lida.cloud.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterUpdate;
import com.lida.cloud.alipay.OrderInfoUtil2_0;
import com.lida.cloud.alipay.PayResult;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.PriceBean;
import com.lida.cloud.bean.UpdateBean;
import com.lida.cloud.widght.InnerGridView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员升级
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityMemberUpdate extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.ivType)
    ImageView ivType;
    @BindView(R.id.gridView)
    InnerGridView gridView;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.btnOk)
    Button btnOk;

    private AdapterUpdate adapterUpdate;
    private List<PriceBean.DataBean> data = new ArrayList<>();
    private PriceBean bean;

    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberupdate);
        ButterKnife.bind(this);
        LogUtils.e("mem_staus:"+ac.getProperty("mem_staus"));
        if ("0".equals(ac.getProperty("mem_staus"))) {
            tvType.setText("普通用户");
            ivType.setImageResource(R.drawable.icon_low);
        } else if ("1".equals(ac.mem_staus)) {
            tvType.setText("银钻用户");
            ivType.setImageResource(R.drawable.icon_silver);
        } else if ("2".equals(ac.getProperty("mem_staus"))) {
            tvType.setText("金钻用户");
            ivType.setImageResource(R.drawable.icon_gold);
        }
        topbar.setTitle("会员升级");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        ac.setImage(ivHead, Constant.IMGBASEURLPB + ac.getProperty("head_img"));
        AppUtil.getCloudApiClient(ac).price(this);
        adapterUpdate = new AdapterUpdate(_activity, data);
        gridView.setAdapter(adapterUpdate);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnOk.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnOk.setClickable(true);
        if (res.isOK()) {
            if("price".equals(tag)){
                bean = (PriceBean) res;
                if ("0".equals(ac.getProperty("mem_staus"))) {
                    data.addAll(bean.getData());
                } else if ("1".equals(ac.getProperty("mem_staus"))) {
                    data.add(bean.getData().get(1));
                    data.add(bean.getData().get(2));
                    data.add(bean.getData().get(3));
                } else if ("2".equals(ac.getProperty("mem_staus"))) {
                    data.add(bean.getData().get(2));
                    data.add(bean.getData().get(3));
                }
                adapterUpdate.notifyDataSetChanged();
            }
            if("addlv".equals(tag)){
                UpdateBean bean = (UpdateBean) res;
                if("king!".equals(res.getMessage())){
                    UIHelper.t(_activity,"升级成功！");
                    setResult(RESULT_OK);
                    finish();
                    return;
                }
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
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        String grade="";
        if(bean.getData().get(0).isSelect()){//银钻
            grade = "1";
        }else if(bean.getData().get(1).isSelect()){//金钻
            grade = "2";
        }else if(bean.getData().get(2).isSelect()){//商户
            grade = "3";
        }else if(bean.getData().get(3).isSelect()){//商户
            grade = "4";
        }
        AppUtil.getCloudApiClient(ac).addlv(ac.id,grade,this);
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
                        UIHelper.t(_activity,"支付失败");
                        finish();
                    }
                    break;
            }
        }
    };
}
