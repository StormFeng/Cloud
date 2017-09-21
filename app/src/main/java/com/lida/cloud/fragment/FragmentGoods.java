package com.lida.cloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.adapter.AdapterFragGoods;
import com.lida.cloud.adapter.AdapterHomeGridView;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.InnerGridView;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 商品展示
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentGoods extends BaseFragment {
    @BindView(R.id.gridView)
    InnerGridView gridView;
    Unbinder unbinder;

    private AdapterFragGoods adapter;
    private List<GoodBean.DataBean> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goods, null);
        unbinder = ButterKnife.bind(this, v);
        adapter = new AdapterFragGoods(_activity,data);
        gridView.setAdapter(adapter);
        AppUtil.getCloudApiClient(ac).shopli(ActivityShopDetail.selid, callback);
        return v;
    }

    BaseApiCallback callback = new BaseApiCallback(){
        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            if(res.isOK()){
                GoodBean bean = (GoodBean) res;
                if(bean.getData()!=null){
                    data.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
