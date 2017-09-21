package com.lida.cloud.app;
import com.midian.base.app.AppContext;
/**
 * Created by Administrator on 2017/3/7.
 */

public class AppUtil {
    public static BaseAppContext getBaseAppContext(AppContext ac){
        return (BaseAppContext) ac;
    }

    public static CloudApiClient getCloudApiClient(AppContext ac){
        return ac.api.getApiClient(CloudApiClient.class);
    }
}
