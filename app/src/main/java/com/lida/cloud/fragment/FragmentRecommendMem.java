package com.lida.cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityRecommendMemData;
import com.lida.cloud.data.ActivityRecommendShopData;
import com.lida.cloud.tpl.ActivityRecommendMemTpl;
import com.lida.cloud.tpl.ActivityRecommendShopTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.base.BaseListFragment;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐的会员
 * Created by Administrator on 2017/6/2.
 */

public class FragmentRecommendMem extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRecommendMemData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRecommendMemTpl.class;
    }
}
