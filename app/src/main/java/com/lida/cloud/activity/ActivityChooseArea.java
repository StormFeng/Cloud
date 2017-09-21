package com.lida.cloud.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterPoi;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 百度地图
 * Created by Administrator on 2017/6/3.
 */

public class ActivityChooseArea extends BaseActivity implements OnGetPoiSearchResultListener {
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    ListView listView;

    private LocationClient mLocationClient = null;
    private BaiduMap mBaiduMap = null;
    private BDLocationListener myListener = new MyLocationListener();

    private boolean isFristLocation = true;
    private LatLng myLatLng;

    private PoiSearch mPoiSearch;
    private double longitude;
    private double latitude;

    public String province = "";//省
    public String city = "";//市
    public String district = "";//区

    private List<PoiInfo> dataList = new ArrayList<>();
    private AdapterPoi adapter;

    private Overlay target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseposition);
        ButterKnife.bind(this);
        topbar.setTitle("选择地址");
        topbar.setLeftImageButton(R.drawable.icon_back,UIHelper.finish(_activity));
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(15).build()));
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        adapter = new AdapterPoi(this,dataList);
        listView.setAdapter(adapter);
        initLocation();
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(target!=null){
                    target.remove();
                }
                OverlayOptions options;
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_myadd);
                options = new MarkerOptions()
                        .position(latLng)//设置位置
                        .icon(bitmap)//设置图标样式
                        .zIndex(9) // 设置marker所在层级
                        .draggable(true); // 设置手势拖拽;
                target = mBaiduMap.addOverlay(options);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(u);
                searchNeayBy(latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
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


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                return;
            }
            mBaiduMap.setMyLocationEnabled(true);
            int locType = location.getLocType();
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);


            LogUtils.e(location.getLocationDescribe());
            ac.setProperty("position", location.getLocationDescribe());
            myLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (isFristLocation) {
                isFristLocation = false;
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(myLatLng);
                mBaiduMap.animateMapStatus(u);
                LogUtils.e("animateMapStatus");
            }

            if (locType == BDLocation.TypeNetWorkLocation) {
                String addrStr = location.getAddrStr();// 获取反地理编码(文字描述的地址)
                LogUtils.e("文字描述的地址:" + addrStr);
            }
            province = location.getProvince();
            city = location.getCity();
            district = location.getDistrict();
            LogUtils.e("省:" + province + ", 市:" + city + ", 区:" + district);

            //添加当前位置到poi
//            PoiInfo info = new PoiInfo();
//            info.address = location.getAddrStr();
//            info.city = location.getCity();
//            info.location = myLatLng;
//            info.name = location.getAddrStr();
//            dataList.add(info);

            CoordinateConverter converter = new CoordinateConverter();
            converter.coord(myLatLng);
            converter.from(CoordinateConverter.CoordType.COMMON);
            OverlayOptions ooA = new MarkerOptions().position(myLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_myadd));
            Marker mCurrentMarker = (Marker) mBaiduMap.addOverlay(ooA);
            mMapView.showZoomControls(false);
            searchNeayBy(myLatLng);
        }
    }

    private void searchNeayBy(LatLng latLng) {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();
        poiNearbySearchOption.keyword("公司");
        poiNearbySearchOption.location(latLng);
        poiNearbySearchOption.radius(100);  // 检索半径，单位是米
        poiNearbySearchOption.pageCapacity(20);  // 默认每页10条
        mPoiSearch.searchNearby(poiNearbySearchOption);  // 发起附近检索请求
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {// 没有找到检索结果
            UIHelper.t(_activity, "未搜索到结果！");
            return;
        }

        if (result.error == SearchResult.ERRORNO.NO_ERROR) {// 检索结果正常返回
            dataList.clear();
            if (result != null) {
                if (result.getAllPoi() != null && result.getAllPoi().size() > 0) {
                    LogUtils.e(result.getAllPoi());
                    dataList.addAll(result.getAllPoi());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

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
