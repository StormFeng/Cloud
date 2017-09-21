package com.lida.cloud.tpl;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BalanceListBean;
import com.lida.cloud.bean.MarkListBean;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 信用积分明细
 * Created by Administrator on 2017/6/16.
 */

public class ActivityMarkListTpl extends BaseTpl<MarkListBean.DataBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivityMarkListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityMarkListTpl(Context context) {
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
    public void setBean(MarkListBean.DataBean bean, int position) {
        if(bean!=null){
            if("1".equals(bean.getJf_state())||"3".equals(bean.getJf_state())
                    ||"4".equals(bean.getJf_state())){
                tvType.setText("收入");
                tvTime.setText(bean.getJf_time());
                tvMoney.setText("+"+bean.getJf_jf());
                tvMoney.setTextColor(Color.parseColor("#33CC33"));
            }else if("2".equals(bean.getJf_state())){
                tvType.setText("支出");
                tvTime.setText(bean.getJf_time());
                tvMoney.setText("-"+bean.getJf_jf());
                tvMoney.setTextColor(Color.parseColor("#363636"));
            }
        }
//        String[] split = bean.getZhi().split(",");
//        String type = split[2];
//        tvTime.setText(split[0]);
//        tvType.setText(type);
//        if("返还".equals(type)||"充值".equals(type)||"奖励".equals(type)){
//            tvMoney.setText("+"+split[1]);
//            tvMoney.setTextColor(Color.parseColor("#33CC33"));
//        }else if("提现".equals(type)||"转账".equals(type)||"转出".equals(type)){
//            tvMoney.setText("-"+split[1]);
//            tvMoney.setTextColor(Color.parseColor("#363636"));
//        }
    }
}
