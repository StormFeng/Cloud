package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.KCJFBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentInIntegralTpl extends BaseTpl<KCJFBean.DataBean.LiBean> {

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvType)
    TextView tvType;

    public FragmentInIntegralTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentInIntegralTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_kcjf;
    }

    @Override
    public void setBean(KCJFBean.DataBean.LiBean bean, int position) {
        if (bean != null) {
            tvNumber.setText(bean.getJf_number());
            tvTime.setText(bean.getJf_time());
            tvType.setText("购买积分");
        }
    }
}
