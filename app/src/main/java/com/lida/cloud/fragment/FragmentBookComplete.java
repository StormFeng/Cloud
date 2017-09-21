package com.lida.cloud.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lida.cloud.data.FragmentBookCompleteData;
import com.lida.cloud.tpl.FragmentBookCompleteTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.util.Func;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

/**
 * 已完成订单
 * Created by WeiQingFeng on 2017/5/5.
 */

public class FragmentBookComplete extends BaseListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        listView.setPadding(0, Func.Dp2Px(_activity,10),0,0);
        listView.setDivider(null);
        listView.setDividerHeight(Func.Dp2Px(_activity,10));
        return v;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentBookCompleteData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentBookCompleteTpl.class;
    }
}
