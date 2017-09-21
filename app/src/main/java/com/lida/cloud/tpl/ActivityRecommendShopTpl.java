package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.RecommendBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐的商户
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRecommendShopTpl extends BaseTpl<RecommendBean.DataBean.ShopliBean> {

    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;

    public ActivityRecommendShopTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRecommendShopTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityrecommend;
    }

    @Override
    public void setBean(RecommendBean.DataBean.ShopliBean bean, int position) {
        if(bean!=null){
            tvName.setText(bean.getMem_hname());
            ac.setImage(ivHead, Constant.IMGBASEURLPB+bean.getMem_tx());
        }
    }
}
