package com.lida.cloud.data;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.SellerBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 商家
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentShopData extends BaseListDataSource {

    private String type;
    private int index;
    private String methers;
    private String dqid;

    public FragmentShopData(Context context, String type) {
        super(context);
        this.type = type;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void setParams(String var1, String var2){
        this.methers = var1;
        this.dqid = var2;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        SellerBean seller = null;
        if(index==0){
            seller = AppUtil.getCloudApiClient(ac).seller(type,ac.lon,ac.lat);
        }
        if(index==1){
            seller = AppUtil.getCloudApiClient(ac).nearby(ac.getProperty("lon"),ac.getProperty("lat"),methers,dqid);
        }
        models.addAll(seller.getData().getCy());
        hasMore = false;
        return models;
    }
}
