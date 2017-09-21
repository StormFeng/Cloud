package com.lida.cloud.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.PersonalDataBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.DialogChangePhone;
import com.lida.cloud.widght.DialogChoosePicType;
import com.lida.cloud.widght.DialogChooseSex;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityPersonalData extends TakePhotoActivity implements ApiCallback {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnSeve)
    Button btnSeve;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.llSex)
    LinearLayout llSex;
    @BindView(R.id.etAdd)
    EditText etAdd;
    @BindView(R.id.etMail)
    EditText etMail;

    private DialogChoosePicType dialog;
    private TakePhotoActivity context;

    private String headPath = "";
    private String name;
    private String phone;
    private String sex;
    private String address;
    private String email;

    private AppContext ac;
    private DialogChooseSex dialogChooseSex;
    private DialogChangePhone dialogChangePhone;
    private Activity _activity;

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
        setContentView(R.layout.activity_personaldata);
        ButterKnife.bind(this);
        ac = (AppContext) getApplication();
        _activity = this;
        context=this;
        topbar.setTitle("个人资料");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(context));
        ac.setImage(ivHead, Constant.IMGBASEURLPB+ac.getProperty("head_img"));
        etName.setText(ac.getProperty("mem_nc"));
        tvPhone.setText(ac.getProperty("phone"));
        tvSex.setText(ac.getProperty("sex"));
        etAdd.setText(ac.getProperty("address"));
        etMail.setText(ac.getProperty("email"));

        dialogChooseSex =  new DialogChooseSex(context);
        dialogChooseSex.setTypeSelectedListener(new DialogChooseSex.onTypeSelectedListener() {
            @Override
            public void select(String s) {
                tvSex.setText(s);
            }
        });
    }

    @OnClick(R.id.btnSeve)
    public void onViewClicked() {
        LogUtils.e("onViewClicked");
        name = etName.getText().toString();
        phone = tvPhone.getText().toString();
        sex = tvSex.getText().toString();
        address = etAdd.getText().toString();
        email = etMail.getText().toString();
//        if("".equals(etName.getText().toString())){
//            UIHelper.t(context,"昵称不能为空！");
//            return;
//        }
//        if(!Func.checkNameChese(etName.getText().toString())){
//            UIHelper.t(context,"昵称必须为中文！");
//            return;
//        }
        try {
            AppUtil.getCloudApiClient(ac).alter(name,sex,phone,address,email,headPath,this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.llHead, R.id.llPhone, R.id.llSex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llHead:
                dialog = new DialogChoosePicType(context);
                dialog.setTypeSelectedListener(listener);
                dialog.show();
                break;
            case R.id.llPhone:
                dialogChangePhone = new DialogChangePhone(context);
                dialogChangePhone.setOnTelSelect(new DialogChangePhone.onTelSelect() {
                    @Override
                    public void onTelSelect(final String phone, String code) {
                        AppUtil.getCloudApiClient(ac).pdcode(code, new BaseApiCallback(){
                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                if(res.isOK()){
                                    if("no!".equals(res.getMessage())){
                                        UIHelper.t(_activity, "验证码不正确！");
                                        return;
                                    }
                                    tvPhone.setText(phone);
                                    dialogChangePhone.dismiss();
                                }
                            }
                        });

                    }
                });
                dialogChangePhone.show();
                break;
            case R.id.llSex:
                dialogChooseSex.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
           headPath = result.getImage().getCompressPath();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeFile(headPath);
                    ivHead.setImageBitmap(bitmap);
                }
            });
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        LogUtils.e(result);
    }

    DialogChoosePicType.onTypeSelectedListener listener=new DialogChoosePicType.onTypeSelectedListener() {
        @Override
        public void onOpenCamera() {
            TakePhoto takePhoto = context.getTakePhoto();
            File file=new File(Environment.getExternalStorageDirectory(), "/activity_chooseposition/"+System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists())file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromCapture(imageUri);
        }

        @Override
        public void onOpenPic() {
            TakePhoto takePhoto = context.getTakePhoto();
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromGallery();
        }
    };

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if(res.isOK()){
            PersonalDataBean bean = (PersonalDataBean) res;
            if(!"".equals(headPath)){
                ac.setProperty("head_img",bean.getData().getMem_tx());
            }
            if(name!=null){
                ac.setProperty("mem_nc",bean.getData().getMem_nc());
            }
            if(phone!=null){
                ac.setProperty("phone",bean.getData().getMem_tel());
            }
            if(sex!=null){
                ac.setProperty("sex",bean.getData().getMem_sex());
            }
            if(address!=null){
                ac.setProperty("address",bean.getData().getMem_add());
            }
            if(email!=null){
                ac.setProperty("email",bean.getData().getMem_email());
            }
            UIHelper.t(context, "个人资料修改成功！");
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
