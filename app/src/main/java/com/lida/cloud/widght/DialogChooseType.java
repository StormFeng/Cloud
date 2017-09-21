package com.lida.cloud.widght;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.bean.TypeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家选择类别
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogChooseType extends Dialog {

    @BindView(R.id.listView)
    InnerListView listView;
    private Context context;
    private onTypeSelectedListener listener;

    private TypeBean data;
    private TextView tv;

    public DialogChooseType(Context context, TypeBean data, TextView tv) {
        super(context, R.style.diy_dialog);
        this.context = context;
        this.data = data;
        this.tv = tv;
        init(context);
    }

    public DialogChooseType(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public void setTypeSelectedListener(onTypeSelectedListener listener) {
        this.listener = listener;
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_choosetype, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        listView.setAdapter(new MyAdapter());
    }

    public interface onTypeSelectedListener {
        void select(String s);
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.getData().size();
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
            if(convertView==null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_dialogchoosetype, null);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.tv.setText(data.getData().get(position).getTypename());
            viewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setText(data.getData().get(position).getTypename());
                    dismiss();
                }
            });
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tv)
            TextView tv;
        }
    }
}
