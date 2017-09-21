package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.lida.cloud.R;
import com.lida.cloud.data.ActivityCommentData;
import com.lida.cloud.tpl.ActivityCommentTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.PullToRefreshListView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ActivityCommentList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnComment)
    Button btnComment;
    @BindView(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;

    private String selid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("评论");
        topbar.setLeftImageButton(R.drawable.icon_back,UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        selid = mBundle.getString("selid");
        return new ActivityCommentData(_activity,selid);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityCommentTpl.class;
    }

    @OnClick(R.id.btnComment)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("selid", selid);
        UIHelper.jumpForResult(_activity,ActivityComment.class,bundle,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}
