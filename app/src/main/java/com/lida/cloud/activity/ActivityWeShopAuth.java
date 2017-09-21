package com.lida.cloud.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.TypeBean;
import com.lida.cloud.widght.DialogChoosePicType;
import com.lida.cloud.widght.DialogChooseType;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.LoadingDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 微商商家认证
 * Created by WeiQingFeng on 2017/5/20.
 */

public class ActivityWeShopAuth extends TakePhotoActivity implements ApiCallback {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    //    @BindView(R.id.gvPic)
//    InnerGridView gvPic;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.ivCardA)
    RoundedImageView ivCardA;
    @BindView(R.id.ivCardB)
    RoundedImageView ivCardB;
    @BindView(R.id.ivCardC)
    RoundedImageView ivCardC;

    private List<String> pics = new ArrayList<>();
    private String name;//负责人电话
    private String tel;//手机
    private String number;//营业执照注册号
    private String shopName;//营业执照名称
    private String province;//省
    private String city;//市
    private String area;//区
    private String address;//公司地址
    private String lat;//经度
    private String lon;//纬度
    private String type;//公司类型
    private TypeBean beanType;//公司类型

    private Activity _activity;
    private AppContext ac;
    private LoadingDialog dlg;
    private int flag = 0;//0 正面  1 反面  2 营业执照
    private DialogChoosePicType dialog;

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
        setContentView(R.layout.activity_weshopauth);
        ButterKnife.bind(this);
        _activity = this;
        ac = (AppContext) getApplication();
        topbar.setTitle("微商认证");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
    }

    @OnClick({R.id.btnCommit, R.id.tvType, R.id.tvAddress, R.id.btnA, R.id.btnB, R.id.btnC})
    public void onViewClicked(View view) {
        dialog = new DialogChoosePicType(_activity);
        dialog.setTypeSelectedListener(listener);
        switch (view.getId()) {
            case R.id.btnCommit:
                name = etName.getText().toString();
                tel = etPhone.getText().toString();
                number = etNumber.getText().toString();
                shopName = etShopName.getText().toString();
                type = tvType.getText().toString();
                address = tvAddress.getText().toString();
                if ("".equals(name) || "".equals(tel) || "".equals(number) || "".equals(shopName)
                        || "".equals(type) || "".equals(address)) {
                    UIHelper.t(this, "资料填写不完整！");
                    AnimatorUtils.onVibrationView(btnCommit);
                    return;
                }
                if (!Func.isMobileNO(tel)) {
                    UIHelper.t(this, "电话号码格式不正确！");
                    AnimatorUtils.onVibrationView(btnCommit);
                    return;
                }
               if (pics.size() < 3) {
                   UIHelper.t(this, "请上传照片！");
                   return;
               }
                try {
                    AppUtil.getCloudApiClient(ac).seladd(name, tel, number, type, address, lon, lat, province,
                            city, area, shopName, pics, this);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvType:
                if (beanType == null) {
                    AppUtil.getCloudApiClient(ac).type(this);
                } else {
                    new DialogChooseType(_activity, beanType, tvType).show();
                }
                break;
            case R.id.tvAddress:
                UIHelper.jumpForResult(_activity, ActivityChooseArea.class, 2001);
                break;
            case R.id.btnA:
                flag = 0;
                dialog.show();
                break;
            case R.id.btnB:
                flag = 1;
                dialog.show();
                break;
            case R.id.btnC:
                flag = 2;
                dialog.show();
                break;
        }
    }

    DialogChoosePicType.onTypeSelectedListener listener = new DialogChoosePicType.onTypeSelectedListener() {
        @Override
        public void onOpenCamera() {
            TakePhoto takePhoto = getTakePhoto();
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            CompressConfig config;
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config = CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config, false);
            takePhoto.onPickFromCapture(imageUri);
        }

        @Override
        public void onOpenPic() {
            TakePhoto takePhoto = getTakePhoto();
            CompressConfig config;
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config = CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config, false);
            takePhoto.onPickFromGallery();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (RESULT_OK == resultCode) {
            if (requestCode == 2001) {
                LogUtils.e(data.getParcelableExtra("location"));
                PoiInfo poiInfo = data.getParcelableExtra("location");
                tvAddress.setText(poiInfo.address);
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
        String path = result.getImage().getCompressPath();
        if (!"".equals(path)) {
            if (flag == 0) {
                if (pics.size() > 0) {
                    pics.set(0, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardA.setImageBitmap(BitmapFactory.decodeFile(pics.get(0)));
                    }
                });
            }
            if (flag == 1) {
                if (pics.size() > 1) {
                    pics.set(1, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardB.setImageBitmap(BitmapFactory.decodeFile(pics.get(1)));
                    }
                });
            }
            if (flag == 2) {
                if (pics.size() > 2) {
                    pics.set(2, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardC.setImageBitmap(BitmapFactory.decodeFile(pics.get(2)));
                    }
                });
            }
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        LogUtils.e(result);
        hideLoadingDlg();
    }

    @Override
    public void onApiStart(String tag) {
        tvType.setClickable(false);
        showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {
        tvType.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        hideLoadingDlg();
        tvType.setClickable(true);
        if (res.isOK()) {
            if ("seladd".equals(tag)) {
                UIHelper.t(ActivityWeShopAuth.this, "提交成功！");
                setResult(RESULT_OK);
                finish();
            }
            if ("type".equals(tag)) {
                beanType = (TypeBean) res;
                new DialogChooseType(_activity, beanType, tvType).show();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        tvType.setClickable(true);
        hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {
        hideLoadingDlg();
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
