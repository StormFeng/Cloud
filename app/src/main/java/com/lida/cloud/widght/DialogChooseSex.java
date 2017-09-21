package com.lida.cloud.widght;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lida.cloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择性别
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogChooseSex extends Dialog {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private Context context;
    private onTypeSelectedListener listener;

    public DialogChooseSex(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogChooseSex(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public void setTypeSelectedListener(onTypeSelectedListener listener){
        this.listener=listener;
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_choosesex, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton button = (RadioButton) findViewById(checkedId);
                listener.select(button.getText().toString());
                dismiss();
            }
        });
    }

    public interface onTypeSelectedListener{
        void select(String s);
    }
}
