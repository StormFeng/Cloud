package com.lida.cloud.widght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 更改手机号
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogChangePhone extends Dialog {

    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnOk)
    Button btnOk;

    private Context context;
    private onTelSelect listener;
    private String tel;
    private String code;
    private AppContext ac;

    private CountDownTimer mCountDownTimer;

    public DialogChangePhone(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogChangePhone(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public void setOnTelSelect(onTelSelect listener) {
        this.listener = listener;
    }

    private void init(Context context) {
        this.context = context;
        ac = (AppContext) ((Activity)context).getApplication();
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_changephone, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>=4){
                    btnOk.setEnabled(true);
                }else {
                    btnOk.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.btnCode, R.id.btnOk})
    public void onViewClicked(View view) {
        tel = etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.btnCode:
                if(!Func.isMobileNO(tel)){
                    UIHelper.t(context, "手机号格式不正确！");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                AppUtil.getCloudApiClient(ac).code(tel, new BaseApiCallback(){
                    @Override
                    public void onApiSuccess(NetResult res, String tag) {
                        super.onApiSuccess(res, tag);
                        if(res.isOK()){
                            downTime();
                        }
                    }
                });
                break;
            case R.id.btnOk:
                code = etCode.getText().toString();
                if(!Func.isMobileNO(tel)){
                    UIHelper.t(context, "手机号格式不正确！");
                    AnimatorUtils.onVibrationView(etPhone);
                    return;
                }
                if("".equals(code)){
                    UIHelper.t(context, "请输入验证码！");
                    AnimatorUtils.onVibrationView(etCode);
                    return;
                }
                if(listener!=null){
                    listener.onTelSelect(tel, code);
                }
                break;
        }
    }

    public interface onTelSelect {
        void onTelSelect(String phone, String code);
    }

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
}
