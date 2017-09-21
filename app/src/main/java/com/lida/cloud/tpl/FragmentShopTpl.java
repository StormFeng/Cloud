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
import com.lida.cloud.bean.SellerBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentShopTpl extends BaseTpl<SellerBean.DataBean.CyBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFanLi)
    TextView tvFanLi;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvArea)
    TextView tvArea;

    public FragmentShopTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentShopTpl(Context context) {
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
    public void setBean(final SellerBean.DataBean.CyBean bean, final int position) {
        if(bean!=null){
            ac.setImage(iv, Constant.IMGSHOP + bean.getSelimage());
            tvName.setText(bean.getSelshopname());
            tvArea.setText(bean.getD3_name());
            tvType.setText(bean.getSelshoptype());
            if(bean.getShu()!=null){
                String meter = bean.getShu();
                DecimalFormat df = new DecimalFormat("#.#");
                double d = Double.valueOf(meter);
                if(d>1000){
                    d = d / 1000;
                    String format = df.format(d);
                    tvDistance.setText(format+"Km");
                }else{
                    String format = df.format(d);
                    tvDistance.setText(format+"m");
                }
            }
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
