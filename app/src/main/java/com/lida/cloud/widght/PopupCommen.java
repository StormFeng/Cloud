package com.lida.cloud.widght;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.cloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class PopupCommen extends PopupWindow {

    private Context context;
    private ListView listView;
    private List<String> data=new ArrayList<>();
    private OnItemClickListener listener;

    public PopupCommen(Context context, View contentView, int width, int height, List<String> data) {
        super(contentView, width, height);
        this.context = context;
        this.data = data;
        listView = (ListView) contentView.findViewById(R.id.listView);
        listView.setAdapter(new Adapter());
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_dimendview,null);
            tv.setText(data.get(position));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.doNext(position,tv.getText().toString().trim());
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
