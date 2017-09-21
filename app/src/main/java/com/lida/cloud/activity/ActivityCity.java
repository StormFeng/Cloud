package com.lida.cloud.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterCity;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.widght.IndexList.CityBean;
import com.lida.cloud.widght.IndexList.DividerItemDecoration;
import com.lida.cloud.widght.IndexList.HeaderRecyclerAndFooterWrapperAdapter;
import com.lida.cloud.widght.IndexList.ViewHolder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
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
 * 选择城市
 * Created by Administrator on 2017/6/8.
 */

public class ActivityCity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.indexBar)
    IndexBar mIndexBar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvCurrentPosition)
    TextView tvCurrentPosition;
    @BindView(R.id.btnOk)
    Button btnOk;

    private AdapterCity mAdapter;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<CityBean> mDatas;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        ButterKnife.bind(this);
        topbar.setTitle("市");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        mRv.setLayoutManager(mManager = new LinearLayoutManager(_activity));
        mAdapter = new AdapterCity(_activity, mDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(_activity, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mRv.addItemDecoration(new DividerItemDecoration(_activity, DividerItemDecoration.VERTICAL_LIST));
        mIndexBar.setmPressedShowTextView(mTvSideBarHint).setmLayoutManager(mManager);
        tvCurrentPosition.setText(ac.getProperty("position"));
        AppUtil.getCloudApiClient(ac).getCity(mBundle.getString("provinceId"), this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            com.lida.cloud.bean.CityBean bean = (com.lida.cloud.bean.CityBean) res;
            LogUtils.e(bean);
            mDatas = new ArrayList<>();
            if (bean.getData().size() > 0) {
                for (int i = 0; i < bean.getData().size(); i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(bean.getData().get(i).getD2_name());
                    cityBean.setId(bean.getData().get(i).getD2_id());
                    mDatas.add(cityBean);
                }
            }
            mIndexBar.setmSourceDatas(mDatas)//设置数据
                    .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                    .invalidate();

            mAdapter.setDatas(mDatas);
            mHeaderAdapter.notifyDataSetChanged();
            mDecoration.setmDatas(mDatas);
            mDecoration.setColorTitleFont(Color.parseColor("#F55D12"));
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            ac.setProperty("district", data.getStringExtra("area"));
            setResult(RESULT_OK);
            finish();
        }
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        ac.setProperty("district",ac.getProperty("currentArea"));
        setResult(RESULT_OK);
        finish();
    }
}
