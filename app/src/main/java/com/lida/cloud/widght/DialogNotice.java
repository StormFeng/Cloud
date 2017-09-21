package com.lida.cloud.widght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityLogin;
import com.lida.cloud.activity.MainActivity;
import com.midian.base.app.AppContext;
import com.midian.base.app.AppManager;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现提醒
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogNotice extends Dialog {

    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;

    private Context context;
    private Activity activity;
    private String str;

    public DialogNotice(Context context, String str) {
        super(context, R.style.diy_dialog);
        this.str = str;
        init(context);
    }

    public DialogNotice(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_notice, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tv.setText(str);
    }

    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                dismiss();
                ((Activity) context).finish();
                break;
        }
    }
}
