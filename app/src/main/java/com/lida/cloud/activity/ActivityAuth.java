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
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.lida.cloud.widght.DialogAuthComplete;
import com.lida.cloud.widght.DialogChoosePicType;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.LoadingDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现认证
 * Created by Administrator on 2017/6/19.
 */

public class ActivityAuth extends TakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etCardId)
    EditText etCardId;
    @BindView(R.id.ivCardFront)
    ImageView ivCardFront;
    @BindView(R.id.ivCardBack)
    ImageView ivCardBack;
    @BindView(R.id.ivCardWithPerson)
    ImageView ivCardWithPerson;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    @BindView(R.id.rlFront)
    RelativeLayout rlFront;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.rlWithPerson)
    RelativeLayout rlWithPerson;

    private int flag = 0;//0 正面  1 反面  2 手持
    private Activity _activity;
    private DialogChoosePicType dialog;
    private String str1 = "";//正
    private String str2 = "";//反
    private String str3 = "";//手持
    private List<String> pics = new ArrayList<>();
    private AppContext ac;
    private LoadingDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(_activity, 0, null);
        }
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        _activity = this;
        ac = (AppContext) getApplication();
        topbar.setTitle("实名认证");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.rlFront, R.id.rlBack, R.id.rlWithPerson, R.id.btnCommit})
    public void onViewClicked(View view) {
        dialog = new DialogChoosePicType(_activity);
        dialog.setTypeSelectedListener(listener);
        switch (view.getId()) {
            case R.id.rlFront:
                flag = 0;
                dialog.show();
                break;
            case R.id.rlBack:
                flag = 1;
                dialog.show();
                break;
            case R.id.rlWithPerson:
                flag = 2;
                dialog.show();
                break;
            case R.id.btnCommit:
                String name = etName.getText().toString();
                String cardId = etCardId.getText().toString();
                if("".equals(name)){
                    UIHelper.t(_activity,"请输入真实名字");
                    return;
                }
                if("".equals(cardId)){
                    UIHelper.t(_activity,"请输入身份证号码");
                    return;
                }
                if(pics.size()<3){
                    UIHelper.t(_activity,"请上传认证所需照片");
                    return;
                }
                AppUtil.getCloudApiClient(ac).commitAuth(ac.id, name, cardId, pics, callback);
                break;
        }
    }
    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {

        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if(res.isOK()){
                new DialogAuthComplete(_activity).show();
            }else{
                UIHelper.t(_activity,"提交失败！");
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            UIHelper.t(_activity,"提交失败！");
        }

        @Override
        public void onParseError(String tag) {

        }
    };

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
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String path = result.getImage().getCompressPath();
        if (!"".equals(path)) {
            if (flag == 0) {
                if(pics.size()>0){
                    pics.set(0, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardFront.setImageBitmap(BitmapFactory.decodeFile(pics.get(0)));
                    }
                });
            }
            if (flag == 1) {
                if(pics.size()>1){
                    pics.set(1, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardBack.setImageBitmap(BitmapFactory.decodeFile(pics.get(1)));
                    }
                });
            }
            if (flag == 2) {
                if(pics.size()>2){
                    pics.set(2, path);
                }else{
                    pics.add(path);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivCardWithPerson.setImageBitmap(BitmapFactory.decodeFile(pics.get(2)));
                    }
                });
            }
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);

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
