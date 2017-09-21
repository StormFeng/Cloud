package com.lida.cloud.widght;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.bean.AreaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class PopupCommenCopy extends PopupWindow {

    private Context context;
    private ListView pListView;
    private ListView cListView;
    private AreaBean pData;
    private AreaBean cData;
    private OnItemClickListener listener;

    public PopupCommenCopy(Context context, View contentView, int width, int height, AreaBean pData,  AreaBean cData) {
        super(contentView, width, height);
        this.context = context;
        this.pData = pData;
        this.cData = cData;
        LogUtils.e(this.pData);
        LogUtils.e(this.cData);
        pListView = (ListView) contentView.findViewById(R.id.pListView);
        cListView = (ListView) contentView.findViewById(R.id.cListView);
        pListView.setAdapter(new Adapter(this.pData));
        cListView.setAdapter(new Adapter(this.cData));
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class Adapter extends BaseAdapter{

        private AreaBean data;

        public Adapter(AreaBean data) {
            LogUtils.e(data);
            this.data = data;
        }

        @Override
        public int getCount() {
            if(data!=null){
                return data.getData().size();
            }else{
                return 0;
            }
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
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_dimendview,null);
            tv.setText(data.getData().get(position).getD3_name());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.doNext(position,tv.getText().toString().trim());
                        dismiss();
                    }
                }
            });
            return tv;
        }
    }

    public interface OnItemClickListener {
        void doNext(int positon, String text);
    }
}
