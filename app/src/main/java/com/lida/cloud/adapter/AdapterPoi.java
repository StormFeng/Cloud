package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.jph.takephoto.app.TakePhotoActivity;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityChooseArea;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择照片
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterPoi extends BaseAdapter {

    private ActivityChooseArea context;
    private List<PoiInfo> data = new ArrayList<>();

    public AdapterPoi(ActivityChooseArea context, List<PoiInfo> data) {
        this.context = context;
        this.data = data;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_poi, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.get(position).name);
        viewHolder.tvPosition.setText(data.get(position).address);
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("location",data.get(position));
                intent.putExtra("province",context.province);
                intent.putExtra("city",context.city);
                intent.putExtra("area",context.district);
                context.setResult(Activity.RESULT_OK, intent);
                context.finish();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvPosition)
        TextView tvPosition;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
