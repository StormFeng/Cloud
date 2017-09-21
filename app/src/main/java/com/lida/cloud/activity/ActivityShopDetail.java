package com.lida.cloud.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterFragGoods;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.DetailBean;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.util.MapUtil;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.DialogShare;
import com.lida.cloud.widght.InnerGridView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.umeng.socialize.UMShareAPI;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商家详情
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityShopDetail extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.tvFanLi)
    TextView tvFanLi;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.bg1)
    View bg1;
    @BindView(R.id.bg2)
    View bg2;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvCommend)
    TextView tvCommend;
    @BindView(R.id.tvReport)
    TextView tvReport;
    @BindView(R.id.llF1)
    LinearLayout llF1;
    @BindView(R.id.gridView)
    InnerGridView gridView;
    @BindView(R.id.tvCommendCount)
    TextView tvCommendCount;
    private boolean isLike;

    public static String des = "";
    public static String position = "";
    public static String tel = "";
    public static String time = "";
    public static String selid;
    private String commentCount;
    private AdapterFragGoods adapter;
    private List<GoodBean.DataBean> data = new ArrayList<>();

    private double lon;
    private double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(_activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2001);
            }
        }
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        initView();
        selid = mBundle.getString("id");
        AppUtil.getCloudApiClient(ac).detail(selid, callback);
        AppUtil.getCloudApiClient(ac).shopli(ActivityShopDetail.selid, callback);
    }

    private void initView() {
        topbar.setBackgroundColor(Color.TRANSPARENT);
        topbar.setTitle("");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_share, listener);
        topbar.setRight2ImageButton(R.drawable.icon_like, listener);
        setButton(0);
    }

    /**
     * 收藏、分享
     */
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_ib:
                    new DialogShare(_activity, "加入云众利，消费不再贵", "泉州云众利网络科技有限公司（以下简称：云众利）由福建本土民营企业家黄文汉先生投资创建，于2017年4月在泉州工商局注册成立（目前注册资金为800万元）", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495425393735&di=4f6f7ee60cb289f43298e649c2e01615&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0324%2F7b07c97b5e653c45c37499236848d519.jpg",
                            ac.getProperty("shareUrl")).show();
//                    UMImage imagelocal = new UMImage(_activity,R.drawable.share);
//                    new ShareAction(ActivityShopDetail.this).withMedia(imagelocal )
//                            .setPlatform(SHARE_MEDIA.QQ)
//                            .setCallback(shareListener).share();
                    break;
                case R.id.right2_ib:
                    if (isLike) {
                        AppUtil.getCloudApiClient(ac).coldel(selid, callback);//取消收藏
                    } else {
                        AppUtil.getCloudApiClient(ac).collection(selid, callback);//收藏
                    }
                    break;
            }
        }
    };

    BaseApiCallback callback = new BaseApiCallback() {

        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            if (res.isOK()) {
                if ("detail".equals(tag)) {
                    DetailBean detailBean = (DetailBean) res;
                    ac.setImage(iv, Constant.IMGSHOP + detailBean.getData().getRes().getSelimage());
                    tvName.setText(detailBean.getData().getRes().getSelshopname());
                    tvFanLi.setText(detailBean.getData().getRes().getSelback() + "%");
                    tvClass.setText(detailBean.getData().getRes().getSelshoptype());
                    tvDes.setText(detailBean.getData().getRes().getSeldetail());
                    tvPosition.setText(detailBean.getData().getRes().getSelshopadd());
                    tvTel.setText(detailBean.getData().getRes().getSeltel());
                    tvTime.setText(detailBean.getData().getRes().getSelshoptime());
                    tvCommendCount.setText(detailBean.getData().getSl());
                    try {
                        if(!"".equals(detailBean.getData().getRes().getDime()) && detailBean.getData().getRes().getDime()!=null){
                            lat = Double.valueOf(detailBean.getData().getRes().getDime());
                        }
                        if(!"".equals(detailBean.getData().getRes().getDime()) && detailBean.getData().getRes().getLon()!=null){
                            lon = Double.valueOf(detailBean.getData().getRes().getLon());
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if ("0".equals(detailBean.getData().getIsc())) {
                        isLike = false;
                        topbar.setRight2ImageButton(R.drawable.icon_like, listener);
                    } else {
                        isLike = true;
                        topbar.setRight2ImageButton(R.drawable.icon_like_y, listener);
                    }
                }
                if ("shopli".equals(tag)) {
                    GoodBean bean = (GoodBean) res;
                    if (bean.getData() != null) {
                        data.addAll(bean.getData());
//                        adapter.notifyDataSetChanged();
                        adapter = new AdapterFragGoods(_activity, data);
                        gridView.setAdapter(adapter);
                    }
                }
                if ("collection".equals(tag)) {
                    isLike = true;
                    topbar.setRight2ImageButton(R.drawable.icon_like_y, listener);
                    UIHelper.t(_activity, "收藏成功！");
                }
                if ("coldel".equals(tag)) {
                    isLike = false;
                    topbar.setRight2ImageButton(R.drawable.icon_like, listener);
                    UIHelper.t(_activity, "取消收藏！");
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.tvReport, R.id.tvPosition, R.id.tvCommend})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("selid", ActivityShopDetail.selid);
        switch (view.getId()) {
            case R.id.btn1:
                setButton(0);
                break;
            case R.id.btn2:
                setButton(1);
                break;
            case R.id.tvReport:
                UIHelper.jump(_activity, ActivityReport.class, bundle);
                break;
            case R.id.tvPosition:
                if(lat==0.0||lon==0.0){
                    UIHelper.t(_activity,"位置不明确，无法导航！");
                    return;
                }
                LatLng target = new LatLng(lat,lon);
                LatLng self = new LatLng(Double.valueOf(ac.getProperty("lat")),Double.valueOf(ac.getProperty("lon")));
                NaviParaOption para = new NaviParaOption()
                        .startPoint(self).endPoint(target)
                        .startName("我的位置").endName("目的地");
                BaiduMapNavigation.openBaiduMapNavi(para, this);
//                Intent intent = new Intent();
//                intent.setData(Uri.parse("baidumap://map/marker?location="+lat+","+lon+"&title="+tvName.getText().toString()+"&content=makeamarker&traffic=on"));
//                startActivity(intent);
//                LogUtils.e("tvPosition_2");
                break;
            case R.id.tvCommend:
                UIHelper.jump(_activity, ActivityCommentList.class, bundle);
                break;
        }
    }

    private void setButton(int i) {
        if (i == 0) {
            btn1.setTextColor(Color.parseColor("#FA2220"));
            btn2.setTextColor(Color.parseColor("#363636"));
            bg1.setBackgroundColor(Color.parseColor("#FA2220"));
            bg2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            llF1.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }
        if (i == 1) {
            btn2.setTextColor(Color.parseColor("#FA2220"));
            btn1.setTextColor(Color.parseColor("#363636"));
            bg2.setBackgroundColor(Color.parseColor("#FA2220"));
            bg1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            llF1.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }
    }
}
