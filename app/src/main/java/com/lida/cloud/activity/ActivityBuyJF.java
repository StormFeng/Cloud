package com.lida.cloud.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.OrderInfoUtil2_0;
import com.lida.cloud.alipay.PayResult;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BookBean;
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
 * 购买积分
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityBuyJF extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.etNum)
    EditText etNum;

    private String selid;
    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyjf);
        ButterKnife.bind(this);
        selid = mBundle.getString("selid");
        topbar.setTitle("购买积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb1) {
                    etNum.setText(1000+"");
                    tvMoney.setText(125.0+"");
                }else if (checkedId == R.id.rb2) {
                    etNum.setText(10000+"");
                    tvMoney.setText(1250.0+"");
                }else if (checkedId == R.id.rb3) {
                    etNum.setText(100000+"");
                    tvMoney.setText(12500.0+"");
                }else if (checkedId == R.id.rb4) {
                    etNum.setText(1000000+"");
                    tvMoney.setText(125000.0+"");
                }
            }
        });
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ss = s.toString();
                if(!"".equals(ss)){
                    int num = Integer.valueOf(ss);
                    if(num%100==0){
                        //tvMoney.setText(num/5+"");
                        tvMoney.setText(num*0.125+"");
                    }
                }
            }
        });
    }

    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String s = etNum.getText().toString();
        if("".equals(s)){
            UIHelper.t(_activity, "请输入购买积分数量！");
            return;
        }
        int num = Integer.valueOf(s);
        if(num%100!=0){
            UIHelper.t(_activity, "购买积分只能是100的整数！");
            return;
        }

        String seller_id = "2418577831@qq.com";// 商户收款账号
        String app_id = "2017053107390673";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgr6QGefTGQsG5dd6z9mOZhDg8AD1zSNz5Qtr9d40RKWMGTJJeL6h23Enbe7520CjgOj6Blgr9aCVjYN1kPXufgvPBxLtvY+fW1gQsXDay2LNPxdP/HVE7JrAPccJasBa5bNCVp0LsL2MMMhIaOVbm4L9u6vgZ/FPuhY1TuHiTgDt3R2TFVfjBAKeap1oBz2o3dqsVadTtpY4UOpXxloAJYR8cs52YJPgy6xN9ZMYnKOZ9PDCGeGEx/lqPHX8DVHFRbnojBHQuniJCt8nRpCjH2D73W0z8I70WOv9HKbK1dLukxCE1I8HFY5N0dq6PrWJewkyhv4o8Efqh05Tag/sXAgMBAAECggEBAJyYDwWwvHaqgBi5YfuH1rC5RgRTR4+eJptUKA5z/6oF51cwMERRtZ+ANwoKoicv3WYH3Pp0uukSIb2jnJ4fvOZKvDrN+swnVLztfMuuYjARN046wMsSSyOWJGYouvueN8ck4HG3u2RpGeG8uh+MWcBOrsDthDPuz+zXgk9gq0EyW+wZNuvaeS8+SjrMpkl7vMbXdmK0sba0U/ozNbB4zCgIIQSbk5lVv+Mpsj9mqaSCDUfxQoIQsMRnl3pIqr+ge7lfgxEBoFLKykwA/ga/RTWn963axzIMPgEnDw5AGTR+RT3Sxim8XEQufJDMsehvPJL8f2yfEAv+FTXvLRb6D8ECgYEA579p2UIj4VUOpmyT4XaV7YHs5RVyIjvg2VzSuOyO/POCe5lgE84wUXQwp3ua+4RAJxuLtL3MAkgCDfs1aZdZPY3MoFw9jdGoaizQBn5rLrvr460pVDb2amK1GoW5WiOAdDk7fUCZvrD53qw0OnYKxssGvrKIA7q5VTfoQlxymU8CgYEAsYB3c0hApTYTijXTE+VOZrerbAN7hS3O3DIcK/OjuD9tHtIDpQaB1WnkEfX7+IrYQifEjA9l/YI771JpMZ9vOFeavwwZ5dD6immFR51vN39jgk6YLaj2CnNe1hBntNJw+iwfdq0BZvrH8hBCUHx1W72TG/+8rWe/u+RcumDLf7kCgYB2hxjtDH3rbqRqjBfqdctiyVPPKvPP/I4YvffsqtjLGQSAkMZp+sgNsP59eqOexlpFZjpC+9vOwuOCIRyFX+o9qDujPOoikbuwajEPUTMimOyh6tu7fcz0Rn3YDdLp/kRhrS/29MZtmHiD4dFooR0L5aUSfZ9BQQwvQMPixQO70wKBgHB3mrgT3soPon/fLNgdYfjZhadR6ZDv/nMs2QbMQD89CPIPVbnZjqhfiOAMgVPw/aelNPcdxpzDSSCRkg+cGseTUnycuUgRir3qsEU5Lr/2WmG1VN0RgpwKj9uI130OHgGVtXXnlszeN5Rb0vjWczHmw3BpMO6anzT5M/vv2uEZAoGACl/pYrQ0LYSiVE8ioVbs/GbBXviGWIPSrcSjcPOnwj2NSjvQpKr4X99yMstYomAsZ8kPzllw8FB9R3ZIo1hCW9kCgZgS3RXTeNdwnXcawd41mGvvYAo+ReBYlqs/buLRUAWnplUhUwkhqk/d0tD9Met8wSCusEyjYfayYWJzYcE=";// 商户私钥，pkcs8格式
        String pay_price = "0.01";
        String pay_desc = "这是一个测试支付的描述信息";
        String pay_title = "这是一个测试支付的标题";
        String order_sn = OrderInfoUtil2_0.getOutTradeNo();
        String notify_url = "https://api.xx.com/receive_notify.htm";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());


//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(app_id,pay_price,timestamp,
//                notify_url, pay_title, pay_desc, true);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//        LogUtils.e("params:"+params);
//        String sign = OrderInfoUtil2_0.getSign(params, privateKey, true);
//        final String orderInfo = orderParam + "&" + sign;
//        Runnable payRunnable = new Runnable() {
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(_activity);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//                Log.i("msp", result.toString());
//                Message msg = new Message();
//                msg.obj = result;
//            }
//        };
//
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
        AppUtil.getCloudApiClient(ac).addjifen(selid, s, this);
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
            if("addjifen".equals(tag)){
                BookBean bean = (BookBean) res;
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

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
