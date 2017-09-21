package com.lida.cloud.fragment;

import com.lida.cloud.activity.ActivityKCJF;
import com.lida.cloud.data.FragmentInIntegralData;
import com.lida.cloud.tpl.FragmentInIntegralTpl;
import com.lida.cloud.tpl.FragmentOutIntegralTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13.
 */

public class FragmentInIntegral extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentInIntegralData(_activity, ActivityKCJF.selid);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentInIntegralTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
