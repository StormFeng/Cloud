package com.lida.cloud.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.alipay.OrderInfoUtil2_0;
import com.lida.cloud.alipay.PayResult;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RegisterBean;
import com.lida.cloud.widght.EditTextWithDeleteButton;
import com.lida.cloud.wxapi.WXConstants;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRegister extends BaseActivity {


    @BindView(R.id.etAccount)
    EditTextWithDeleteButton etAccount;
    @BindView(R.id.etPhone)
    EditTextWithDeleteButton etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.etPassAgain)
    EditText etPassAgain;
    @BindView(R.id.etName)
    EditTextWithDeleteButton etName;
    @BindView(R.id.etID)
    EditTextWithDeleteButton etID;
    @BindView(R.id.cbVip)
    RadioButton cbVip;
    @BindView(R.id.cbNBVip)
    RadioButton cbNBVip;
    @BindView(R.id.etRecomment)
    EditTextWithDeleteButton etRecomment;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.cbShop)
    RadioButton cbShop;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.cbOrdinary)
    RadioButton cbOrdinary;
    @BindView(R.id.cbWeShop)
    RadioButton cbWeShop;
    private String str0 = "<font color='#7D7C7C' size='15'>普通用户</font>&nbsp<font color='#FA2220' size='15'>0元</font>";
    private String str1 = "<font color='#7D7C7C' size='15'>银钻用户</font>&nbsp<font color='#FA2220' size='15'>100元</font>";
    private String str2 = "<font color='#7D7C7C' size='15'>金钻用户</font>&nbsp<font color='#FA2220' size='15'>1000元</font>";
    private String str3 = "<font color='#7D7C7C' size='15'>实体商户</font>&nbsp<font color='#FA2220' size='15'>1000元</font>";
    private String str4 = "<font color='#7D7C7C' size='15'>微商商户</font>&nbsp<font color='#FA2220' size='15'>1000元</font>";

    private String grade = "0";
    private CountDownTimer mCountDownTimer;
    private String phone;

    private static final int SDK_PAY_FLAG = 1;

    //判断密码是否是数字加字母正则
    public final static Pattern numerals = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$");

    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        cbOrdinary.setText(Html.fromHtml(str0));
        cbVip.setText(Html.fromHtml(str1));
        cbNBVip.setText(Html.fromHtml(str2));
        cbShop.setText(Html.fromHtml(str3));
        cbWeShop.setText(Html.fromHtml(str4));
//        cbOrdinary.setOnCheckedChangeListener(onCheckedChangeListener);
//        cbVip.setOnCheckedChangeListener(onCheckedChangeListener);
//        cbNBVip.setOnCheckedChangeListener(onCheckedChangeListener);
//        cbShop.setOnCheckedChangeListener(onCheckedChangeListener);

        etAccount.setDigits(getString(R.string.digits));
        etPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        etCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        etID.setDigits(getString(R.string.cardNum));
        etRecomment.setDigits(getString(R.string.digits));
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                cbOrdinary.setChecked(false);
                cbVip.setChecked(false);
                cbNBVip.setChecked(false);
                cbShop.setChecked(false);
            }
            buttonView.setChecked(isChecked);
        }
    };


    @OnClick({R.id.btnCode, R.id.btnRegister, R.id.tvAgreement})
    public void onViewClicked(View view) {
        phone = etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.btnCode:
                if ("".equals(phone)) {
                    UIHelper.t(_activity, "手机号不能为空!");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if (!Func.isMobileNO(phone)) {
                    UIHelper.t(_activity, "手机号格式不正确！");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                AppUtil.getCloudApiClient(ac).code(phone, this);
                break;
            case R.id.tvAgreement:
                UIHelper.jump(_activity, ActivityAgreement.class);
                break;
            case R.id.btnRegister:
                if ("".equals(etAccount.getText())) {
                    UIHelper.t(_activity, "用户名不能为空！");
                    AnimatorUtils.onVibrationView(etAccount);
                    return;
                }
                if ("".equals(etPhone.getText())) {
                    UIHelper.t(_activity, "手机号不能为空！");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if (!Func.isMobileNO(etPhone.getText())) {
                    UIHelper.t(_activity, "手机号格式不正确！");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if ("".equals(etCode.getText().toString().trim())) {
                    UIHelper.t(_activity, "验证码不能为空！");
                    AnimatorUtils.onVibrationView(etCode);
                    return;
                }
                if ("".equals(etPass.getText().toString().trim())) {
                    UIHelper.t(_activity, "密码不能为空！");
                    AnimatorUtils.onVibrationView(etPass);
                    return;
                }



                if (etPass.getText().toString().trim().length() < 6) {
                    UIHelper.t(_activity, "密码不能少于六位！");
                    AnimatorUtils.onVibrationView(etPass);
                    return;
                }
                if (!(etPassAgain.getText().toString().trim()).equals(etPass.getText().toString().trim())) {
                    UIHelper.t(_activity, "密码不一致！");
                    AnimatorUtils.onVibrationView(etPass);
                    AnimatorUtils.onVibrationView(etPassAgain);
                    return;
                }
                if(isNumeralsNO(etPass.getText().toString().trim())==false){
                    UIHelper.t(_activity,"密码必须数字与英文结合");
                    return;
                }

//                if ("".equals(etName.getText())) {
//                    UIHelper.t(_activity, "姓名不能为空！");
//                    AnimatorUtils.onVibrationView(etName);
//                    return;
//                }
//                if ("".equals(etID.getText())) {
//                    UIHelper.t(_activity, "身份证号不能为空！");
//                    AnimatorUtils.onVibrationView(etID);
//                    return;
//                }
                if (!cb.isChecked()) {
                    UIHelper.t(_activity, "请先阅读《云众利用户注册协议》！");
                    return;
                }
                if (cbOrdinary.isChecked()) {
                    grade = "0";
                } else if (cbVip.isChecked()) {
                    grade = "1";
                } else if (cbNBVip.isChecked()) {
                    grade = "2";
                } else if (cbShop.isChecked()) {
                    grade = "3";
                } else if (cbWeShop.isChecked()){
                    grade = "4";
                }
                AppUtil.getCloudApiClient(ac).pdcode(etCode.getText().toString(), this);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("pdcode".equals(tag)) {
                if ("no!".equals(res.getMessage())) {
                    UIHelper.t(_activity, "验证码不正确！");
                    AnimatorUtils.onVibrationView(btnRegister);
                    return;
                }
                AppUtil.getCloudApiClient(ac).register(etAccount.getText().toString(), etPass.getText().toString(), phone,
                        etCode.getText().toString(), etName.getText().toString(), etID.getText().toString(), etRecomment.getText().toString(),
                        grade, this);
            }
            if ("register".equals(tag)) {
                RegisterBean bean = (RegisterBean) res;
                if ("0".equals(bean.getData().getPrice())) {
                    UIHelper.t(_activity, "注册成功！");
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
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(app_id, pay_price, timestamp,
                        notify_url, pay_title, pay_desc, orderNo, true);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                LogUtils.e("params:" + params);
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
            if ("code".equals(tag)) {
                UIHelper.t(_activity, "验证码发送成功！");
                downTime();
            }
        } else {
            UIHelper.t(_activity, res.message);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        UIHelper.t(_activity, "注册成功！");
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。]
                        UIHelper.t(_activity, "支付失败,请重试");
                    }
                    break;
            }
        }
    };

    private void downTime() {
        mCountDownTimer = new CountDownTimer(59 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timeText = "秒";
                btnCode.setEnabled(false);
                btnCode.setText(millisUntilFinished / 1000 + timeText);
            }

            @Override
            public void onFinish() {
                btnCode.setEnabled(true);
                btnCode.setText("验证码");
            }
        };
        mCountDownTimer.start();
    }

    public static boolean isNumeralsNO(String password) {

        return numerals.matcher(password).matches();
    }

    /**
     * 调用微信支付
     */
    private void toPayWX(){
        iwxapi = WXAPIFactory.createWXAPI(_activity, WXConstants.APP_ID);
        iwxapi.registerApp(WXConstants.APP_ID);//将应用注册到微信
        if(isWXAppInstalledAndSupported(iwxapi)==false){
            UIHelper.t(_activity,"请先安装微信客户端");
            return;
        }
        loadWXPayOL();
    }

    /**
     *  判断是否安装了微信客户端
     */
    private boolean isWXAppInstalledAndSupported(IWXAPI msgApi) {

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();
        return sIsWXAppInstalledAndSupported;
    }

    /**
     * 获取微信预支付订单数据
     */
    private void loadWXPayOL(){

    }


}
