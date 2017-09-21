package com.lida.cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityMap;
import com.lida.cloud.activity.ActivityReport;
import com.lida.cloud.activity.ActivityShopDetail;
import com.midian.base.base.BaseFragment;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商家详情
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentShopDetail extends BaseFragment {


    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvTime)
    TextView tvTime;
    Unbinder unbinder;
    @BindView(R.id.tvReport)
    TextView tvReport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopdetail, null);
        unbinder = ButterKnife.bind(this, v);
        tvDes.setText(ActivityShopDetail.des);
        tvPosition.setText(ActivityShopDetail.position);
        tvTel.setText(ActivityShopDetail.tel);
        tvTime.setText(ActivityShopDetail.time);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvReport,R.id.tvPosition})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tvReport:
                Bundle bundle = new Bundle();
                bundle.putString("selid", ActivityShopDetail.selid);
                UIHelper.jump(_activity, ActivityReport.class, bundle);
                break;
            case R.id.tvPosition:
                UIHelper.jump(_activity, ActivityMap.class);
                break;
        }
    }
}
