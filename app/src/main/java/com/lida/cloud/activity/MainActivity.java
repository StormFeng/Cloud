package com.lida.cloud.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.ShareUrlBean;
import com.lida.cloud.fragment.FragmentHome;
import com.lida.cloud.fragment.FragmentPersonal;
import com.lida.cloud.fragment.FragmentShop;
import com.lida.cloud.widght.DialogGoSetting;
import com.lida.cloud.widght.grandienttab.GradientTabStrip;
import com.lida.cloud.widght.grandienttab.GradientTabStripAdapter;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity implements DialogGoSetting.onButtonClickListener {

    @BindView(R.id.gts_vp_fragments)
    ViewPager vpFragments;
    @BindView(R.id.gradientTabStrip)
    GradientTabStrip tabStrip;

    private GradientTabStripAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    private long waitTime = 2000;
    private long touchTime = 0;

    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    public String province = "";//省
    public String city = "";//市
    public String currentArea = "";//区
    public String district = "";//区
    public String lon = "";//
    public String lat = "";//

    private DialogGoSetting dialogGoSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(_activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2001);
            }else{
                initView();
                initLocation();
            }
        }else{
            initView();
            initLocation();
        }
        dialogGoSetting = new DialogGoSetting(_activity);
        dialogGoSetting.setOnButtonClickListener(this);
        AppUtil.getCloudApiClient(ac).shareUrl(this);//分享链接
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            ShareUrlBean bean = (ShareUrlBean) res;
            ac.setProperty("shareUrl", "http://" + bean.getData().getUrl());
        }
    }

    private void initView() {
        vpFragments.setOffscreenPageLimit(3);
        fragments.add(new FragmentHome());
        fragments.add(new FragmentShop());
        fragments.add(new FragmentPersonal());
        adapter = new GradientTabStripAdapter(getSupportFragmentManager(),fragments);
        vpFragments.setAdapter(adapter);
        tabStrip.setAdapter(adapter);
        tabStrip.bindViewPager(vpFragments);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span = 5000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedLocationDescribe(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(_activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2001);
                }else{
                    initView();
                    initLocation();
                }
            }
        }
    }

    /**
     * 设置权限
     */
    @Override
    public void onButtonClicked() {
        dialogGoSetting.dismiss();
        Uri packageURI = Uri.parse("package:" + _activity.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        startActivityForResult(intent,1001);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            int locType = location.getLocType();
            LogUtils.e(location.getLocationDescribe());
            ac.setProperty("position", location.getLocationDescribe());
            if (locType == BDLocation.TypeNetWorkLocation) {
                String addrStr = location.getAddrStr();// 获取反地理编码(文字描述的地址)
                LogUtils.e("文字描述的地址:" + addrStr);
            }
            province = location.getProvince();
            city = location.getCity();
            district = location.getDistrict();
            currentArea = district;
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            ac.setProperty("province",province);
            ac.setProperty("city",city);
            ac.setProperty("currentArea",currentArea);
            ac.setProperty("district",district);
            ac.setProperty("lon",lon);
            ac.setProperty("lat",lat);
            LogUtils.e("省:" + province + ", 市:" + city + ", 区:" + district);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            dialogGoSetting.show();
        }else{
            initView();
            initLocation();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
                if(FragmentShop.popupWindowCopy!=null){
                    if(FragmentShop.popupWindowCopy.isShowing()){
                        FragmentShop.popupWindowCopy.dismiss();
                        return true;
                    }
                }
                if(FragmentShop.popupWindow!=null){
                    if(FragmentShop.popupWindow.isShowing()){
                        FragmentShop.popupWindow.dismiss();
                        return true;
                    }
                }
                long currentTime = System.currentTimeMillis();
                if ((currentTime - touchTime) >= waitTime) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    touchTime = currentTime;
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
