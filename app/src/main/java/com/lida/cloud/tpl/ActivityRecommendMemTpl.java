package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.RecommendBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐的会员
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityRecommendMemTpl extends BaseTpl<RecommendBean.DataBean.MemliBean> {

    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvAccount)
    TextView tvAccount;

    public ActivityRecommendMemTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRecommendMemTpl(Context context) {
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
    public void setBean(RecommendBean.DataBean.MemliBean bean, int position) {
        if (bean != null) {
            String mem_hname = bean.getMem_hname();
            if("".equals(mem_hname)||mem_hname==null){
                tvName.setText("真实姓名：(未填写)");
            }else{
                tvName.setText("真实姓名："+mem_hname);
            }
            tvAccount.setVisibility(VISIBLE);
            tvAccount.setText("会员账号："+bean.getMem_name());
            ac.setImage(ivHead, Constant.IMGBASEURLPB + bean.getMem_tx());
            if ("0".equals(bean.getMem_state())) {
                tvType.setText("普通会员");
            } else if ("1".equals(bean.getMem_state())) {
                tvType.setText("银钻会员");
            } else if ("2".equals(bean.getMem_state())) {
                tvType.setText("金钻会员");
            } else if ("3".equals(bean.getMem_state())) {
                tvType.setText("实体商户");
            }
        }
    }
}
