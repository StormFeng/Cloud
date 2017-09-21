package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.RewardMarkBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 奖励的积分
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRewardMarkTpl extends BaseTpl<RewardMarkBean.DataBean> {

    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public ActivityRewardMarkTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRewardMarkTpl(Context context) {
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
    public void setBean(RewardMarkBean.DataBean bean, int position) {
        if(bean!=null){
            tvName.setText(bean.getMem_hname());
            tvCount.setText(bean.getCre_credit());
            tvTime.setText(bean.getCre_time());
            ac.setImage(ivHead, Constant.IMGBASEURLPB+bean.getMem_tx());
        }
    }
}
