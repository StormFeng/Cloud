package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterActivityCollection;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CollectBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收藏的商户
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityCollection extends BaseActivity implements AdapterActivityCollection.onSelectedChangeListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private boolean isEdit = false;
    private TextView rightTv;
    private AdapterActivityCollection adapter;
    private onViewChangeListener onViewChangeListener;
    private String delete;

    private List<CollectBean.DataBean> data = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        topbar.setTitle("收藏的商户");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        rightTv = topbar.getRight_tv();
        topbar.setRightText("编辑", listener);
        adapter = new AdapterActivityCollection(_activity, data, status);
        listView.setAdapter(adapter);
        adapter.setOnSelectedChangeListener(this);
        delete = getResources().getString(R.string.delete);
        btnDelete.setText(String.format(delete, 0));

        AppUtil.getCloudApiClient(ac).collect(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if("collect".equals(tag)){
                data.clear();
                CollectBean bean = (CollectBean) res;
                data.addAll(bean.getData());
                for (int i = 0; i < data.size(); i++) {
                    status.add(i,false);
                }
                adapter.notifyDataSetChanged();
            }
            if("del".equals(tag)){
                AppUtil.getCloudApiClient(ac).collect(this);
//                rightTv.setText("编辑");
//                btnDelete.setVisibility(View.GONE);
                topbar.getRight_tv().performClick();
                updateCount(0);
                UIHelper.t(_activity,"删除成功！");
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isEdit) {
                rightTv.setText("编辑");
                btnDelete.setVisibility(View.GONE);
            } else {
                rightTv.setText("完成");
                btnDelete.setVisibility(View.VISIBLE);
            }
            isEdit = !isEdit;
            if(onViewChangeListener!=null){
                onViewChangeListener.onChaned(isEdit);
            }
        }
    };

    @OnClick(R.id.btnDelete)
    public void onViewClicked() {
        StringBuilder colid = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if(status.get(i)){
                colid.append(data.get(i).getCol_id()+",");
            }
        }
        if(colid.length()>0){
            StringBuilder ids = colid.deleteCharAt(colid.length() - 1);
            LogUtils.e(ids);
            AppUtil.getCloudApiClient(ac).del(ids.toString(),this);
        }else{
            UIHelper.t(_activity,"请选择需要删除的商户");
        }
    }

    public void setOnViewChangeListener(onViewChangeListener listener) {
        this.onViewChangeListener = listener;
    }

    @Override
    public void updateCount(int count) {
        btnDelete.setText(String.format(delete, count));
    }

    public interface onViewChangeListener {
        void onChaned(boolean isEdit);
    }
}
