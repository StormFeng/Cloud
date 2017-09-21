package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityCollection;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.CollectBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收藏的商户
 * Created by WeiQingFeng on 2017/5/4.
 */

public class AdapterActivityCollection extends BaseAdapter {

    private Context context;
    private List<Boolean> status;
    private boolean isEdit;
    private onSelectedChangeListener onSelectedChangeListener;
    private int count = 0;

    private List<CollectBean.DataBean> data;


    public AdapterActivityCollection(Context context, List<CollectBean.DataBean> data, List<Boolean> status) {
        this.context = context;
        this.data = data;
        this.status = status;
        ((ActivityCollection) context).setOnViewChangeListener(new ActivityCollection.onViewChangeListener() {
            @Override
            public void onChaned(boolean is) {
                isEdit = is;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activitycollection, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (isEdit) {
            viewHolder.cb.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cb.setVisibility(View.GONE);
        }
        if (status.get(position)) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }

        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.set(position, viewHolder.cb.isChecked());
                count = 0;
                for (int i = 0; i < status.size(); i++) {
                    if (status.get(i)) {
                        count++;
                    }
                    if (onSelectedChangeListener != null) {
                        onSelectedChangeListener.updateCount(count);
                    }
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",data.get(position).getSelid());
                UIHelper.jump((Activity) context, ActivityShopDetail.class, bundle);
            }
        });
        viewHolder.tvName.setText(data.get(position).getSelshopname());
        ((AppContext)(((Activity) context).getApplication())).setImage(viewHolder.iv, Constant.IMGSHOP+data.get(position).getSelimage());
        viewHolder.tvFanLi.setText(data.get(position).getSelback()+"%");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvFanLi)
        TextView tvFanLi;
        @BindView(R.id.tvPosition)
        TextView tvPosition;
        @BindView(R.id.tvDistance)
        TextView tvDistance;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnSelectedChangeListener(onSelectedChangeListener listener) {
        this.onSelectedChangeListener = listener;
    }

    public interface onSelectedChangeListener {
        void updateCount(int count);
    }
}
