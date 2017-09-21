package com.lida.cloud.data;

import android.content.Context;

import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CommentBean;
import com.lida.cloud.bean.SellerBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 商家评论
 * Created by WeiQingFeng on 2017/5/4.
 */

public class ActivityCommentData extends BaseListDataSource {

    private String selid;

    public ActivityCommentData(Context context, String selid) {
        super(context);
        this.selid = selid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        CommentBean bean = AppUtil.getCloudApiClient(ac).commentli(selid);
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
