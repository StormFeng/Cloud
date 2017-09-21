package com.lida.cloud.data;

import android.app.Activity;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.activity.ActivityKCJF;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.KCJFBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentInIntegralData extends BaseListDataSource {

    private String selid;

    public FragmentInIntegralData(Context context, String selid) {
        super(context);
        this.selid = selid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        final KCJFBean bean = AppUtil.getCloudApiClient(ac).jifen(selid);
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData().getLi());
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(bean.getData().getKc()==null){
                    ac.setProperty("integral","0");
                    ActivityKCJF.tvKCJF.setText("0");
                }else{
                    ac.setProperty("integral",bean.getData().getKc());
                    ActivityKCJF.tvKCJF.setText(bean.getData().getKc());
                }
            }
        });
        hasMore = false;
        return models;
    }
}
