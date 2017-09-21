package com.lida.cloud.tpl;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BalanceListBean;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 余额明细
 * Created by Administrator on 2017/6/16.
 */

public class ActivityBalanceListTpl extends BaseTpl<BalanceListBean.DataBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivityBalanceListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityBalanceListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitybalancelist;
    }

    @Override
    public void setBean(BalanceListBean.DataBean bean, int position) {
        String[] split = bean.getZhi().split(",");
        String type = split[2];
        tvTime.setText(split[0]);
        tvType.setText(type);
        if("返还".equals(type)||"充值".equals(type)||"奖励".equals(type)||type.contains("转入")){
            tvMoney.setText("+"+split[1]);
            tvMoney.setTextColor(Color.parseColor("#33CC33"));
        }else if("提现".equals(type)||"转账".equals(type)||"转出".equals(type)||type.contains("转出")){
            tvMoney.setText("-"+split[1]);
            tvMoney.setTextColor(Color.parseColor("#363636"));
        }
    }
}
