package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.CommentBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家评论
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityCommentTpl extends BaseTpl<CommentBean.DataBean> {


    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvComment)
    TextView tvComment;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public ActivityCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitycomment;
    }

    @Override
    public void setBean(CommentBean.DataBean bean, int position) {
        if (bean != null) {
            ac.setImage(ivHead,Constant.IMGBASEURLPB+bean.getMem_tx());
            tvComment.setText(bean.getCom_con());
            tvName.setText(bean.getMem_name());
            tvTime.setText(bean.getCom_time());
        }
    }
}
