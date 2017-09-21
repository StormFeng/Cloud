package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivitySearchResultData;
import com.lida.cloud.tpl.ActivitySearchResultTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家搜索结果页
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivitySearchResult extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private ActivitySearchResultData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("商家");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    data.setCon(etSearch.getText().toString());
                    listViewHelper.refresh();
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        data = new ActivitySearchResultData(_activity);
        return data;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivitySearchResultTpl.class;
    }
}