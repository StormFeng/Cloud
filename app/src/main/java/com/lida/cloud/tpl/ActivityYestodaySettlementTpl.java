package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BalanceBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 昨日结算
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityYestodaySettlementTpl extends BaseTpl<BalanceBean.DataBean.BanBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivityYestodaySettlementTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityYestodaySettlementTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityyestodaysettlement;
    }

    @Override
    public void setBean(BalanceBean.DataBean.BanBean bean, int position) {
        if(bean!=null){
            tvMoney.setText(bean.getBa_money());
            tvTime.setText(bean.getBa_time());
        }
    }
}
