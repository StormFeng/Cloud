package com.lida.cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityRecommendShopData;
import com.lida.cloud.data.ActivityRewardMarkData;
import com.lida.cloud.tpl.ActivityRecommendShopTpl;
import com.lida.cloud.tpl.ActivityRewardMarkTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.base.BaseListFragment;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐的商户
 * Created by Administrator on 2017/6/2.
 */

public class FragmentRecommendShop extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRecommendShopData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRecommendShopTpl.class;
    }
}
