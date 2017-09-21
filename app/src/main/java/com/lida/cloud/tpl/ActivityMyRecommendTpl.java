package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAddCard;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的推荐
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityMyRecommendTpl extends BaseTpl {

    public ActivityMyRecommendTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityMyRecommendTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myrecommend;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
