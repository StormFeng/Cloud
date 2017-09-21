package com.lida.cloud.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.BankBean;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发卡银行
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityBankListTpl extends BaseTpl<BankBean.DataBean> {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityBankListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityBankListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitybanklist;
    }

    @Override
    public void setBean(final BankBean.DataBean bean, int position) {
        if(bean!=null){
            tvName.setText(bean.getBank_name());
            ac.setImage(ivIcon, Constant.IMGBASE+bean.getBank_image());
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("name",tvName.getText().toString());
                    intent.putExtra("bankId",bean.getBank_id());
                    _activity.setResult(Activity.RESULT_OK,intent);
                    _activity.finish();
                }
            });
        }
    }
}
