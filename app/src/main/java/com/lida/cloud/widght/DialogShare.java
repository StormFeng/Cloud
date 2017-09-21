package com.lida.cloud.widght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class DialogShare extends Dialog {

    @BindView(R.id.iv_WeChat)
    TextView ivWeChat;
    @BindView(R.id.tvQQ)
    TextView tvQQ;
    @BindView(R.id.tvSina)
    TextView tvSina;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    private Context context;
    private String title;
    private String content;
    private String imageUrl;
    private String targetUrl;
    //imageurl不能为空
    private UMImage imagelocal;
    private UMWeb web;

    public DialogShare(Context context) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public DialogShare(Context context, int themeResId) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public DialogShare(Context context, String title, String content, String imageUrl, String targetUrl) {
        super(context, R.style.bottom_dialog);
        init(context);
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.targetUrl = targetUrl;
        imagelocal = new UMImage(context,R.drawable.share);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_share, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    @OnClick({R.id.iv_WeChat, R.id.tvQQ, R.id.tvSina, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_WeChat:
                share(SHARE_MEDIA.WEIXIN,title,content,imageUrl,targetUrl);
                break;
            case R.id.tvQQ:
                share(SHARE_MEDIA.QQ,title,content,imageUrl,targetUrl);
                break;
            case R.id.tvSina:
                share(SHARE_MEDIA.SINA,title,content,imageUrl,targetUrl);
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(DialogShare.this);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context,"分享成功！",Toast.LENGTH_LONG).show();
            SocializeUtils.safeCloseDialog(DialogShare.this);
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SocializeUtils.safeCloseDialog(DialogShare.this);
            Toast.makeText(context,"分享失败！"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            SocializeUtils.safeCloseDialog(DialogShare.this);
            Toast.makeText(context,"分享取消了!",Toast.LENGTH_LONG).show();

        }
    };

    public void share(SHARE_MEDIA platform, String title, String content, String imageUrl, String targetUrl) {
        web = new UMWeb(targetUrl);
        web.setTitle(title);
        web.setDescription(content);
        web.setThumb(imagelocal);
        new ShareAction((Activity) context)
                .withMedia(web)
                .setPlatform(platform)
                .setCallback(shareListener)
                .share();

//        new ShareAction((Activity) context)
//                .withSubject("title")
//                .withText(content)
//                .withMedia(imagelocal)
//                .setPlatform(platform)
//                .setCallback(shareListener)
//                .share();
    }
}
