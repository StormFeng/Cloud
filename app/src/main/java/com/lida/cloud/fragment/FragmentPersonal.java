package com.lida.cloud.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityApplyToShop;
import com.lida.cloud.activity.ActivityBalance;
import com.lida.cloud.activity.ActivityCollection;
import com.lida.cloud.activity.ActivityLoan;
import com.lida.cloud.activity.ActivityMarkList;
import com.lida.cloud.activity.ActivityMemberUpdate;
import com.lida.cloud.activity.ActivityMyBooks;
import com.lida.cloud.activity.ActivityMyCard;
import com.lida.cloud.activity.ActivityMyRecommend;
import com.lida.cloud.activity.ActivityPersonalData;
import com.lida.cloud.activity.ActivitySetting;
import com.lida.cloud.activity.ActivityShopAuth;
import com.lida.cloud.activity.ActivityShopCenter;
import com.lida.cloud.activity.ActivityWeShopAuth;
import com.lida.cloud.activity.ActivityYestodaySettlement;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.MemDetailBean;
import com.lida.cloud.bean.ShopDetailBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentPersonal extends BaseFragment implements ApiCallback {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    @BindView(R.id.rlHead)
    RelativeLayout rlHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvBook)
    TextView tvBook;
    @BindView(R.id.tvCollection)
    TextView tvCollection;
    @BindView(R.id.tvSettlement)
    TextView tvSettlement;
    @BindView(R.id.tvBankCard)
    TextView tvBankCard;
    @BindView(R.id.tvMemberUpdate)
    TextView tvMemberUpdate;
    @BindView(R.id.tvShopCenter)
    TextView tvShopCenter;
    @BindView(R.id.tvMyRecomment)
    TextView tvMyRecomment;
    @BindView(R.id.tvFansSpirited)
    TextView tvFansSpirited;
    @BindView(R.id.tvShop)
    TextView tvShop;
    Unbinder unbinder;
    @BindView(R.id.tvYuE)
    TextView tvYuE;
    @BindView(R.id.tvJiFen)
    TextView tvJiFen;
    @BindView(R.id.ivType)
    ImageView ivType;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvProportion)
    TextView tvProportion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, null);
        unbinder = ButterKnife.bind(this, v);
        topbar.setTitle("个人中心");
        topbar.setRightImageButton(R.drawable.icon_setting, listener);
        topbar.setBackgroundColor(Color.TRANSPARENT);
        if (ac.head_img != null) {
            ac.setImage(ivHead, Constant.IMGBASEURLPB + ac.head_img);
        }
        if(ac.mem_nc==null||"".equals(ac.mem_nc)){
            tvName.setText("未设置昵称");
        }else{
            tvName.setText(ac.mem_nc);
        }
        AppUtil.getCloudApiClient(ac).getPersonalDataInfo(ac.id,this);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity, ActivitySetting.class);
        }
    };

    @OnClick({R.id.ivEdit, R.id.tvBook, R.id.tvCollection, R.id.tvSettlement, R.id.tvBankCard,
            R.id.tvMemberUpdate, R.id.tvShopCenter, R.id.tvMyRecomment, R.id.tvFansSpirited,
            R.id.tvShop, R.id.llBalance, R.id.llMark, R.id.tvLoan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivEdit://个人资料
                this.startActivityForResult(new Intent(_activity, ActivityPersonalData.class), 1001);
                break;
            case R.id.tvBook://我的订单
                UIHelper.jump(_activity, ActivityMyBooks.class);
                break;
            case R.id.tvCollection://收藏的商户
                UIHelper.jump(_activity, ActivityCollection.class);
                break;
            case R.id.tvSettlement://昨日结算
                UIHelper.jump(_activity, ActivityYestodaySettlement.class);
                break;
            case R.id.tvBankCard://我的银行卡
                UIHelper.jump(_activity, ActivityMyCard.class);
                break;
            case R.id.tvMemberUpdate://会员升级
                if ("3".equals(ac.getProperty("mem_staus"))){
                    UIHelper.t(_activity, "您已经是实体商户！");
                    return;
                }
                if("4".equals(ac.getProperty("mem_staus"))){
                    UIHelper.t(_activity, "您已经是微商商户！");
                    return;
                }
                UIHelper.jump(_activity, ActivityMemberUpdate.class);
                break;
            case R.id.tvShopCenter://商户中心
                if("3".equals(ac.mem_staus)||"4".equals(ac.mem_staus)){
                    AppUtil.getCloudApiClient(ac).clicksel(this);
                }else {
                    UIHelper.t(_activity,"请先升级成为商户！");
                }
                break;
            case R.id.tvMyRecomment://我的推荐
                UIHelper.jump(_activity, ActivityMyRecommend.class);
                break;
            case R.id.tvFansSpirited://粉丝公益
                UIHelper.t(_activity, "粉丝公益正在建设中，敬请期待！");
                break;
            case R.id.tvShop:
                UIHelper.t(_activity, "商城正在建设中，敬请期待！");
                break;
            case R.id.tvLoan://信用积分贷款
                UIHelper.jump(_activity, ActivityLoan.class);
//                UIHelper.t(_activity, "信用积分贷款正在建设中，敬请期待！");
                break;
            case R.id.llBalance:
                UIHelper.jump(_activity, ActivityBalance.class);
                break;
            case R.id.llMark://信用积分
                UIHelper.jump(_activity, ActivityMarkList.class);
                break;
        }
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            if("getPersonalDataInfo".equals(tag)){
                MemDetailBean bean = (MemDetailBean) res;
                if(bean.getData().size()>0){
                    try {
                        tvYuE.setText(bean.getData().get(0).getMem_yue());
                        tvJiFen.setText(bean.getData().get(0).getMem_jifen());
                        ac.setProperty("mem_staus",bean.getData().get(0).getMem_state());
                        ac.setProperty("yue",bean.getData().get(0).getMem_yue());
                        initInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if("clicksel".equals(tag)){
                ShopDetailBean bean = (ShopDetailBean) res;
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", bean);
                if("3".equals(ac.getProperty("mem_staus"))||"4".equals(ac.getProperty("mem_staus"))){
                    if (res.noData()) {
                        if("3".equals(ac.getProperty("mem_staus"))){
                            UIHelper.jump(_activity, ActivityShopAuth.class);
                        }else if("4".equals(ac.getProperty("mem_staus"))){
                            UIHelper.jump(_activity, ActivityWeShopAuth.class);
                        }
                    } else {
                        UIHelper.jump(_activity, ActivityShopCenter.class,bundle);
                    }
                }else{
                    if (res.noData()) {
                        UIHelper.jump(_activity, ActivityApplyToShop.class);
                    } else {
                        UIHelper.jump(_activity, ActivityShopCenter.class, bundle);
                    }
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppUtil.getCloudApiClient(ac).getPersonalDataInfo(ac.id,this);
        ac.setImage(ivHead, Constant.IMGBASEURLPB + ac.getProperty("head_img"));
        tvName.setText(ac.getProperty("mem_nc"));
        initInfo();
    }

    private void initInfo() {
        if("0".equals(ac.getProperty("mem_staus"))){
            tvType.setText("普通会员");
            ivType.setImageResource(R.drawable.icon_low);
            tvProportion.setText("返还比例：万分之三");
        }else if("1".equals(ac.getProperty("mem_staus"))){
            tvType.setText("银钻会员");
            ivType.setImageResource(R.drawable.icon_silver);
            tvProportion.setText("返还比例：万分之五");
        }else if("2".equals(ac.getProperty("mem_staus"))){
            tvType.setText("金钻会员");
            ivType.setImageResource(R.drawable.icon_gold);
            tvProportion.setText("返还比例：万分之七");
        }else if("3".equals(ac.getProperty("mem_staus"))){
            tvType.setText("实体商户");
            ivType.setImageResource(R.drawable.icon_shop);
            tvProportion.setText("返还比例：万分之七");
        }else if("4".equals(ac.getProperty("mem_staus"))){
            tvType.setText("微商商户");
            ivType.setImageResource(R.drawable.icon_shop);
            tvProportion.setText("返还比例：万分之七");
        }
    }

    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {

    }
}
