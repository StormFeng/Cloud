package com.midian.base.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.bishilai.thirdpackage.R;
import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.jaeger.library.StatusBarUtil;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.app.AppManager;
import com.midian.base.bean.NetResult;
import com.midian.base.util.OnTouchEffectUtil;
import com.midian.base.util.UIHelper;
import com.midian.base.util.UMengStatistticUtil;
import com.midian.base.widget.dialog.LoadingDialog;


/**
 * Activity基类
 * 
 * @author XuYang
 * 
 */
public class BaseActivity extends Activity implements ApiCallback,OnClickListener, OnPermissionCallback{

	protected AppContext ac;
	protected Intent _Intent;
	protected Bundle mBundle;
	protected Activity _activity;
	protected LoadingDialog dlg;
	protected PermissionHelper permissionHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		} else {
			StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
			StatusBarUtil.setTranslucentForImageViewInFragment(_activity, 0, null);
		}
		_activity = this;
		ac = (AppContext) getApplication();
		_Intent = getIntent();
		if (_Intent != null) {
			mBundle = _Intent.getExtras();
		}
		permissionHelper = PermissionHelper.getInstance(_activity);
	}

	public void showLoadingDlg(String msg, final boolean isNotBackFinish) {
		if (dlg != null && dlg.isShowing()) {
			return;
		}
		if (dlg == null) {
			dlg = new LoadingDialog(this, R.layout.dialog_msg,
					R.style.dialog_msg);
		}
		dlg.setCanceledOnTouchOutside(isNotBackFinish);
		dlg.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				if (!isNotBackFinish) {
					finish();
				}
			}
		});
		dlg.showMessage(msg);
	}

	public void showLoadingDlg() {
		showLoadingDlg("", true);
	}

	public void hideLoadingDlg() {
		if (dlg != null) {
			dlg.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
		if (dlg != null) {
			dlg.dismiss();
		}
	}

	/**
	 * 返回结果到上一个activity
	 * 
	 * @param resultCode
	 * @param bundle
	 */
	protected void setResult(int resultCode, Bundle bundle) {
		Intent intent = new Intent();
		intent.putExtras(bundle);
		setResult(resultCode, intent);
	}

	protected <T extends View> T findView(int id) {
		View child = findViewById(id);
		return (T) child;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		UMengStatistticUtil.onResumeForActivity(this);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus){
			setPress();
		}
	}

	public void setPress(){
		FrameLayout root= (FrameLayout)this.getWindow().getDecorView();
		setViewPressState(root);
	}


	private void setViewPressState(ViewGroup groud){
		for(int i=0;i<groud.getChildCount();i++){
			View view=groud.getChildAt(i);
			if(view instanceof ViewGroup){
				setViewPressState((ViewGroup) view);
			}else{
				OnTouchEffectUtil.setViewTouchEffectforBase(view);
			}
		}
		OnTouchEffectUtil.setViewTouchEffectforBase(groud);
	}

	@Override
	protected void onPause() {
		super.onPause();
		UMengStatistticUtil.onPauseForActivity(this);
	}

	@Override
	public void onApiStart(String tag) {
	}

	@Override
	public void onApiLoading(long count, long current, String tag) {

	}

	@Override
	public void onApiSuccess(NetResult res, String tag) {

	}

	@Override
	public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
		t.printStackTrace();
		UIHelper.t(_activity, getString(R.string.net_error));
	}

	@Override
	public void onParseError(String tag) {
		UIHelper.t(_activity, getString(R.string.parser_error));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPermissionGranted(@NonNull String[] permissionName) {

	}

	@Override
	public void onPermissionDeclined(@NonNull String[] permissionName) {

	}

	@Override
	public void onPermissionPreGranted(@NonNull String permissionsName) {

	}

	@Override
	public void onPermissionNeedExplanation(@NonNull String permissionName) {

	}

	@Override
	public void onPermissionReallyDeclined(@NonNull String permissionName) {

	}

	@Override
	public void onNoPermissionNeeded() {

	}
}
