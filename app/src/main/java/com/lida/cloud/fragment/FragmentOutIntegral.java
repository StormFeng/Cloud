package com.lida.cloud.fragment;

import com.lida.cloud.activity.ActivityKCJF;
import com.lida.cloud.data.FragmentOutIntegralData;
import com.lida.cloud.tpl.FragmentOutIntegralTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13.
 */

public class FragmentOutIntegral extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentOutIntegralData(_activity, ActivityKCJF.selid);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentOutIntegralTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
