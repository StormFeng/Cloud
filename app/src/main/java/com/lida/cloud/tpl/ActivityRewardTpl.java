package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.RewardBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 奖励的金额
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRewardTpl extends BaseTpl<RewardBean.DataBean> {

    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public ActivityRewardTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRewardTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityreward;
    }

    @Override
    public void setBean(RewardBean.DataBean bean, int position) {
        if(bean!=null){
            tvCount.setText(bean.getRec_price());
            tvName.setText(bean.getMem_hname());
            tvTime.setText(bean.getRec_time());
            ac.setImage(ivHead, Constant.IMGBASEURLPB+bean.getMem_tx());
        }
    }
}
