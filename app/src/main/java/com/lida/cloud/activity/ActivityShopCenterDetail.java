package com.lida.cloud.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterShopPic;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ShopDetailBean;
import com.lida.cloud.bean.TypeBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.DialogChooseType;
import com.lida.cloud.widght.InnerGridView;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商户基本资料
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityShopCenterDetail extends TakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.gvPic)
    InnerGridView gvPic;
    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etMail)
    EditText etMail;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnSave)
    Button btnSave;

    private ShopDetailBean bean;
    private String selid="";
    private List<String> pics = new ArrayList<>();
    private AdapterShopPic adapter;

    private AppContext ac;
    private Activity _activity;
    private TypeBean beanType;

    private String lat = "";
    private String lon = "";
    private String province = "";
    private String city = "";
    private String area = "";
    private String selimage = "";

    private LoadingDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        }
        setContentView(R.layout.activity_shopcenterdetail);
        ButterKnife.bind(this);
        ac = (AppContext) getApplication();
        _activity = this;
        topbar.setTitle("商户基本资料");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        pics.add("");
        adapter = new AdapterShopPic(this, pics);
        gvPic.setAdapter(adapter);
        AppUtil.getCloudApiClient(ac).clicksel(apiCallback);
    }

    @OnClick({R.id.btnSave, R.id.tvType, R.id.tvPosition})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                String selshopname = etShopName.getText().toString();
                String selshoptype = tvType.getText().toString();
                String selshopadd = tvPosition.getText().toString();
                String seltel = etPhone.getText().toString();
                String selemail = etMail.getText().toString();
                String seldetail = etDes.getText().toString();
                if(pics.size()>1){
                    selimage = pics.get(0);
                }
                LogUtils.e(selimage);
                AppUtil.getCloudApiClient(ac).hold(selid,selshopname,selimage,selshoptype,selshopadd,seltel,
                        selemail,seldetail,province,city,area,lon,lat,apiCallback);
                break;
            case R.id.tvType:
                if(beanType==null){
                    AppUtil.getCloudApiClient(ac).type(apiCallback);
                }else {
                    new DialogChooseType(_activity,beanType,tvType).show();
                }
                break;
            case R.id.tvPosition:
                UIHelper.jumpForResult(_activity, ActivityChooseArea.class, 2001);
                break;
        }
    }

    BaseApiCallback apiCallback = new BaseApiCallback(){

        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            tvType.setClickable(false);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            tvType.setClickable(true);
            if(res.isOK()){
                if("hold".equals(tag)){
                    UIHelper.t(_activity,"保存成功！");
                    Intent intent = new Intent();
                    intent.putExtra("head",selimage);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                if("type".equals(tag)){
                    beanType = (TypeBean) res;
                    new DialogChooseType(_activity,beanType,tvType).show();
                }
                if("clicksel".equals(tag)){
                    ShopDetailBean bean = (ShopDetailBean) res;
                    etShopName.setText(bean.getData().getSelshopname());
                    tvType.setText(bean.getData().getSelshoptype());
                    tvPosition.setText(bean.getData().getSelshopadd());
                    etPhone.setText(bean.getData().getSeltel());
                    etMail.setText(bean.getData().getSelemail());
                    etDes.setText(bean.getData().getSeldetail());
                    selid = bean.getData().getSelid();
                    if(bean.getData().getD1_name()!=null){
                        province = bean.getData().getD1_name();
                    }
                    if(bean.getData().getD1_name()!=null){
                        city = bean.getData().getD2_name();
                    }
                    if(bean.getData().getD1_name()!=null) {
                        area = bean.getData().getD3_name();
                    }
                    if(bean.getData().getLon()!=null) {
                        lon = bean.getData().getLon();
                    }
                    if(bean.getData().getDime()!=null) {
                        lat = bean.getData().getDime();
                    }
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            tvType.setClickable(true);
            hideLoadingDlg();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if(RESULT_OK==resultCode){
            if(requestCode == 2001){
                LogUtils.e(data.getParcelableExtra("location"));
                PoiInfo poiInfo = data.getParcelableExtra("location");
                tvPosition.setText(poiInfo.address);
                LatLng location = poiInfo.location;
                lat = String.valueOf(location.latitude);
                lon = String.valueOf(location.longitude);
                province = data.getStringExtra("province");
                city = data.getStringExtra("city");
                area = data.getStringExtra("area");
            }
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            selimage = result.getImage().getCompressPath();
            pics.add(pics.size() - 1, result.getImage().getCompressPath());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void showLoadingDlg() {
        if (dlg != null && dlg.isShowing()) {
            return;
        }
        if (dlg == null) {
            dlg = new LoadingDialog(this, com.bishilai.thirdpackage.R.layout.dialog_msg,
                    com.bishilai.thirdpackage.R.style.dialog_msg);
        }
        dlg.setCanceledOnTouchOutside(true);
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (!true) {
                    finish();
                }
            }
        });
        dlg.showMessage("");
    }
    public void hideLoadingDlg() {
        if (dlg != null) {
            dlg.dismiss();
        }
    }
}
