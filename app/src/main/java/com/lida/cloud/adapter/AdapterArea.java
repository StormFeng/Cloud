package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityArea;
import com.lida.cloud.widght.IndexList.CityBean;
import com.midian.base.util.UIHelper;

import java.util.List;

/**
 * 区
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class AdapterArea extends RecyclerView.Adapter<AdapterArea.ViewHolder> {
    protected Activity mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;

    public AdapterArea(Activity mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public AdapterArea setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public AdapterArea.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdapterArea.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("area",cityBean.getCity());
                mContext.setResult(Activity.RESULT_OK, intent);
                mContext.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            content = itemView.findViewById(R.id.content);
        }
    }
}
