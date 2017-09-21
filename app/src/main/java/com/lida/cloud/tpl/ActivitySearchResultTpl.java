package com.lida.cloud.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.ActivitySearchReslutBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家搜索结果页
 * Created by Administrator on 2017/6/7.
 */

public class ActivitySearchResultTpl extends BaseTpl<ActivitySearchReslutBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFanLi)
    TextView tvFanLi;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvArea)
    TextView tvArea;

    public ActivitySearchResultTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivitySearchResultTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentshop;
    }

    @Override
    public void setBean(final ActivitySearchReslutBean.DataBean bean, int position) {
        if (bean != null) {
            ac.setImage(iv, Constant.IMGSHOP + bean.getSelimage());
            tvName.setText(bean.getSelshopname());
            tvArea.setText(bean.getD3_name());
            tvType.setText(bean.getSelshoptype());
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", bean.getSelid());
                    UIHelper.jump(_activity, ActivityShopDetail.class, bundle);
                }
            });
        }
    }
}
