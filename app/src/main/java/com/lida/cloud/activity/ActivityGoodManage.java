package com.lida.cloud.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import com.apkfuns.logutils.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterGoodManage;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.InnerGridView;
import com.midian.base.app.AppContext;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品管理
 * Created by Administrator on 2017/5/31.
 */

public class ActivityGoodManage extends TakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.gvPic)
    GridView gvPic;

    private AdapterGoodManage adapter;
    private List<GoodBean.DataBean> pics = new ArrayList<>();
    private AppContext ac;
    private Activity _activity;
    private String selid;

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
        setContentView(R.layout.activity_goodmanage);
        ButterKnife.bind(this);
        selid = getIntent().getExtras().getString("selid");
        ac = (AppContext) getApplication();
        _activity = this;
        topbar.setTitle("商品管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        pics.add(new GoodBean.DataBean());
        adapter = new AdapterGoodManage(this, pics);
        gvPic.setAdapter(adapter);
        AppUtil.getCloudApiClient(ac).shopli(selid, callback);
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
            String path = result.getImage().getCompressPath();
            AppUtil.getCloudApiClient(ac).addShop(selid,path,callback);
//            pics.add(pics.size() - 1, path);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.notifyDataSetChanged();
//                }
//            });
        }
    }

    BaseApiCallback callback = new BaseApiCallback(){

        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            super.onApiSuccess(res, tag);
            if(res.isOK()){
                if("addShop".equals(tag)){
                    UIHelper.t(_activity, "图片上传成功！");
                    AppUtil.getCloudApiClient(ac).shopli(selid, callback);
                }
                if("shopli".equals(tag)){
                    GoodBean bean = (GoodBean) res;
                    pics.clear();
                    pics.add(new GoodBean.DataBean());
                    for (int i = 0; i < bean.getData().size(); i++) {
                        pics.add(pics.size() - 1, bean.getData().get(i));
                    }
                    LogUtils.e(pics);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };

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
