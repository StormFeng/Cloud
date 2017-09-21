package com.lida.cloud.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.ShopDetailBean;
import com.lida.cloud.widght.DialogShare;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商户中心
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityShopCenter extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvShopName)
    TextView tvShopName;

    private ShopDetailBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcenter);
        ButterKnife.bind(this);
        topbar.setTitle("商户中心");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        bean = (ShopDetailBean) mBundle.getSerializable("data");
        ac.setImage(ivHead, Constant.IMGSHOP+bean.getData().getSelimage());
        tvShopName.setText(bean.getData().getSelshopname());
    }

    @OnClick({R.id.tvShopDetail, R.id.tvGoodsManage, R.id.tvShareShop, R.id.tvKcjf, R.id.tvZcjf})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tvShopDetail://商户详情
                bundle.putSerializable("data",bean);
                UIHelper.jumpForResult(_activity, ActivityShopCenterDetail.class, bundle,1001);
                break;
            case R.id.tvGoodsManage:
                bundle.putString("selid",bean.getData().getSelid());
                UIHelper.jump(_activity, ActivityGoodManage.class,bundle);
                break;
            case R.id.tvShareShop:
                new DialogShare(_activity,"加入云众利，消费不再贵","泉州云众利网络科技有限公司（以下简称：云众利）由福建本土民营企业家黄文汉先生投资创建，于2017年4月在泉州工商局注册成立（目前注册资金为800万元）","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495425393735&di=4f6f7ee60cb289f43298e649c2e01615&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0324%2F7b07c97b5e653c45c37499236848d519.jpg",
                        ac.getProperty("shareUrl")).show();
                break;
            case R.id.tvKcjf://库存积分
                LogUtils.e("selid:"+bean.getData().getSelid());
                bundle.putString("selid",bean.getData().getSelid());
                UIHelper.jump(_activity, ActivityKCJF.class, bundle);
                break;
            case R.id.tvZcjf:
                bundle.putString("selid",bean.getData().getSelid());
                bundle.putString("integral",bean.getData().getSeljifen());
                UIHelper.jump(_activity, ActivityTurnOut.class,bundle);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            String head = data.getStringExtra("head");
            if(!"".equals(head)){
                ivHead.setImageBitmap(BitmapFactory.decodeFile(head));
            }
        }
    }
}
