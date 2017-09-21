package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.PriceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/13.
 */

public class AdapterUpdate extends BaseAdapter {

    private Context context;
    private List<PriceBean.DataBean> data;

    public AdapterUpdate(Context context, List<PriceBean.DataBean> data) {
        this.context = context;
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            if(i==0){
                data.get(i).setSelect(true);
            }else{
                data.get(i).setSelect(false);
            }
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_update, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvShopFan.setText("返现比例" + data.get(position).getPri_back() + "%");
        viewHolder.tvShopPrice.setText("￥" + data.get(position).getPri_type());
        viewHolder.tvShopName.setText(data.get(position).getMem_type());
        if(data.get(position).isSelect()){
            viewHolder.cb.setChecked(true);
        }else{
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setSelect(false);
                }
                data.get(position).setSelect(true);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvShopName)
        TextView tvShopName;
        @BindView(R.id.tvShopPrice)
        TextView tvShopPrice;
        @BindView(R.id.tvShopFan)
        TextView tvShopFan;
        @BindView(R.id.cb)
        RadioButton cb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
