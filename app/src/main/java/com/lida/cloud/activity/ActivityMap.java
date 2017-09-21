package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.lida.cloud.R;
import com.midian.base.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 百度地图
 * Created by Administrator on 2017/6/3.
 */

public class ActivityMap extends BaseActivity {
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.btn_location)
    ImageView btnLocation;

    private LocationClient mLocationClient = null;
    private BaiduMap mBaiduMap = null;
    private BDLocationListener myListener = new MyLocationListener();
    private boolean isFristLocation = true;
    private LatLng myLatLng;

    private String endLat;
    private String endLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        endLat = mBundle.getString("lat");
        endLon = mBundle.getString("lon");
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(15).build()));
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span = 5000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @OnClick(R.id.btn_location)
    public void onViewClicked() {
        MapStatusUpdate u= MapStatusUpdateFactory.newLatLng(myLatLng);
        mBaiduMap.animateMapStatus(u);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mMapView == null) {
                return;
            }
            mBaiduMap.setMyLocationEnabled(true);
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locData);
            BitmapDescriptor mMyPosition = BitmapDescriptorFactory.fromResource(R.drawable.icon_myadd);
            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mMyPosition);
            mBaiduMap.setMyLocationConfigeration(configuration);
            myLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(myLatLng);
            Marker marker = (Marker) mBaiduMap.addOverlay(markerOptions);
            LogUtils.e(bdLocation.getLocationDescribe());
            ac.setProperty("position", bdLocation.getLocationDescribe());
            if (isFristLocation) {
                isFristLocation = false;
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(myLatLng);
                mBaiduMap.animateMapStatus(u);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
