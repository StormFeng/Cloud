package com.lida.cloud.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivitySearchResult;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.AreaBean;
import com.lida.cloud.bean.TypeBean;
import com.lida.cloud.data.FragmentShopData;
import com.lida.cloud.tpl.FragmentShopTpl;
import com.lida.cloud.widght.AnimationHelper;
import com.lida.cloud.widght.DimedView;
import com.lida.cloud.widght.PopupCommen;
import com.lida.cloud.widght.PopupCommenCopy;
import com.lida.cloud.widght.SelectFilterView;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseListFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataAdapter;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 商家
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentShop extends BaseListFragment implements DimedView.OpenCloseListener, SelectFilterView.onTabChangeListener, PopupCommen.OnItemClickListener, PopupCommenCopy.OnItemClickListener, ApiCallback {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.selectedView)
    SelectFilterView selectedView;
    @BindView(R.id.bg_view)
    View bgView;
    Unbinder unbinder;


    private int index;//记录当前selectView位置
    private List<String> items= new ArrayList<>();
    private List<String> pitems= new ArrayList<>();
    public static PopupCommen popupWindow;
    public static PopupCommenCopy popupWindowCopy;

    private FragmentShopData dataSource;//列表数据

    private String type = "";//全部分类
    private String select1 = "全部分类";//全部分类
    private String select2 = "附近";//全部分类
//    private String select3 = "智能排序";//全部分类

    private AreaBean pBean;
    private AreaBean cBean = new AreaBean();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, v);
        topbar.setTitle("商家");
        topbar.setRightImageButton(R.drawable.icon_search,searchBtn);
        selectedView.setOnTabChangeListener(this);
        AppUtil.getCloudApiClient(ac).type(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("ac.getProperty(\"province\")"+ac.getProperty("province"));
        LogUtils.e("ac.getProperty(\"city\")"+ac.getProperty("city"));
        AppUtil.getCloudApiClient(ac).getArea(ac.getProperty("province"),ac.getProperty("city"),this);
    }

    View.OnClickListener searchBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity, ActivitySearchResult.class);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new FragmentShopData(_activity, type);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentShopTpl.class;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void open() {
        AnimationHelper.getFadeInAnimator(bgView).setDuration(200).start();
    }

    @Override
    public void close() {
        AnimationHelper.getFadeOutAnimator(bgView).setDuration(200).start();
        selectedView.changeState(index,false);
    }


    @Override
    public void onTabChange(int index, boolean isSelect) {
        this.index = index;
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
        if(popupWindowCopy!=null){
            popupWindowCopy.dismiss();
        }
        switch (index){
            case 0:
                if(isSelect){
                    showMenu(selectedView);
                }else{
                    popupWindow.dismiss();
                }
                break;
            case 1:
                if(isSelect){
                    showMenuCopy(selectedView);
                }else{
                    popupWindowCopy.dismiss();
                }
                break;
            case 2:
                if(isSelect){
                    showMenu(selectedView);
                }else{
                    popupWindow.dismiss();
                }
                break;
        }
        selectedView.changeState(index,isSelect);
    }

    private void showMenuCopy(View parent) {
        List<AreaBean.DataBean> temp = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            AreaBean.DataBean item = new AreaBean.DataBean();
            item.setD3_name(i+"千米");
            temp.add(item);
        }
        cBean.setData(temp);
        View view = LayoutInflater.from(_activity).inflate(R.layout.spinnercopy, null);
        popupWindowCopy = new PopupCommenCopy(_activity, view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, pBean, cBean);
        popupWindowCopy.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindowCopy.showAsDropDown(parent);
        popupWindowCopy.setOnItemClickListener(new PopupCommenCopy.OnItemClickListener() {
            @Override
            public void doNext(int positon, String text) {
                dataSource.setIndex(index);
                if(hasDigit(text)){//是否包含数字
                    LogUtils.e("包含数字");
                    if(positon==0){
                        text=1000+"";
                    }else if(positon==1){
                        text=2000+"";
                    }else if(positon==2){
                        text=3000+"";
                    }else if(positon==3){
                        text=4000+"";
                    }else if(positon==4){
                        text=5000+"";
                    }
                    dataSource.setParams(text,"");
                }else{
                    dataSource.setParams("", pBean.getData().get(positon).getD3_id());
                }
                LogUtils.e("index:"+index);
                listViewHelper.refresh();
            }
        });
        popupWindowCopy.setOnDismissListener(listener);
    }

    private void showMenu(View parent) {
        if(popupWindow!=null){
            popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
            popupWindow.showAsDropDown(parent);
            popupWindow.setOnItemClickListener(this);
        }
    }

    public boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void doNext(int positon, String text) {
        if(index==0){
            select1 = text;
        }
//        else if(index==2){
//            select3 = text;
//        }
        if(positon==0){
            text="";
        }
        String tabs[] = {select1,select2};
        select1 = "全部分类";//全部分类
        select2 = "附近";//全部分类
//        select3 = "智能排序";//全部分类
        selectedView.initTab(tabs);
        dataSource.setIndex(index);
        dataSource.setType(text);
        listViewHelper.refresh();
        popupWindow.dismiss();
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if(res.isOK()){
            if("type".equals(tag)){
                TypeBean typeBean = (TypeBean) res;
                items.add("全部分类");
                for (int i = 0; i < typeBean.getData().size(); i++) {
                    items.add(typeBean.getData().get(i).getTypename());
                }
                View view = LayoutInflater.from(_activity).inflate(R.layout.spinner, null);
                popupWindow = new PopupCommen(_activity, view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, items);
                popupWindow.setOnDismissListener(listener);
            }
            if("getArea".equals(tag)){
                pBean = (AreaBean) res;
            }
        }
    }

    PopupWindow.OnDismissListener listener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            for (int i = 0; i < 3; i++) {
                selectedView.setchangeState(i,false);
            }
        }
    };

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }

    @Override
    public void onStartRefresh(IDataAdapter adapter) {
        super.onStartRefresh(adapter);
        _activity.showLoadingDlg();
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, ArrayList result) {
        super.onEndRefresh(adapter, result);
        _activity.hideLoadingDlg();
    }
}
