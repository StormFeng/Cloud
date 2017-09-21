package com.lida.cloud.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.DialogChoosePicType;
import com.lida.cloud.widght.DialogIfDeleteGood;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品管理
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterGoodManage extends BaseAdapter {

    private TakePhotoActivity context;
    private DialogChoosePicType dialog;
    private List<GoodBean.DataBean> pics=new ArrayList<>();
    private AppContext ac;

    public AdapterGoodManage(TakePhotoActivity context, List<GoodBean.DataBean> pics) {
        this.context = context;
        this.pics = pics;
        ac = (AppContext) context.getApplication();
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
    public int getCount() {
        return pics.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pic, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position==pics.size()-1){
            viewHolder.ivAdd.setVisibility(View.VISIBLE);
            viewHolder.ivDel.setVisibility(View.GONE);
            viewHolder.ivSource.setVisibility(View.GONE);
        }else{
            viewHolder.ivAdd.setVisibility(View.GONE);
            viewHolder.ivSource.setVisibility(View.VISIBLE);
            viewHolder.ivDel.setVisibility(View.VISIBLE);
            ac.setImage(viewHolder.ivSource, Constant.IMGSHOP + pics.get(position).getSp_image());
        }
        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=50){
                    UIHelper.t(context,"最多只能上传50张商品图片！");
                    return;
                }
                dialog = new DialogChoosePicType(context);
                dialog.setTypeSelectedListener(listener);
                dialog.show();
            }
        });
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogIfDeleteGood dialog = new DialogIfDeleteGood(context);
                dialog.setOnButtonClickListener(new DialogIfDeleteGood.onButtonClickListener() {
                    @Override
                    public void onButtonClicked() {
                        AppUtil.getCloudApiClient(ac).delshop(pics.get(position).getSp_id(),new BaseApiCallback(){
                            @Override
                            public void onApiStart(String tag) {
                                super.onApiStart(tag);
                                finalViewHolder.ivDel.setClickable(false);
                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                if(res.isOK()){
                                    finalViewHolder.ivDel.setClickable(true);
                                    dialog.dismiss();
                                    pics.remove(position);
                                    UIHelper.t(context,"删除成功！");
                                    notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                super.onApiFailure(t, errorNo, strMsg, tag);
                                finalViewHolder.ivDel.setClickable(true);
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivSource)
        ImageView ivSource;
        @BindView(R.id.ivAdd)
        ImageView ivAdd;
        @BindView(R.id.ivDel)
        ImageView ivDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
