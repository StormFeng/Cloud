package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.GoodBean;
import com.midian.base.app.AppContext;
import com.midian.base.widget.PhotoPicker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品详情-商品展示
 * Created by WeiQingFeng on 2017/5/4.
 */

public class AdapterFragGoods extends BaseAdapter {

    private Activity context;
    private List<GoodBean.DataBean> data;
    private AppContext ac;
    private ArrayList<String> pics = new ArrayList<>();//页面中间5张图片

    public AdapterFragGoods(Activity context, List<GoodBean.DataBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplication();
        for (int i = 0; i < data.size(); i++) {
            pics.add(Constant.IMGSHOP + data.get(i).getSp_image());
        }
    }

    @Override
    public int getCount() {
        LogUtils.e(data);
        return data.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragmentgood, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ac.setImage(viewHolder.iv, Constant.IMGSHOP + data.get(position).getSp_image());
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(pics);
                PhotoPreview.builder()
                        .setPhotos(pics)
                        .setCurrentItem(position)
                        .setShowDeleteButton(false)
                        .start(context);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivSource)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
