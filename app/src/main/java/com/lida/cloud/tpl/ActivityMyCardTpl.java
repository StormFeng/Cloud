package com.lida.cloud.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAddCard;
import com.lida.cloud.bean.BankCardBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的银行卡
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityMyCardTpl extends BaseTpl<BankCardBean.DataBean> {

    @BindView(R.id.rlCard)
    RelativeLayout rlCard;
    @BindView(R.id.rlAddCard)
    RelativeLayout rlAddCard;
    @BindView(R.id.tvCardIcon)
    RoundedImageView tvCardIcon;
    @BindView(R.id.tvBankName)
    TextView tvBankName;
    @BindView(R.id.tvCardType)
    TextView tvCardType;
    @BindView(R.id.tvCardNumber)
    TextView tvCardNumber;

    public ActivityMyCardTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityMyCardTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitymycard;
    }

    @Override
    public void setBean(BankCardBean.DataBean bean, int position) {
        if(bean!=null){
            if(bean.isLast()){
                rlAddCard.setVisibility(VISIBLE);
                rlCard.setVisibility(GONE);
                rlAddCard.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.jumpForResult(_activity, ActivityAddCard.class,1002);
                    }
                });
            }else{
                rlAddCard.setVisibility(GONE);
                rlCard.setVisibility(VISIBLE);
                tvCardNumber.setText(Func.bankCardFormat(bean.getB_number()));
                tvBankName.setText(bean.getB_name());
            }
        }
    }
}
